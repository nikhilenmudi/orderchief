package com.orderchief.dao;

import java.util.List;

import com.orderchief.domain.Vendor;

public interface VendorDao {
	public List<Vendor> getListOfVendors();
	
	public List<Vendor> getListByLocation(double latitude, double longitude);
	
	public Vendor findById(int id);
}
