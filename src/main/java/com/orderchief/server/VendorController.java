package com.orderchief.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.ResponseBody;

import com.orderchief.domain.Vendor;
import com.orderchief.service.VendorService;


@Controller
public class VendorController {
	
	@Autowired
	private VendorService vendorService;

	@RequestMapping("/getVendors/{lat}/{lng}")
	public @ResponseBody List<Vendor> getVendors(@PathVariable double lat,@PathVariable double lng){
		System.out.println("Current position is "+lat+" and"+lng);
		List<Vendor> vendors = vendorService.getVendorsListByLocation(lat, lng); 
		System.out.println(vendors.size());
		return vendors;
	}
	
	
}
