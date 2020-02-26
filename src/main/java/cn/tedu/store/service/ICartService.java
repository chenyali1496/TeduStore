package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

public interface ICartService {

  void 	addToCart(Cart cart);
  /**
   * ��ѯ���ﳵ�б�
   * @param uid
   * @return
   */
  List<CartVO> getCartListByUid(Integer uid);
  
  /**
   * ��Ʒ����+1
   * @param id
   */
  void addNum(Integer id);
  
  /**
   * ����Ʒ����-1
   * @param id
   */
  void reduceNum(Integer id);
}
