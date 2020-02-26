package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

public interface CartMapper {

	/**
	 * 插入数据
	 * @param cart
	 * @return
	 */
	Integer insert(Cart cart);
	

	/**
	 * 查询
	 * @param uid
	 * @param productId
	 * @return
	 */
	Cart findCartByUidAndProductId(
			@Param("uid") Integer uid,
			@Param("productId") String productId);
	

	/**
	 * 修改数量
	 * @param id
	 * @param productNum
	 * @return
	 */
	Integer updateProductNum(
			@Param("id") Integer id,
			@Param("productNum") Integer productNum);
	/**
	 * 
	 * @param uid
	 * @return
	 */
 List<CartVO>	getList(Integer uid);
	
 /**
  * 根据id查询购物车数据
  * @param id
  * @return
  */
 Cart findCartById(Integer id);
}
