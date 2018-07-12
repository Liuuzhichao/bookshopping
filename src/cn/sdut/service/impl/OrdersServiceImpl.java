package cn.sdut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sdut.dao.OrdersMapper;
import cn.sdut.model.OrderItem;
import cn.sdut.model.Orders;
import cn.sdut.service.OrderItemService;
import cn.sdut.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersMapper mapper;
	@Autowired
	private OrderItemService service;
	
	@Override
	public int saveOrder(Orders order) {
		int num = mapper.saveOrder(order);
		//保存订单项
		List<OrderItem> orderItems = order.getOrderItems();
		for ( OrderItem orderItem: orderItems) {
			service.saveOrderItem(orderItem);
		}
		return num;
	}

}
