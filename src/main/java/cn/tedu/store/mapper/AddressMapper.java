package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Address;

public interface AddressMapper {
	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer insert(Address address);
	/**
	 * 根据用户id查询有多少条收货地址
	 * @param uid
	 * @return 用户的收货地址的数量
	 */
	Integer getCountByUid(Integer uid);
	
	/**
	 * 获取某用户的收货地址列表
	 * @param uid
	 * @return 收货地址列表，如果没有匹配的数据，则返回无元素的空列表
	 */
	List<Address> getList(Integer uid);
	
	/**
	 * 修改某用户的收货地址全部为非默认地址
	 * @param uid
	 * @return 受影响的行数
	 */
	Integer setNonDefault(Integer uid);
	
	/**
	 * 修改某用户的某个收货地址全部为默认地址
	 * @param id 数据id 
	 * @return 受影响的行数
	 */
	Integer setDefault(Integer id);
	
	/**
	 * 根据地址id查询用户地址信息
	 * @param id 数据id 
	 * @return 返回收货地址数据，若没有数据，则返回null
	 */
	Address findAddressById(Integer id);
	
	/**
	 * 根据id删除收获地址
	 * @param id
	 * @return 受影响的行数
	 */
	Integer deleteById(Integer id);
	
	/**
	 * 获取收货地址中id最大的那一条数据
	 * @param uid
	 * @return 受影响的行数
	 */
	Integer getMaxId(Integer uid);
	
}


