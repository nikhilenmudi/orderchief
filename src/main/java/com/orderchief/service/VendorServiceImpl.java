package com.orderchief.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderchief.dao.VendorDao;
import com.orderchief.domain.UserVendor;
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

	@Transactional
	@Override
	public List<Vendor> getVendorsListByLocation(double latitude,
			double longitude) {
		List<Vendor> vendors = this.vendorDao.getListByLocation(latitude, longitude);
		for(Vendor vendor : vendors){
		System.out.println("The distance is "+ vendor.getDistance());
		}

		return vendors;
	}

	@Override
	@Transactional
	public UserVendor authenticateVendor(String loginKey) {
		String[] loginInfo = loginKey.split("\\s+");
		String username = loginInfo[0];
		String password = loginInfo[1];
		UserVendor user = this.vendorDao.checkVendor(username, password);
		boolean loginStatus = false;
		if(user != null){
			return user;
		}
		return null;
	}

}
