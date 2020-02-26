package cn.tedu.store.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.ResponseResult;

import cn.tedu.store.service.IProductService;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
	
		@Autowired
		private IProductService productService;

		@RequestMapping("/hot_list.do")
		@ResponseBody
		public ResponseResult<List<Product>> getList(){
			ResponseResult<List<Product>> rr=new ResponseResult<List<Product>>();
			List<Product> list=productService.getHotProductList();
			rr.setData(list);
			return rr;
		}
		
		@RequestMapping("/details.do")
		@ResponseBody
		public ResponseResult<Product> getDetails(@RequestParam("id")String id){
			Product product=productService.getProductById(id);
			ResponseResult<Product> rr=new ResponseResult<Product>();
			rr.setData(product);
			return  rr;
		}
	}


