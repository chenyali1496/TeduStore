package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Address;

public interface AddressMapper {
	/**
	 * �����ջ���ַ����
	 * @param address �ջ���ַ����
	 * @return ��Ӱ�������
	 */
	Integer insert(Address address);
	/**
	 * �����û�id��ѯ�ж������ջ���ַ
	 * @param uid
	 * @return �û����ջ���ַ������
	 */
	Integer getCountByUid(Integer uid);
	
	/**
	 * ��ȡĳ�û����ջ���ַ�б�
	 * @param uid
	 * @return �ջ���ַ�б����û��ƥ������ݣ��򷵻���Ԫ�صĿ��б�
	 */
	List<Address> getList(Integer uid);
	
	/**
	 * �޸�ĳ�û����ջ���ַȫ��Ϊ��Ĭ�ϵ�ַ
	 * @param uid
	 * @return ��Ӱ�������
	 */
	Integer setNonDefault(Integer uid);
	
	/**
	 * �޸�ĳ�û���ĳ���ջ���ַȫ��ΪĬ�ϵ�ַ
	 * @param id ����id 
	 * @return ��Ӱ�������
	 */
	Integer setDefault(Integer id);
	
	/**
	 * ���ݵ�ַid��ѯ�û���ַ��Ϣ
	 * @param id ����id 
	 * @return �����ջ���ַ���ݣ���û�����ݣ��򷵻�null
	 */
	Address findAddressById(Integer id);
	
	/**
	 * ����idɾ���ջ��ַ
	 * @param id
	 * @return ��Ӱ�������
	 */
	Integer deleteById(Integer id);
	
	/**
	 * ��ȡ�ջ���ַ��id������һ������
	 * @param uid
	 * @return ��Ӱ�������
	 */
	Integer getMaxId(Integer uid);
	
}


