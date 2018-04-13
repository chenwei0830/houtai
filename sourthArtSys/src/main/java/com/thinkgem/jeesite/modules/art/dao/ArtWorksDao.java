package com.thinkgem.jeesite.modules.art.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.entity.ArtWorksIndexPage;
import com.thinkgem.jeesite.modules.api.entity.ArtWorksVo;
import com.thinkgem.jeesite.modules.api.entity.CommentVo;
import com.thinkgem.jeesite.modules.api.entity.MineArtWorks;
import com.thinkgem.jeesite.modules.art.entity.ArtWorks;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksContent;

/**
 * 艺术作品DAO接口
 * @author LM
 */
@MyBatisDao
public interface ArtWorksDao extends CrudDao<ArtWorks>{

	int insertArtWorkContent(ArtWorksContent art);
	
	List<Integer> getMineCountInfo(@Param("openId")String openId);
	
	List<MineArtWorks> getMineArtWorksList(MineArtWorks mineArtWorks);
	
	ArtWorksVo geArtWorksDetailById(@Param("id")String id,@Param("openId")String openId);
	
	List<ArtWorksContent> getArtWorksContentListByWorksId(@Param("artWorksId")String artWorksId);
	
	List<CommentVo> getArtWorksCommentList(@Param("artWorksId")String artWorksId);
	
	List<ArtWorksIndexPage> getIndexPage(ArtWorksIndexPage artWorksIndexPage);
	
	List<ArtWorksContent> getArtWorksContentListToGif();
	
	void updateArtWorksContentGifUrl(ArtWorksContent artWorksContent);
}
