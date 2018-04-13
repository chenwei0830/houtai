package com.thinkgem.jeesite.modules.art.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.QiNiuUtils;
import com.thinkgem.jeesite.modules.api.entity.AppJson;
import com.thinkgem.jeesite.modules.art.dao.ArtWorksDao;
import com.thinkgem.jeesite.modules.art.entity.ArtWorks;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksContent;

/**
 * 艺术作品Service
 * @author LM
 */
@Service
@Transactional(readOnly = true)
public class ArtWorksService extends CrudService<ArtWorksDao, ArtWorks>{

	/**
	 * 获取未生成gif的视频,每次获取10条
	 */
	public List<ArtWorksContent> getArtWorksContentListToGif(){
		return dao.getArtWorksContentListToGif();
	}
	
	/**
	 * 更新视频gif
	 */
	@Transactional(readOnly = false)
	public void updateArtWorksContentGifUrl(List<ArtWorksContent> list) {
		
		for(ArtWorksContent c : list) {
			File file = new File(c.getGifUrl());
			UploadManager uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
			Response resp = null;
			try {
				resp = uploadManager.put(file, c.getId(), QiNiuUtils.getUpToken());
				//解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(resp.bodyString(), DefaultPutRet.class);
				c.setGifUrl(QiNiuUtils.QN_URL+"/"+putRet.key);
				dao.updateArtWorksContentGifUrl(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
