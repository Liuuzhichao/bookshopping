package cn.sdut.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.sdut.model.Products;
import cn.sdut.service.ProductService;
import cn.sdut.utils.ProductUtils;

@Controller
public class CartController {

	@Autowired
	private ProductService service;
	
	//显示购物车页面
	@RequestMapping("showCart")
	public String showCart() {
		return "cart";
	}
	
	//加入购物车
	@RequestMapping("addCart")
	@ResponseBody
	public String addCart(String id, HttpServletRequest request) {
		//获取到要加入购物车的商品
		Products product = null;
		HttpSession session = request.getSession();
		
		//先从session中获取购物车对象
		Map<Products,Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");
		//判断是否存在购物车对象
		if(cart==null) {
			//定义一个map集合来作为购物车
			cart = new HashMap<>();
			//根据商品id来查询到商品
			product = service.findProductById(id);
			//将商品放入购物车,且置购买数量为1
			cart.put(product, 1);
		} else {
			//判断加入购物车的商品在购物车中是否已经存在
			Set<Products> keySet = cart.keySet();
			product = ProductUtils.findProduct(keySet, id);
			if(product==null) {
				product = service.findProductById(id);
				cart.put(product, 1);
			} else {//购物车中已经存在该商品
				if(cart.get(product)==product.getPnum()) {
					return "{\"msg\":\"notEnough\"}";
				}else {
					cart.put(product,cart.get(product)+1);
				}
			}
		}
		
		//将购物车对象放到session中
		session.setAttribute("cart", cart);
		return "{\"msg\":\"true\"}";
	}
	
	//修改购物车信息
	@RequestMapping("updateCart")
	public String updateCart(String id,Integer count, HttpServletRequest request) {
		//获取到购物车对象
		HttpSession session = request.getSession();
		Map<Products,Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");
		
		Set<Products> keySet = cart.keySet();
		Products product = ProductUtils.findProduct(keySet, id);
		
		cart.put(product, count);
		
		if(count==0) {
			cart.remove(product);
		}
		
		
		session.setAttribute("cart", cart);
		
		return "redirect:showCart";
	}


}
