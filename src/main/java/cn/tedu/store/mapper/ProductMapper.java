package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Product;

public interface ProductMapper {
	
	/**
	 * ����id��ѯ��Ʒ����
	 * @param id ��Ʒ��id
	 * @return ƥ�����Ʒ���ݣ����û��ƥ������ݣ��򷵻�null
	 */
	Product findProductById(String id);
	/**
	 * ��ȡ��Ʒ�б�
	 * @param where ��ѯ����е�where�Ӿ䣬������where�ؼ���
	 * @param orderBy ��ѯ����е�orderby�Ӿ䣬������order by�ؼ���
	 * @param offset ��ѯʱ�������еĲ�ѯ����У�����ǰ�������ٿ�ʼ��ȡ����
	 * @param count ����ѯ����������
	 * @return ��Ʒ�б�
	 */
	List<Product> getList(
			@Param("where") String where,
			@Param("orderBy") String orderBy,
			@Param("offset") Integer offset,
			@Param("count") Integer count
			);
}
