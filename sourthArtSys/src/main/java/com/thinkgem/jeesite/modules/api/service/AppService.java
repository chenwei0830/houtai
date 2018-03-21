package com.thinkgem.jeesite.modules.api.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.common.utils.PhoneUtils;
import com.thinkgem.jeesite.modules.api.entity.ArtWorksVo;
import com.thinkgem.jeesite.modules.api.entity.MineArtWorks;
import com.thinkgem.jeesite.modules.api.entity.MineWorksAndInterest;
import com.thinkgem.jeesite.modules.api.entity.WxUser;
import com.thinkgem.jeesite.modules.art.dao.ArtAuthDao;
import com.thinkgem.jeesite.modules.art.dao.ArtWorksDao;
import com.thinkgem.jeesite.modules.art.entity.ArtAuth;
import com.thinkgem.jeesite.modules.art.entity.ArtAuthImg;
import com.thinkgem.jeesite.modules.art.entity.ArtWorks;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksContent;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 接口Service
 * @author LM
 */

@Service
@Transactional(readOnly = true)
public class AppService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ArtAuthDao artAuthDao;
	
	@Autowired
	private ArtWorksDao artWorksDao;
	/**
	 * 根据艺术类别获取列表
	 */
	public List<ArtWorksVo> listArtWorks(){
		
		return null;
	}
	
	/**
	 * 根据openId获取用户
	 */
	public User getUserByOpenId(String openId,String orgId) {
		return userDao.getByOpenId(openId,orgId);
	}
	
	
	/**
	 * 根据openId更新用户
	 */
	public int updateUserByOpenId(WxUser wxUser) {
		return userDao.updateUserByOpenId(wxUser);
	}
	
	
	
	/**
	 * 认证保存
	 */
	@Transactional(readOnly = false)
	public boolean saveAuth(ArtAuth artAuth) {
		
		//校验
		if(StringUtils.isNotBlank(artAuth.getOpenId())
				&& StringUtils.isNotBlank(artAuth.getName())
				&& StringUtils.isNotBlank(artAuth.getIdCard()) 
				&& IdcardUtils.validateCard(artAuth.getIdCard())
				&& StringUtils.isNotBlank(artAuth.getPhone()) 
				&& PhoneUtils.checkMobile(artAuth.getPhone())
				&& StringUtils.isNotBlank(artAuth.getArtType())
				&& StringUtils.isNotBlank(artAuth.getArtLevel())
				&& !artAuth.getrList().isEmpty()
				&& StringUtils.isNotBlank(artAuth.getOrgId())) {
			User user = userDao.getByOpenId(artAuth.getOpenId(),artAuth.getOrgId());
			//插入主记录
			artAuth.preInsert();
			artAuth.setUpdateBy(user);
			artAuth.setCreateBy(user);
			artAuth.setUser(user);
			artAuth.setStatus("0");//强制设置为'0'-待审核状态
			artAuth.setOrg(new Org(artAuth.getOrgId()));
			int a = artAuthDao.insert(artAuth);
			if(a>0) {
				//插入认证图片
				for(String imgUrl : artAuth.getrList()) {
					ArtAuthImg img = new ArtAuthImg();
					img.setUserAuthId(artAuth.getId());
					img.setImgUrl(imgUrl);
					img.preInsert();
					artAuthDao.insertAuthImg(img);
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * 作品保存
	 */
	@Transactional(readOnly = false)
	public boolean saveArtworks(ArtWorks artWorks) {
		
		//校验
		if(StringUtils.isNotBlank(artWorks.getOpenId()) && StringUtils.isNotBlank(artWorks.getOrgId())) {
			
			User user = userDao.getByOpenId(artWorks.getOpenId(),artWorks.getOrgId());
			if(user!=null) {
				//插入主记录
				artWorks.setId(IdGen.uuid());
				artWorks.setUpdateBy(user);
				artWorks.setCreateBy(user);
				artWorks.setUpdateDate(new Date());
				artWorks.setCreateDate(artWorks.getUpdateDate());
				artWorks.setUser(user);
				artWorks.setStatus("0");//强制设置为'0'-待审核状态
				artWorks.setOrg(new Org(artWorks.getOrgId()));
				artWorks.setDzNum(0);//默认点赞数为0
				int a = artWorksDao.insert(artWorks);
				if(a>0) {
					switch (artWorks.getModelType()) {
					case "0"://图文
						//插入认证图片
						if(artWorks.getImgList()!=null) {
							for(int i=0;i<artWorks.getImgList().size();i++ ) {
								ArtWorksContent art = new ArtWorksContent();
								art.setId(IdGen.uuid());
								art.setArtWorksId(artWorks.getId());
								art.setContent(artWorks.getImgList().get(i));
								art.setFileType(Global.FILE_TYPE_IMG);
								art.setSort(i);
								artWorksDao.insertArtWorkContent(art);
							}
						}
						break;
					case "1"://视频
						//插入认证图片
						if(artWorks.getVideoList()!=null) {
							for(int i=0;i<artWorks.getVideoList().size();i++ ) {
								ArtWorksContent art = new ArtWorksContent();
								art.setId(IdGen.uuid());
								art.setArtWorksId(artWorks.getId());
								art.setContent(artWorks.getVideoList().get(i));
								art.setFileType(Global.FILE_TYPE_VEDIO);
								art.setSort(i);
								artWorksDao.insertArtWorkContent(art);
							}
						}
						break;
					case "2"://纯文
						break;
					default:
						break;
					}
					//最后插入内容
					ArtWorksContent art = new ArtWorksContent();
					art.setId(IdGen.uuid());
					art.setArtWorksId(artWorks.getId());
					art.setContent(artWorks.getTextContent());
					art.setFileType(Global.FILE_TYPE_TEXT);
					art.setSort(10);//限制文件最多上传9个,取10,排在最后
					artWorksDao.insertArtWorkContent(art);
				}
				return true;
			}
			
			
		}
		
		return false;
	}
	
	
	/**
	 * 个人中心 获取作品数、收藏、关注
	 */
	public MineWorksAndInterest getMineCountInfo(String openId) {
		
		List<Integer> list = artWorksDao.getMineCountInfo(openId);
		if(list!=null && list.size()==5) {
			return new MineWorksAndInterest(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4));
		}
		return new MineWorksAndInterest();
	}
	
	
	/**
	 * 注册
	 */
	@Transactional(readOnly = false)
	public int registerXcx(WxUser wxUser) {
		wxUser.setId(IdGen.uuid());
		wxUser.setCreateDate(new Date());
		wxUser.setUpdateDate(new Date());
		return userDao.registerXcx(wxUser);
	}
	
	/**
	 * 获取我的作品列表
	 */
	public List<MineArtWorks> getMineArtWorksList(MineArtWorks mineArtWorks){
		return artWorksDao.getMineArtWorksList(mineArtWorks);
	}
}
