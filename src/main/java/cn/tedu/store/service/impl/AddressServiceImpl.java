package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AddressAccessException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteDataException;
import cn.tedu.store.service.ex.InsertDataException;
import cn.tedu.store.service.ex.UpdateDataException;

@Service("addressService")
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private IDistrictService districtService;

	public Address addnew(Address address) {

		//完善数据：recv_district,实例：河北省、石家庄、长安区
		String recvDistrict=getRecvDistrictByCode(
				address.getRecvProvince(),
				address.getRecvCity(),
				address.getRecvArea());
		address.setRecvDistrict(recvDistrict);



		//完善数据：is_default
		//第一次增加的是默认--1，否则不默认--0
		Integer count=getCountByUid(address.getUid());
		address.setIsDefault(count==0?1:0);


		//执行插入数据，获取返回值
		Address result= insert(address);
		return result;	
	}

	public List<Address> getList(Integer uid) {
		return addressMapper.getList(uid);
	}
	@Transactional
	public void setDefaultAddress(Integer id, Integer uid) throws
	AddressNotFoundException,UpdateDataException{
		//1检查数据是否归属用户
		//可能抛出：AddressNotFoundException
		Address address=findAddressById(id);
		if(address==null) {
			throw new AddressNotFoundException("尝试访问的收货地址数据不存在！");
		}
		if(!address.getUid().equals(uid)) {
			throw new AddressAccessException("尝试访问的收货地址数据归属错误！");
		}
		//2将该用户的所有地址设置非默认
		//可能抛出：UpdateDataException
		setNonDefault(uid);
		//3将指定id的地址设置为默认
		//可能抛出：UpdateDataException
		setDefault(id);

	}

	// 根据id删除收获地址
	@Transactional
	public void delete(Integer id, Integer uid)throws
	AddressNotFoundException,AddressAccessException,DeleteDataException {
		//根据id查询数据
		Address data=findAddressById(id);
		//检查数据是否存在
		if(data!=null) {
			//存在：检查数据的uid归属
			if(data.getUid().equals(uid)) {
				//归属正常：执行删除
				deleteById(id);
				//判断刚才删除的地址是否是默认
				if(data.getIsDefault()==1) {
					//是：当前还有灭有收货地址(数量多少)
					Integer count=getCountByUid(uid);
					if(count>0) {
						//不为0：将id最大的数据设置为默认地址
						Integer maxId=getMaxId(uid);
						setDefault(maxId);
					}
					//为0：不需要执行任何任务
				}
				//否：不需要执行任何任务
			}else {
				//归属错误：抛出AddressAccessException
				throw new AddressAccessException("尝试删除的收货地址数据归属错误！");
			}
		}else {
			//不存在：抛出异常AddressNotFoundException
			throw new AddressNotFoundException("尝试删除的收货地址数据不存在！");
		}

	}

	/**
	 * 根据id删除收获地址
	 * @param id
	 * @return 受影响的行数
	 */
	private void deleteById(Integer id) {
		Integer rows=addressMapper.deleteById(id);
		if(rows!=1) {
			throw new DeleteDataException("删除收货地址时出现未知错误，请联系系统管理员！ ");
		}
	}

	/**
	 * 获取收货地址中id最大的那一条数据
	 * @param uid
	 * @return 受影响的行数
	 */
	private Integer getMaxId(Integer uid) {
		return addressMapper.getMaxId(uid);
	}

	/**
	 * 将用户的所有收获地址设置为非默认收货地址
	 * @param uid
	 */
	private void setNonDefault(Integer uid) {
		Integer rows=addressMapper.setNonDefault(uid);
		if(rows<1) {
			throw new UpdateDataException("更新收货地址时出现未知错误，请联系系统管理员！ ");
		}
	}


	private void setDefault(Integer id) {
		Integer rows=addressMapper.setDefault(id);
		if(rows<1) {
			throw new UpdateDataException("更新收货地址时出现未知错误，请联系系统管理员！ ");
		}
	}
	/**
	 * 查询用户地址
	 * @return
	 */
	private Address findAddressById(Integer id) {
		return addressMapper.findAddressById(id);
	}

	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 * @return 成功插入的收货地址数据，包括数据id
	 */
	private Address insert(Address address) {
		Integer rows=addressMapper.insert(address);
		if(rows!=1) {
			throw new InsertDataException("插入收获地址数据时出现未知错误，请联系系统管理员！");
		}else {
			return  address;
		}
	}
	/**
	 * 根据用户id获取该用户的收获地址数据的数量
	 * @param uid
	 * @return 
	 */
	private Integer getCountByUid(Integer uid) {
		return addressMapper.getCountByUid(uid);
	}

	/**
	 * 获取收货地址的省市区
	 * @param provinceCode
	 * @param cityCode
	 * @param areaCode
	 * @return 收货地址的省市区，例如河北省，石家庄市，长安区
	 */
	private String getRecvDistrictByCode(
			String provinceCode,
			String cityCode,
			String areaCode) {
		District province=districtService.getDistrictByCode(provinceCode);
		District city=districtService.getDistrictByCode(cityCode);
		District area=districtService.getDistrictByCode(areaCode);

		StringBuffer str=new StringBuffer();
		str.append(province==null ?"Null":province.getName());
		str.append(", ");
		str.append(city==null ?"Null":city.getName());
		str.append(", ");
		str.append(area==null ?"Null":area.getName());

		return str.toString();
	}






}
