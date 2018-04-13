package com.thinkgem.jeesite.common.utils;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class JpgToGifUtils {

	
	
	
	public static void main(String[] args) throws Exception{
		URL pic_0 = new URL("http://p3mjvv81y.bkt.clouddn.com/tmp_3249f4a7ed5c5b5884ed96d74f809201592de3fa82fc533c.mp4?vframe/jpg/offset/0");
		URL pic_1 = new URL("http://p3mjvv81y.bkt.clouddn.com/tmp_3249f4a7ed5c5b5884ed96d74f809201592de3fa82fc533c.mp4?vframe/jpg/offset/1");
		URL pic_2 = new URL("http://p3mjvv81y.bkt.clouddn.com/tmp_3249f4a7ed5c5b5884ed96d74f809201592de3fa82fc533c.mp4?vframe/jpg/offset/2");
		URL pic_3 = new URL("http://p3mjvv81y.bkt.clouddn.com/tmp_3249f4a7ed5c5b5884ed96d74f809201592de3fa82fc533c.mp4?vframe/jpg/offset/3");
		URL[] pic = new URL[4];
		pic[0] = pic_0;
		pic[1] = pic_1;
		pic[2] = pic_2;
		pic[3] = pic_3;
		
		String newPic = "D:\\WORK\\gif\\2.gif";
		int playTime = 400;
        jpgToGif(pic,newPic,playTime);
	}
	
	public synchronized static void jpgToGif(URL pic[], String newPic, int payTime) {
		
		try {
			AnimatedGifEncoder egd = new AnimatedGifEncoder();
			egd.setRepeat(0);
			egd.start(newPic);
			BufferedImage src[] = new BufferedImage[pic.length];
			for(int i=0;i<src.length;i++) {
				egd.setDelay(payTime);//设置播放的延迟时间
				try {
					src[i] = ImageIO.read(pic[i]);// 读入需要播放的jpg文件 
					egd.addFrame(src[i]);//添加到帧中
				} catch (Exception e) {
					System.out.println("第"+i+"张图获取失败");
					break;
				}
			}
			egd.finish();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("jpgToGif Failed:");
		}
	}
}
