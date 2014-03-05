package com.orderchief.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderchief.dao.VendorDao;
import com.orderchief.domain.Vendor;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorDao vendorDao;
	
	@Transactional
	@Override
	public List<Vendor> getListOfVendors() {
		List<Vendor> vendors = vendorDao.getListOfVendors();
		return vendors;
	}

}
