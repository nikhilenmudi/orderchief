package com.orderchief.server;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.orderchief.domain.Product;
import com.orderchief.service.MenuService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/getMenu")
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	
	@Autowired
	private MenuService menuService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/{vendorId}", method = RequestMethod.GET)
	public @ResponseBody List<Product> getMenu(@PathVariable int vendorId){
		
		List<Product> Menu = menuService.getMenuById(vendorId);
		return Menu;
	}
	
}
