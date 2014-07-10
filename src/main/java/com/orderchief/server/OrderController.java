package com.orderchief.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orderchief.service.OrderService;
import com.orderchief.util.OrderItem;




@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ResponseBody @RequestMapping(value = "/submitorder" , method = RequestMethod.POST, headers = "Accept=application/*" )
	public void submitOrder(@RequestBody List<OrderItem> jsonOrders, HttpServletResponse response){
		System.out.println("the size is"+jsonOrders.size());
		this.orderService.saveOrder(jsonOrders);
		System.out.println("the size is"+jsonOrders.size());
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
