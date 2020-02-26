package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.IProductService;


@Service("productService")
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductMapper productMapper;
	
	public Product getProductById(String id) {
		return findProductById(id);
	}
	
	public List<Product> getHotProductList() {
		return getList(null,"priority DESC",0,4);
	}

	private List<Product> getList(String where,String orderBy,Integer offset,Integer count){
		return productMapper.getList(where, orderBy, offset, count);
	}
	private Product findProductById(String id) {
		return productMapper.findProductById(id);
		
	}

}
