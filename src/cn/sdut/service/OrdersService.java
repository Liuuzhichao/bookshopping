package cn.sdut.service;

import java.util.List;

import cn.sdut.model.Orders;

public interface OrdersService {

	/**
	 * 保存订单
	 * @param order
	 * @return
	 */
	public int saveOrder(Orders order);

	/**
	 * 根据用户id查询订单
	 * @param id 用户id
	 * @return 查询到的订单
	 */
	public List<Orders> findOrderList(Integer id);
	
}
