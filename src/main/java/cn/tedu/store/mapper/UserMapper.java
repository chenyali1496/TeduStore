package cn.tedu.store.mapper;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

public interface UserMapper {
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer insert(User user);
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 用户数据
	 */
	User findUserByUsername(String username);
	/**
	 * 根据用户id查询用户信息
	 * @param id 
	 * @return 用户信息
	 */
	User findUserById(Integer id);
	/**
	 * 根据用户id修改用户密码
	 * @param id 
	 * @param password
	 * @return 受影响的行数
	 */
	Integer updatePassword(
			@Param("id") Integer id,
			@Param("password") String password);
	/**
	 * 更新用户数据
	 * @param user 用户信息
	 * @return 受影响的行数
	 */
	Integer updateInfo(User user);
	
	/**
	 * 更新用户头像
	 * @param id
	 * @param avatar 用户头像在服务器端的路径
	 * @return	 受影响的行数
	 */
	Integer updateAvatar(
		    @Param("id") Integer id, 
		    @Param("avatar") String avatar);
}