package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;

/**
 * 省市区的数据业务层
 * @author Administrator
 *
 */
public interface IDistrictService {
	/**
	 * 根据省市区的代号获取详细数据
	 * @param code
	 * @return 省市区的详细数据
	 */
	District getDistrictByCode(String code);
	/**
	 * 获取省的列表/某省的市的列表/某市的区的列表
	 * @param parent 父级单位的代号，如果需要获取
	 * @return 省列表/市列表/区列表
	 */
	List<District>  getList(String parent);
}
