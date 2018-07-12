package cn.sdut.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sdut.model.OrderItem;
import cn.sdut.model.Orders;
import cn.sdut.model.Products;
import cn.sdut.model.Users;
import cn.sdut.service.OrdersService;
import cn.sdut.utils.Dateutils;
import cn.sdut.utils.UUIDUtils;

@Controller
public class OrderController {
	
	@Autowired
	private OrdersService service;
	
	//打开生成订单的页面
	@RequestMapping("showCreatOrder")
	public String showCreatOrder() {
		return "order";
	}
	
	//生成订单
	@RequestMapping("creatOrder")
	public String creatOrder(Orders order, HttpServletRequest request) {
		//只获取到了金额和收货地信息,需要补充订单的其他信息
		String id = UUIDUtils.getUUID();
		order.setId(id);
		order.setOrdertime(Dateutils.FormatDate(new Date()));
		order.setPaystate(0);//订单状态:0表示未支付,1表示已支付
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		order.setUserId(user.getId());
		
		//获取购物车信息
		Map<Products, Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");
		Set<Products> keySet = cart.keySet();
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		
		for(Products product :keySet) {
			//设置订单项信息
			OrderItem item = new OrderItem();
			item.setOrderId(id);
			item.setProductId(product.getId());
			item.setBuynum(cart.get(product));
			items.add(item);
		}
		order.setOrderItems(items);
		
		service.saveOrder(order);
		
		session.removeAttribute("cart");
		
		return "";
	}

}
