package com.thinkgem.jeesite.modules.api.task;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.JpgToGifUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksContent;
import com.thinkgem.jeesite.modules.art.service.ArtWorksService;

/**
 * 系统定时任务
 */
//@Service
//@Lazy(false)
public class SysTaskJob {

	
	@Autowired
	private ArtWorksService artWorksService;
	
	
	/**
	 * 将上传的视频，截取前几帧图片，生成GIF
	 */
//	@Scheduled(cron="0/50 * * * * ?")
	public void jpgToGifJob() {
		System.out.println("--------------将上传的视频，截取前几帧图片，生成GIF--------------------"+DateUtils.getDate("yyyy/MM/dd HH:mm:ss"));
		
		String path = this.getClass().getResource("").getPath();
		int end = path.indexOf("sourthArtSys");
		if(end!=-1) {
			String gifPath =  path.substring(0, end)+"/gif";
			File file = new File(gifPath);
			if(!file.exists()){ 
				file.mkdirs(); 
			} 
			//TODO 获取视频
			List<ArtWorksContent> list =  artWorksService.getArtWorksContentListToGif();
			
			//生成gif到磁盘
			for(ArtWorksContent c : list) {
				if(StringUtils.isNotBlank(c.getContent())) {
					//默认截取10张
					URL[] pic = new URL[6];
					for(int i=0;i<6;i++) {
						try {
							pic[i] = new URL(c.getContent()+"?vframe/jpg/offset/"+i);
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}
					String newPic = gifPath+"/"+c.getId()+".gif";
					JpgToGifUtils.jpgToGif(pic, newPic, 400);
					
					//gif文件磁盘地址
					c.setGifUrl(newPic);
				}
			}
			
			//将磁盘gif上传七牛同步更新数据库
			artWorksService.updateArtWorksContentGifUrl(list);
		}
		
	}
}
