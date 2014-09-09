package com.orderchief.dao;

import java.util.List;

import com.orderchief.domain.Order;

public interface OrderDAO {

	public void saveOrder(Order order);
	
	public List<Order> getOrderByVendorId(int id);
	
}
