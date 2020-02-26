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
	 * �û�ע��
	 * @param user �û�����
	 * @return  �ɹ�ע����û����ݣ��Ұ����û���id 
	 * @throws UsernameConflictException ע����û����Ѿ����ڵ��쳣
	 * @throws InsertDataException �������ݴ���
	 */
	User reg(User user) 
			throws UsernameConflictException,InsertDataException,
			UsernameFormatException,PasswordFormatException;
	/**
	 * �û���¼
	 * @param username �û���
	 * @param password �û�����
	 * @return �û�������
	 * @throws UserNotFoundException �û����Ҳ���
	 * @throws PasswordNotMatchException �û��������
	 */
	User login(String username,String password) 
			throws UserNotFoundException,PasswordNotMatchException,
			UsernameFormatException,PasswordFormatException;
	/**
	 * �޸��û�����
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return	
	 * @throws UserNotFoundException �û����ݲ�����
	 * @throws PasswordNotMatchException ԭ���벻ƥ��
	 * @throws PasswordFormatException �����ʽ����
	 * @throws UpdateDataException �������ݴ���
	 */
	Void changePassword(Integer id,String oldPassword,String newPassword)
			throws UserNotFoundException,PasswordNotMatchException,
			PasswordFormatException,UpdateDataException;
	/**
	 * �޸��û�����
	 * @param user
	 * @throws UpdateDataException ���´���
	 * @throws UserNotFoundException �û����ݲ�����
	 */
	void changeInfo(User user) 
			throws UpdateDataException,UserNotFoundException;
	
	/**
	 * ����id��ѯ�û���Ϣ
	 * @param id
	 * @return �û���Ϣ
	 */
	 User findUserById(Integer id); 
	 /**
	  * �����û�ͷ��
	  * @param id
	  * @param avatar 
	  */
	 void changeAvatar(Integer id, String avatar)
			 throws  UserNotFoundException,UpdateDataException;
	 
	 
}
