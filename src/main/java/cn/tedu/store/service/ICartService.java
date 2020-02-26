package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

public interface ICartService {

  void 	addToCart(Cart cart);
  /**
   * 查询购物车列表
   * @param uid
   * @return
   */
  List<CartVO> getCartListByUid(Integer uid);
  
  /**
   * 商品数量+1
   * @param id
   */
  void addNum(Integer id);
  
  /**
   * 将商品数量-1
   * @param id
   */
  void reduceNum(Integer id);
}
