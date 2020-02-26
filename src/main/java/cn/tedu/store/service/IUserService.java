package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.InsertDataException;
import cn.tedu.store.service.ex.PasswordFormatException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateDataException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UsernameFormatException;

public interface IUserService {
	/**
	 * 用户注册
	 * @param user 用户数据
	 * @return  成功注册的用户数据，且包含用户的id 
	 * @throws UsernameConflictException 注册的用户名已经存在的异常
	 * @throws InsertDataException 插入数据错误
	 */
	User reg(User user) 
			throws UsernameConflictException,InsertDataException,
			UsernameFormatException,PasswordFormatException;
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 用户的数据
	 * @throws UserNotFoundException 用户名找不到
	 * @throws PasswordNotMatchException 用户密码错误
	 */
	User login(String username,String password) 
			throws UserNotFoundException,PasswordNotMatchException,
			UsernameFormatException,PasswordFormatException;
	/**
	 * 修改用户密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return	
	 * @throws UserNotFoundException 用户数据不存在
	 * @throws PasswordNotMatchException 原密码不匹配
	 * @throws PasswordFormatException 密码格式错误
	 * @throws UpdateDataException 更新数据错误
	 */
	Void changePassword(Integer id,String oldPassword,String newPassword)
			throws UserNotFoundException,PasswordNotMatchException,
			PasswordFormatException,UpdateDataException;
	/**
	 * 修改用户资料
	 * @param user
	 * @throws UpdateDataException 更新错误
	 * @throws UserNotFoundException 用户数据不存在
	 */
	void changeInfo(User user) 
			throws UpdateDataException,UserNotFoundException;
	
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return 用户信息
	 */
	 User findUserById(Integer id); 
	 /**
	  * 跟新用户头像
	  * @param id
	  * @param avatar 
	  */
	 void changeAvatar(Integer id, String avatar)
			 throws  UserNotFoundException,UpdateDataException;
	 
	 
}
