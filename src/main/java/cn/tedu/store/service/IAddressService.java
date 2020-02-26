package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.AddressAccessException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteDataException;
import cn.tedu.store.service.ex.UpdateDataException;
/**
 * 收货地址的数据业务层
 * @author Administrator
 *
 */
public interface IAddressService {
	/**
	 * 增加新的收获地址
	 * @param address 收货地址数据
	 * @return 成功增加的收获地址数据，包括数据id
	 */
	Address	addnew(Address address);
	
	/**
	 * 获取某用户的收货地址列表
	 * @param uid
	 * @return 收货地址列表，如果没有匹配的数据，则返回无元素的空列表
	 */
	List<Address> getList(Integer uid);
	
	/**
	 * 设置默认收货地址
	 * @param id
	 * @param uid
	 */
	void setDefaultAddress(Integer id,Integer uid) throws
		AddressNotFoundException,UpdateDataException;
	
	/**
	 * 根据id删除收获地址
	 * @param id
	 * @return 受影响的行数
	 */
	void delete(Integer id,Integer uid)throws AddressNotFoundException,
	AddressAccessException,DeleteDataException;
	
}
