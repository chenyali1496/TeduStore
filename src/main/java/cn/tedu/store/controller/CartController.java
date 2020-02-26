package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.District;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.vo.CartVO;

@Controller
@RequestMapping("/cart")
public class CartController  extends BaseController {
	@Autowired
	private ICartService cartService;
	
	@RequestMapping(value="/add.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> addToCart(
			HttpSession session,
			@RequestParam("product_id") String productId,
			@RequestParam("product_num") Integer productNum){
				//从Session中获取uid
		Integer uid=getUidFromSession(session);
				//创建Cart类型的对象
		Cart cart=new Cart(uid,productId,productNum);
				//调用业务层方法
		cartService.addToCart(cart);
				//返回
		return new ResponseResult<Void>();
		
	}
	
	@RequestMapping("/list.do")
	@ResponseBody
	public ResponseResult<List<CartVO>> getCartListByUid(
			HttpSession session){
		//获取uid
		Integer uid=getUidFromSession(session);
		//执行
		List<CartVO> list=cartService.getCartListByUid(uid);
		//创建返回值对象
		ResponseResult<List<CartVO>> rr=new	ResponseResult<List<CartVO>>();
		//封装数据
		rr.setData(list);
		return rr;
		
	}
	@RequestMapping("/add_num.do")
	@ResponseBody
	public ResponseResult<Void> addProductNum(
			@RequestParam("id") Integer id){
		cartService.addNum(id);
				return new ResponseResult<Void>();
				
	}
	
	@RequestMapping("/reduce_num.do")
	@ResponseBody
	public ResponseResult<Void> reduceProductNum(
			@RequestParam("id") Integer id){
		cartService.reduceNum(id);
		return new ResponseResult<Void>();
		
	}

}
