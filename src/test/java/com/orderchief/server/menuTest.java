package com.orderchief.server;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.orderchief.dao.MenuDao;
import com.orderchief.domain.Product;
import com.orderchief.domain.Vendor;
import com.orderchief.service.VendorService;


@ContextConfiguration(locations = { "/OcContextTest.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class menuTest {

	@Autowired
	private MenuDao md;
	
	@Autowired
	private VendorService vs;
	
	@Test
	public void testGetMenuForVendor() {
		List<Product> menu = md.getMenuForVendor(1);
		assertNotNull(menu);
		System.out.println(menu.size());
		for(Product product : menu){
			System.out.println(product.getName());
		}
	}
	@Test
	public void testVendors(){
		List<Vendor> vv = this.vs.getVendorsListByLocation(45.66, -75.222);
		System.out.print("test over");
	}

}
