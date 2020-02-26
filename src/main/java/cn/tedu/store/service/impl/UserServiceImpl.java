package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertDataException;
import cn.tedu.store.service.ex.PasswordFormatException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateDataException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UsernameFormatException;
import cn.tedu.store.util.TextValidator;

@Service("userService")
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper userMapper;

	public User reg(User user) 
			throws UsernameConflictException,
			InsertDataException {
		//��֤��֤�û���������ĸ�ʽ�Ƿ���ȷ
		if(!TextValidator.checkUsername(user.getUsername())) {
			throw new UsernameFormatException("����ע����û���("+user.getUsername()+")��ʽ����ȷ");
		}
		if(!TextValidator.checkPassword(user.getPassword())) {
			throw new PasswordFormatException("����ע��������ʽ����ȷ");
		}
		
		//���ݳ���ע����û�����ѯ�û�����
		User data=findUserByUsername(user.getUsername());
		//�ж��Ƿ��ѯ������
		if(data!=null) {
			//�ǣ���ѯ�����ݣ����û�����ռ�ã����׳�UsernameConflictException�쳣
			throw new UsernameConflictException(
					"����ע����û���("+user.getUsername()+")�Ѿ���ռ��");
		}else {
			//��û�в�ѯ�����ݣ����û���û�б�ռ�ã���ִ�в����û����ݣ���ȡ����ֵ
			User result=insert(user);
			return result;
		}

	}
	public User login(String username, String password) 
			throws UserNotFoundException, PasswordNotMatchException {
		//��֤���ݸ�ʽ
		if(!TextValidator.checkUsername(username)) {
			throw new UsernameFormatException("���Ե�½���û���("+username+")��ʽ����ȷ");
		}
		if(!TextValidator.checkPassword(password)) {
			throw new PasswordFormatException("���Ե�½�������ʽ����ȷ");
		}
		User result=findUserByUsername(username);
		//�ж��Ƿ��ѯ������
		if(result!=null) {
			//�ǣ���ѯ���û���ƥ������ݣ���ȡ��ֵ
			String salt=result.getSalt();
			//���ڲ�����������ֵ���м���
			password=getEncrpytedPassword(password,salt);
			//�жϼ��ܽ�����û������е������Ƿ�ƥ��
			if(password.equals(result.getPassword())) {
				//�ǣ������û�����
				result.setPassword(null);
				result.setSalt(null);
				return result;
			}else {
				//�����벻��ȷ���׳�PasswordNotMatchException�쳣
				throw new PasswordNotMatchException("���벻��ȷ");
			}
		}else {
			//��:û�����û���ƥ������ݣ����׳�UserNotFoundException�쳣
			throw new UserNotFoundException("���Ե�¼���û���("+username+")������");
		}

	}
	public Void changePassword(
			Integer id, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException,
			PasswordFormatException,UpdateDataException {
		//��֤���ݸ�ʽ
				if(!TextValidator.checkPassword(oldPassword)) {
					throw new UsernameFormatException("ԭʼ�����ʽ����ȷ��");
				}
				if(!TextValidator.checkPassword(newPassword)) {
					throw new PasswordFormatException("�������ʽ����ȷ��");
				}
		
		
		//����id ��ѯ�û�����
		User user=findUserById(id);
			//�ж��û������Ƿ����(�����û���¼�����ݱ�ɾ��)	
		if(user!=null) {
			//�ǣ��û����ݴ��ڣ���ȡ��ֵ
			String salt=user.getSalt();
			//��oldPassword����
			String oldMd5Password=getEncrpytedPassword(oldPassword,salt);
			//�����ܺ�����룬��ղŲ�ѯ����е�����Ա�
			if(oldMd5Password.equals(user.getPassword())) {
				//�ǣ�������ֵ��newPassword����
				String newMd5Password=getEncrpytedPassword(newPassword,salt);
				// ��������
				updatePassword(id,newMd5Password);
			}else {
				//��ԭ��������׳�PasswordNotMatchException
				throw new PasswordNotMatchException("ԭʼ�������");
			}
		}else {
			//���û����ݲ����ڣ��׳�UserNotFoundException
			throw new UserNotFoundException("���Է��ʵ��û����ݲ����ڣ������Ѿ���ɾ����");
		}
		return null;
	}


	public void changeInfo(User user) {
		//�ж��û�id�Ƿ����
		if(user.getId()==null) {
			throw new UpdateDataException("�����û�����ʧ�ܣ�ȱ�ٱ�Ҫ�������û�id��");
		}
		//����������ݵĸ�ʽ
		
		//�ж��û������Ƿ���������ݱ���
		User data=findUserById(user.getId());
		if(data==null) {
			throw new UserNotFoundException("���Է��ʵ��û����ݲ����ڣ�");
		}
		//��ȫ��Ҫ���µ�����
		user.setModifiedUser(data.getUsername());
		user.setModifiedTime(new Date());
		//ִ�и���
		updateInfo(user);
	}
	
	public void changeAvatar(Integer id, String avatar) 
			throws  UserNotFoundException,UpdateDataException{
		if(findUserById(id)==null) {
			throw new UserNotFoundException("�ϴ�ͷ��ʧ�ܣ��û����ݲ�����");
		}
		updateAvatar(id,avatar);
	}
	

	/**
	 * �����û�����ѯ�û�����
	 * @param username �û���
	 * @return ƥ����û����ݣ����û��ƥ������ݣ����׳��쳣
	 */
	private User findUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}
	/**
	 * ����id��ѯ�û�����
	 * @param �û� id
	 * @return ƥ����û����ݣ����û��ƥ������ݣ����׳��쳣
	 */
	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}
	/**
	 * �����û�����
	 * @param user �û�����
	 * @return �ɹ�������û�����
	 */
	private User insert(User user) {
		//�ڲ���user�з�װ��Щ�����ⲿ�ṩ������:
		//1.�����漴�Σ�����װ��user��
		String salt=UUID.randomUUID().toString().toUpperCase();
		user.setSalt(salt);
		//2.ȡ��ԭ����ִ�м��ܣ�����װ��user��
		String oldPassword=user.getPassword();
		String password=getEncrpytedPassword(oldPassword,salt);
		user.setPassword(password);
		//3.����isDeleteΪ0
		user.setIsDelete(0);
		//4.��־��4��
		Date now=new Date();
		user.setCreatedTime(now);
		user.setModifiedTime(now);
		user.setCreatedUser("[System]");
		user.setModifiedUser("[System]");
		//�����û�����
		Integer rows=userMapper.insert(user);
		if(rows==1) {
			return user;
		}else {
			throw new InsertDataException("�����û�����ʱ����δ֪��������ϵϵͳ����Ա");
		}
	}
	/**
 * ��ȡ���ܺ������
	 * @param password ����ԭ��
	 * @param salt ��ֵ
	 * @return ���ܺ������
	 */
	private String getEncrpytedPassword(String password,String salt) {
		// �����ܹ���
		// 1. ����ԭʼ�����ǿ�ȣ�
		// 2. ʹ��UUID��Ϊ��ֵ����ԭʼ�������������ƴ�ӣ�
		// 3. ѭ������3�Ρ�
		for (int i = 0; i < 3; i++) {
			password = DigestUtils.md5DigestAsHex(
					(salt + password + salt).getBytes())
					.toUpperCase();
		}
		return password;
	}
	/**
	 * ��������
	 * @param id  �û�id
	 * @param password  ������
	 * @return ��Ӱ�������
	 */
	private Integer updatePassword(Integer id,String password) {
		Integer rows=userMapper.updatePassword(id, password);
				if(rows==1) {
					return rows;
				}else {
					throw new UpdateDataException("��������ʱ����δ֪��������ϵ����Ա��");
				}
	}
	/**
	 * �����û�����
	 * @param user �û�����
	 */
	private void updateInfo(User user) {
		Integer rows=userMapper.updateInfo(user);
		if(rows!=1) {
			throw new UpdateDataException("��������ʱ����δ֪��������ϵ����Ա��");
		}
	}
	
	private void updateAvatar(Integer id,String avatar) {
		Integer rows=userMapper.updateAvatar(id, avatar);
		if(rows!=1) {
			throw new UpdateDataException("��������ʱ����δ֪��������ϵ����Ա��");
		}
	}
	
	
	
	
	
	
	
	
	
	

}
