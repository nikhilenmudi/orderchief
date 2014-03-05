package com.orderchief.service;

import java.util.List;

import com.orderchief.domain.Product;

public interface MenuService {

	public List<Product> getMenuById(int VendorId);
	
}
