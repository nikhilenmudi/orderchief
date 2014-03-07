package com.orderchief.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderchief.domain.Vendor;
import com.orderchief.service.VendorService;


@RestController
public class VendorController {
	
	@Autowired
	private VendorService vendorService;

	@RequestMapping("/getVendors")
	public List<Vendor> getVendors(){
		List<Vendor> vendors = vendorService.getListOfVendors(); 
		System.out.println(vendors.size());
		return vendors;
	}
	
		
}
