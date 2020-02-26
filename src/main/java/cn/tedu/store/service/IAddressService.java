package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.AddressAccessException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteDataException;
import cn.tedu.store.service.ex.UpdateDataException;
/**
 * �ջ���ַ������ҵ���
 * @author Administrator
 *
 */
public interface IAddressService {
	/**
	 * �����µ��ջ��ַ
	 * @param address �ջ���ַ����
	 * @return �ɹ����ӵ��ջ��ַ���ݣ���������id
	 */
	Address	addnew(Address address);
	
	/**
	 * ��ȡĳ�û����ջ���ַ�б�
	 * @param uid
	 * @return �ջ���ַ�б����û��ƥ������ݣ��򷵻���Ԫ�صĿ��б�
	 */
	List<Address> getList(Integer uid);
	
	/**
	 * ����Ĭ���ջ���ַ
	 * @param id
	 * @param uid
	 */
	void setDefaultAddress(Integer id,Integer uid) throws
		AddressNotFoundException,UpdateDataException;
	
	/**
	 * ����idɾ���ջ��ַ
	 * @param id
	 * @return ��Ӱ�������
	 */
	void delete(Integer id,Integer uid)throws AddressNotFoundException,
	AddressAccessException,DeleteDataException;
	
}
