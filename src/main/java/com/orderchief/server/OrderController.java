package com.orderchief.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.orderchief.domain.Order;
import com.orderchief.domain.Vendor;
import com.orderchief.service.OrderService;
import com.orderchief.util.OrderItem;




@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ResponseBody @RequestMapping(value = "/submitorder/{userGcmKey}" , method = RequestMethod.POST, headers = "Accept=application/*" )
	public void submitOrder(@PathVariable String userGcmKey, @RequestBody List<OrderItem> jsonOrders, HttpServletResponse response){
		System.out.println("the size is"+jsonOrders.size());
		this.orderService.saveOrder(userGcmKey, jsonOrders);
		System.out.println("the size is"+jsonOrders.size());
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value = "/orderready", method = RequestMethod.POST)
	public void orderReady(@RequestParam String regId, HttpServletResponse response){
		//System.out.println("The server key is "+ apiKey);
		System.out.println("User key is "+ regId);
//		OrderReadyMsg orderReadyMsg = new OrderReadyMsg();
//		orderReadyMsg.createData("This is awesome", "Achieved this awesomeness");
//		orderReadyMsg.addRegId(regId);
//		SendToGCM.post(apiKey, orderReadyMsg);
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://android.googleapis.com/gcm/send?=&=";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "key=AIzaSyA9Y6HebiaONVpW1MUhOSVhrlMdyjL_zR4");
		requestHeaders.set("Content-type", "application/x-www-form-urlencoded");
		MultiValueMap<String, String> postParams = new LinkedMultiValueMap<String, String>();
		postParams.add("registration_id",regId);
		
		String data = "Your Order at is ready. Kindly Visit the Store";
		postParams.add("data", data);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParams, requestHeaders);
		ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		System.out.println(re.getBody());
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value = "/getOrders/{vendorId}", method = RequestMethod.GET)
	public @ResponseBody List<Order> getOrders(@PathVariable int vendorId){
		List<Order> orders = orderService.getOrdersForVendor(vendorId); 
		System.out.println(orders.size());
		return orders;
	}
	
	@RequestMapping(value = "/getOrderItems/{orderId}", method = RequestMethod.GET)
	public @ResponseBody List<OrderItem> getOrderItems(@PathVariable int orderId){
		List<OrderItem> orderItems = orderService.getOrderItems(orderId); 
		System.out.println(orderItems.size());
		return orderItems;
	}
	
	@RequestMapping(value = "/ordercomplete", method = RequestMethod.POST)
	public void orderComplete(@RequestParam int completedOrderId, HttpServletResponse response){
		 System.out.println("completing order"+completedOrderId);
		 this.orderService.completeOrder(completedOrderId);
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
