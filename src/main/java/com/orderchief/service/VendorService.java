package com.orderchief.service;

import java.util.List;

import com.orderchief.domain.Vendor;

public interface VendorService {
	public List<Vendor> getListOfVendors();
	public List<Vendor> getVendorsListByLocation(double latitude, double longitude);
}
