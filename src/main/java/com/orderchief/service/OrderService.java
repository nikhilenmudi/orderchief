package com.orderchief.service;

import java.util.List;

import com.orderchief.domain.Order;
import com.orderchief.domain.Product;
import com.orderchief.util.OrderItem;

public interface OrderService {
	
	public void saveOrder(String userGcmKey, List<OrderItem> orderList);
	
	public List<Order> getOrdersForVendor(int id);
	
}
