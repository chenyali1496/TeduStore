package cn.tedu.store.mapper;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

public interface UserMapper {
	/**
	 * �����û�����
	 * @param user �û�����
	 * @return ��Ӱ�������
	 */
	Integer insert(User user);
	/**
	 * �����û�����ѯ�û�����
	 * @param username �û���
	 * @return �û�����
	 */
	User findUserByUsername(String username);
	/**
	 * �����û�id��ѯ�û���Ϣ
	 * @param id 
	 * @return �û���Ϣ
	 */
	User findUserById(Integer id);
	/**
	 * �����û�id�޸��û�����
	 * @param id 
	 * @param password
	 * @return ��Ӱ�������
	 */
	Integer updatePassword(
			@Param("id") Integer id,
			@Param("password") String password);
	/**
	 * �����û�����
	 * @param user �û���Ϣ
	 * @return ��Ӱ�������
	 */
	Integer updateInfo(User user);
	
	/**
	 * �����û�ͷ��
	 * @param id
	 * @param avatar �û�ͷ���ڷ������˵�·��
	 * @return	 ��Ӱ�������
	 */
	Integer updateAvatar(
		    @Param("id") Integer id, 
		    @Param("avatar") String avatar);
}