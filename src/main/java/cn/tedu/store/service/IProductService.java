package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Product;

public interface IProductService {
	
	/**
	 * ����id��ѯ��Ʒ����
	 * @param id 
	 * @return ƥ�����Ʒ���ݣ����û��ƥ�䣬�򷵻�null
	 */
	Product getProductById(String id);
	/**
	 * ��ȡ������Ʒ�б�
	 * @return �ȶ���ߵ�4����Ʒ���б�
	 */
	List<Product> getHotProductList();
}
