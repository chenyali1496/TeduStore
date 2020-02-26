package cn.tedu.store.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertDataException;
import cn.tedu.store.service.ex.ProductNumLimitException;
import cn.tedu.store.service.ex.UpdateDataException;
import cn.tedu.store.vo.CartVO;

@Service("cartService")
public class CartServiceImpl implements ICartService{

	@Autowired
	private CartMapper cartMapper;
	public void addToCart(Cart cart) {
		//����cart�����з�װ��uid��productIdִ�в�ѯ����
		Cart data=	findCartByUidAndProductId(cart.getUid(),cart.getProductId());
		//�жϽ�����Ƿ�Ϊnull
		if(data==null){
			//�ǣ����û���ǰû����Ӹ���Ʒ����ִ����Ӳ���
			insert(cart);
		}else {
					//�񣺸��û��Ѿ���Ӹ���Ʒ,��ȡԭ�е�����
			Integer n=data.getProductNum();
					//��cart�����л�ȡ�˴ε����ӣ�������õ��µ�����
			Integer newProductNum=cart.getProductNum()+n;
					//������Ʒ����
			updateProductNum(data.getId(),newProductNum);
		}

	}

	public List<CartVO> getCartListByUid(Integer uid) {
		return getList(uid);
	}
	
	public void addNum(Integer id) {
			Cart cart=findCartById(id);
			if(cart==null) {
				throw new CartNotFoundException("���Է��ʵĹ��ﳵ���ݲ����ڣ�");
			}
			Integer num=cart.getProductNum()+1;
			updateProductNum(id,num);
		
	}
	
	public void reduceNum(Integer id) {
		Cart cart=findCartById(id);
		if(cart==null) {
			throw new CartNotFoundException("���Է��ʵĹ��ﳵ���ݲ����ڣ�");
		}
		
		if(cart.getProductNum()<=1) {
			throw new ProductNumLimitException("�����޸ĵĹ��ﳵ���ݵ���Ʒ�����������ƣ�");
		}
		Integer num=cart.getProductNum()-1;
		updateProductNum(id,num);
		
	}
	
	/**
	 * ��������
	 * @param cart
	 * @return
	 */
	private Cart insert(Cart cart) {
		Integer rows=cartMapper.insert(cart);
		if(rows==1) {
			return cart;
		}else {
			throw new InsertDataException("���ﳵ�����������ʱ����δ֪��������ϵ����Ա��");
		}
	}


	/**
	 * ��ѯ
	 * @param uid
	 * @param productId
	 * @return
	 */
	private	Cart findCartByUidAndProductId(
			Integer uid, String productId) {
		return cartMapper.findCartByUidAndProductId(uid, productId);
	}


	/**
	 * �޸�����
	 * @param id
	 * @param productNum
	 * @return
	 */
	private	void updateProductNum(Integer id,Integer productNum) {
		Integer rows=cartMapper.updateProductNum(id, productNum);
		if(rows!=1) {
			throw new UpdateDataException("�޸Ĺ��ﳵ����Ʒ����ʱ����δ֪��������ϵ����Ա��");
		}
	}

	
	/**
	 * ��ѯ���ﳵ�б�
	 * @param uid
	 * @return
	 */
	private List<CartVO> getList(Integer uid){
		return cartMapper.getList(uid);
	}

	
	private	Cart findCartById(Integer id) {
		return cartMapper.findCartById(id);
	}

	
}
