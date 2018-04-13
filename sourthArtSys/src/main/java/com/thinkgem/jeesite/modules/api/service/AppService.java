package com.thinkgem.jeesite.modules.api.service;

import java.util.ArrayList;
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
import com.thinkgem.jeesite.modules.api.dao.AppDao;
import com.thinkgem.jeesite.modules.api.entity.ArtVo;
import com.thinkgem.jeesite.modules.api.entity.ArtWorksIndexPage;
import com.thinkgem.jeesite.modules.api.entity.ArtWorksVo;
import com.thinkgem.jeesite.modules.api.entity.CommentVo;
import com.thinkgem.jeesite.modules.api.entity.MineArtWorks;
import com.thinkgem.jeesite.modules.api.entity.MineWorksAndInterest;
import com.thinkgem.jeesite.modules.api.entity.MsgCode;
import com.thinkgem.jeesite.modules.api.entity.NewsInfoVo;
import com.thinkgem.jeesite.modules.api.entity.NewsVo;
import com.thinkgem.jeesite.modules.api.entity.UserDzVo;
import com.thinkgem.jeesite.modules.api.entity.WxUser;
import com.thinkgem.jeesite.modules.art.dao.ArtAuthDao;
import com.thinkgem.jeesite.modules.art.dao.ArtWorksCollectDao;
import com.thinkgem.jeesite.modules.art.dao.ArtWorksCommentDao;
import com.thinkgem.jeesite.modules.art.dao.ArtWorksDao;
import com.thinkgem.jeesite.modules.art.entity.ArtAuth;
import com.thinkgem.jeesite.modules.art.entity.ArtAuthImg;
import com.thinkgem.jeesite.modules.art.entity.ArtWorks;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksCollect;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksContent;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

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
	
	@Autowired
	private AppDao appDao;
	
	@Autowired
	private ArtWorksCommentDao artWorksCommentDao;
	
	@Autowired
	private ArtWorksCollectDao collectDao;
	
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
	@Transactional(readOnly = false)
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
			artAuth.setStatus("1");//强制设置为'0'-待审核状态 测试阶段 设置为'1'-已审核
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
			//如果初次认证，则初始化用户登录名和密码，默认两者都为用户手机号
			if(StringUtils.isBlank(user.getLoginName())) {
				user.setLoginName(artAuth.getPhone());
				user.setPassword(SystemService.entryptPassword(artAuth.getPhone()));
				userDao.initUserLoginNameAndPassword(user);
				//先删除再初始化角色
				userDao.deleteUserRole(user);
				userDao.initUserRole(user.getId(), Global.USER_ROLE_ID);
			}
			
			//测试阶段默认认证通过，同步用户表中artLevel和artType
			user.setArtLevel(artAuth.getArtLevel());
			user.setArtType(artAuth.getArtType());
			userDao.updateUserArtLevelAndType(user);
			
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
				artWorks.setStatus("1");//强制设置为'0'-待审核状态 测试阶段默认不需要审核
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
	
	/**
	 * 作废未使用的短信验证码
	 */
	@Transactional(readOnly = false)
	public void toVoidMsgCode(String phone) {
		appDao.toVoidMsgCode(phone);
	}
	
	/**
	 * 插入新短信验证码
	 */
	@Transactional(readOnly = false)
	public int insertMsgCode(MsgCode msgCode) {
		return appDao.insertMsgCode(msgCode);
	}
	
	
	/**
	 * 根据ID获取作品信息
	 */
	public ArtWorksVo geArtWorksDetailById(String id,String openId) {
		//获取作品内容
		ArtWorksVo artWorks = artWorksDao.geArtWorksDetailById(id,openId);
		//获取作者信息
		if(artWorks!=null && artWorks.getUser()!=null && StringUtils.isNotBlank(artWorks.getUser().getId())) {
			artWorks.setUser(userDao.getUserInfoAndArtLevel(artWorks.getUser().getId()));
		}
		//获取图片+视频+文字
		List<ArtWorksContent> cList = artWorksDao.getArtWorksContentListByWorksId(id);
		List<String> imgList = new ArrayList<String>();
		List<String> videoList = new ArrayList<String>();
		for(ArtWorksContent awc : cList) {
			switch (awc.getFileType()) {
			case "0"://图片
				imgList.add(awc.getContent());
				break;
			case "1"://视频
				videoList.add(awc.getContent());
				break;
			case "2"://文字
				artWorks.setTextContent(awc.getContent());
				break;
			default:
				break;
			}
		}
		artWorks.setImgList(imgList);
		artWorks.setVideoList(videoList);
		//获取评论
		List<CommentVo> commentList = artWorksDao.getArtWorksCommentList(id);
		artWorks.setCommentList(commentList);
//		List<CommentVo> artCommmentList = new ArrayList<CommentVo>();
//		List<CommentVo> commonCommmentList = new ArrayList<CommentVo>();
//		for(CommentVo v : commentList) {
//			if(StringUtils.equals("1", v.getMsgType())) {//艺术家评论
//				artCommmentList.add(v);
//			}else {
//				commonCommmentList.add(v);
//			}
//		}
//		artWorks.setArtCommmentList(artCommmentList);
//		artWorks.setCommonCommmentList(commonCommmentList);
		return artWorks;
	}
	
	
	/**
	 * 保存评论
	 */
	@Transactional(readOnly = false)
	public int saveComment(CommentVo commentVo) {
		User user = userDao.getByOpenId(commentVo.getOpenId(),commentVo.getOrgId());
		if(user!=null) {
			commentVo.setUserId(user.getId());
			commentVo.setId(IdGen.uuid());
			commentVo.setCreateDate(new Date());
			return artWorksCommentDao.saveComment(commentVo);
		}else {
			return -1;
		}
	}

	/**
	 * 保存或取消收藏
	 */
	@Transactional(readOnly = false)
	public boolean addCollectArtWorks(ArtWorksCollect artWorksCollect) {
		if(StringUtils.isNotBlank(artWorksCollect.getOpenId()) && StringUtils.isNotBlank(artWorksCollect.getOrgId())
				&& StringUtils.isNotBlank(artWorksCollect.getArtWorksId())) {
			
			User user = userDao.getByOpenId(artWorksCollect.getOpenId(),artWorksCollect.getOrgId());
			artWorksCollect.setUser(user);
		
			if (artWorksCollect.getType() == 0) {
				collectDao.deleteCollection(artWorksCollect);
			} else {
				artWorksCollect.preInsert();
				ArtWorksCollect collect = collectDao.findByUserIdAndArtId(artWorksCollect);
				if (collect == null) {
					collectDao.insertCollection(artWorksCollect);
				}
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * 获取我的收藏列表
	 */
	public List<MineArtWorks> getMyCollectList(String openId, String orgId){
		ArtWorksCollect artWorksCollect = new ArtWorksCollect();
		artWorksCollect.setOpenId(openId);
		artWorksCollect.setOrgId(orgId);
		User user = userDao.getByOpenId(artWorksCollect.getOpenId(),artWorksCollect.getOrgId());
		artWorksCollect.setUser(user);
		return collectDao.getCollectListByUserId(artWorksCollect);
	}
	
	/**
	 * 保存或取消评论
	 */
	@Transactional(readOnly = false)
	public boolean targetDz(UserDzVo userDzVo) {
		if(StringUtils.isNotBlank(userDzVo.getOpenId()) && StringUtils.isNotBlank(userDzVo.getOrgId())
				&& StringUtils.isNotBlank(userDzVo.getTargetId())) {
			
			User user = userDao.getByOpenId(userDzVo.getOpenId(),userDzVo.getOrgId());
			userDzVo.setUserId(user.getId());
			if (StringUtils.equals("1", userDzVo.getDelFlag())) {//取消点赞
				if(StringUtils.equals("0", userDzVo.getType())) {//0-作品 1-评论
					appDao.deleteArtWorksDZ(userDzVo);
				}else {
					appDao.deleteCommentDZ(userDzVo);
				}
			} else {
				userDzVo.setId(IdGen.uuid());
				userDzVo.setCreateDate(new Date());
				if(StringUtils.equals("0", userDzVo.getType())) {//0-作品 1-评论
					appDao.insertArtWorksDZ(userDzVo);
				}else {
					appDao.insertCommentDZ(userDzVo);
				}
			}
			//修改作品或评论的点赞总数
			if(StringUtils.equals("0", userDzVo.getType())) {//0-作品 1-评论
				appDao.updateArtWorksDZ(userDzVo);
			}else {
				appDao.updateCommentDZ(userDzVo);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 首页获取作品列表
	 */
	public List<ArtWorksIndexPage> getIndexPage(ArtWorksIndexPage artWorksIndexPage){
		return artWorksDao.getIndexPage(artWorksIndexPage);
	}
	
	/**
	 * 艺术名片
	 */
	public List<ArtVo> getArtList(String searchParam){
		return appDao.getArtList(searchParam);
	}
	
	
	/**
	 * 艺术家信息
	 */
	public ArtVo getArtInfo(String id){
		return appDao.getArtInfo(id);
	}
	
	
	/**
	 * 获取资讯
	 */
	public List<NewsVo> getNewsList(){
		return appDao.getNewsList();
	}
	
	public NewsVo getNewsById(String id){
		return appDao.getNewsById(id);
	}
	
	
	/**
	 * 获取资讯详情
	 */
	public List<NewsInfoVo> getNewsInfo(String newsId){
		return appDao.getNewsInfo(newsId);
	}
	
}
