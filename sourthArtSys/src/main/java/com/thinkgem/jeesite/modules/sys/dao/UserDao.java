package com.thinkgem.jeesite.modules.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.entity.UserVo;
import com.thinkgem.jeesite.modules.api.entity.WxUser;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 用户DAO接口
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);

	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);

	/**
	 * 根据openId查询用户
	 * @param openId
	 * @return
	 */
	public User getByOpenId(@Param("openId")String openId,@Param("orgId")String orgId);
	
	/**
	 * 根据openID更新用户昵称+头像
	 * @param unionId
	 * @return
	 */
	int updateUserByOpenId(WxUser wxUser);
	
	/**
	 * 微信小程序游客注册
	 */
	int registerXcx(WxUser wxUser);
	
	/**
	 * 更新用户名和初始化密码
	 */
	int initUserLoginNameAndPassword(User user);
	
	/**
	 * 初始绑定角色
	 */
	int initUserRole(@Param("id")String id,@Param("roleId")String roleId);
	
	/**
	 * 根据ID获取用户信息及艺术信息
	 */
	UserVo getUserInfoAndArtLevel(@Param("id")String id);
}
