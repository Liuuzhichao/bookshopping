package cn.sdut.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sdut.dao.OrderItemMapper;
import cn.sdut.model.OrderItem;
import cn.sdut.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private OrderItemMapper mapper;
	
	@Override
	public int saveOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return 0;
	}

}
