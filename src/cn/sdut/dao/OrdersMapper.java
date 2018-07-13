package cn.sdut.dao;

import java.util.List;

import cn.sdut.model.Orders;

public interface OrdersMapper {
	
	public int saveOrder(Orders order);

	public List<Orders> findOrderList(Integer id);

}
