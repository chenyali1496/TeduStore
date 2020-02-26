package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

public interface CartMapper {

	/**
	 * ��������
	 * @param cart
	 * @return
	 */
	Integer insert(Cart cart);
	

	/**
	 * ��ѯ
	 * @param uid
	 * @param productId
	 * @return
	 */
	Cart findCartByUidAndProductId(
			@Param("uid") Integer uid,
			@Param("productId") String productId);
	

	/**
	 * �޸�����
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
  * ����id��ѯ���ﳵ����
  * @param id
  * @return
  */
 Cart findCartById(Integer id);
}
