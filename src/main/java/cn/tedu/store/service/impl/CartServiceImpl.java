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
		//根据cart参数中封装的uid和productId执行查询操作
		Cart data=	findCartByUidAndProductId(cart.getUid(),cart.getProductId());
		//判断结果中是否为null
		if(data==null){
			//是：该用户此前没有添加该商品，则执行添加操作
			insert(cart);
		}else {
					//否：该用户已经添加该商品,获取原有的数量
			Integer n=data.getProductNum();
					//从cart参数中获取此次的增加，并计算得到新的数量
			Integer newProductNum=cart.getProductNum()+n;
					//更新商品数据
			updateProductNum(data.getId(),newProductNum);
		}

	}

	public List<CartVO> getCartListByUid(Integer uid) {
		return getList(uid);
	}
	
	public void addNum(Integer id) {
			Cart cart=findCartById(id);
			if(cart==null) {
				throw new CartNotFoundException("尝试访问的购物车数据不存在！");
			}
			Integer num=cart.getProductNum()+1;
			updateProductNum(id,num);
		
	}
	
	public void reduceNum(Integer id) {
		Cart cart=findCartById(id);
		if(cart==null) {
			throw new CartNotFoundException("尝试访问的购物车数据不存在！");
		}
		
		if(cart.getProductNum()<=1) {
			throw new ProductNumLimitException("尝试修改的购物车数据的商品数量超出限制！");
		}
		Integer num=cart.getProductNum()-1;
		updateProductNum(id,num);
		
	}
	
	/**
	 * 插入数据
	 * @param cart
	 * @return
	 */
	private Cart insert(Cart cart) {
		Integer rows=cartMapper.insert(cart);
		if(rows==1) {
			return cart;
		}else {
			throw new InsertDataException("向购物车中添加新数据时出现未知错误，请联系管理员！");
		}
	}


	/**
	 * 查询
	 * @param uid
	 * @param productId
	 * @return
	 */
	private	Cart findCartByUidAndProductId(
			Integer uid, String productId) {
		return cartMapper.findCartByUidAndProductId(uid, productId);
	}


	/**
	 * 修改数量
	 * @param id
	 * @param productNum
	 * @return
	 */
	private	void updateProductNum(Integer id,Integer productNum) {
		Integer rows=cartMapper.updateProductNum(id, productNum);
		if(rows!=1) {
			throw new UpdateDataException("修改购物车的商品数量时出现未知错误，请联系管理员！");
		}
	}

	
	/**
	 * 查询购物车列表
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
