package com.orderchief.dao;

import java.util.List;

import com.orderchief.domain.Product;

public interface MenuDao {

	
	public List<Product> getMenuForVendor(int vendorId);
}
