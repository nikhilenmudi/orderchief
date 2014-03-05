package com.orderchief.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orderchief.domain.Vendor;
import com.orderchief.service.VendorService;

@Controller
@RequestMapping(value="/getVendors")
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Vendor> getVendors(){
		List<Vendor> vendors = vendorService.getListOfVendors(); 
		System.out.println(vendors.size());
		return vendors;
	}
	
}
