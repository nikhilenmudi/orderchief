package com.orderchief.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderchief.dao.OrderDAO;
import com.orderchief.dao.ProductDAO;
import com.orderchief.dao.ProductOptionDAO;
import com.orderchief.dao.ProductSubOptionDAO;
import com.orderchief.domain.Order;
import com.orderchief.domain.Product;
import com.orderchief.domain.ProductOption;
import com.orderchief.domain.ProductSubOption;
import com.orderchief.domain.Vendor;
import com.orderchief.util.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductDAO productDao;

	@Autowired
	private ProductOptionDAO productOptionDao;

	@Autowired
	private ProductSubOptionDAO productSubOptionDao;

	@Autowired
	private OrderDAO orderDao;

	@Override
	@Transactional
	public void saveOrder(String userGcmKey, List<OrderItem> orderList) {
		Product product;
		Order order = new Order();
		order.setUserGcmKey(userGcmKey);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		Vendor ofVendor = null;
		BigDecimal calculateOrderTotal = BigDecimal.ZERO;
		for (OrderItem orderItem : orderList) {
			if (orderItem.getOptionIds().size()>0 || orderItem.getProductSubOptions().size()>0) {
				orderItem.setItemTotal(BigDecimal.ZERO);
				BigDecimal calculateItemTotal = BigDecimal.ZERO;
				product = this.productDao.getProductById(orderItem.getId());
				ofVendor = product.getVendor();
				int waitingNumber = ofVendor.getWaitingNumber();
				waitingNumber+=1;
				ofVendor.setWaitingNumber(waitingNumber);
				orderItem.setProductName(product.getName());
				calculateItemTotal = calculateItemTotal.add(product
						.getBaseprice());
				System.out.println(calculateItemTotal);
				if (orderItem.getOptionIds() != null) {
					for (String optionId : orderItem.getOptionIds()) {
						int orderOptionId = Integer.parseInt(optionId
								.substring(2));
						ProductOption po = this.productOptionDao
								.getProductOptionById(orderOptionId);
						orderItem.getProductOptions().add(po.getTopping());
						calculateItemTotal = calculateItemTotal.add(po.getBaseprice());
						System.out.println(calculateItemTotal);
					}
				}
				if (orderItem.getSubOptionIds() != null) {
					for (int subOptionId : orderItem.getSubOptionIds()) {
						ProductSubOption pso = this.productSubOptionDao
								.getProductSubOptionById(subOptionId);
						orderItem.getProductSubOptions().add(pso.getSize());
						calculateItemTotal = calculateItemTotal.add(pso.getBaseprice());
						System.out.println(calculateItemTotal);
					}
				}
				orderItem.setItemTotal(calculateItemTotal);
				orderItems.add(orderItem);

				calculateOrderTotal = calculateOrderTotal.add(orderItem
						.getItemTotal());

			}
		}

		order.setProduct(orderItems);
		order.setStatus("Pending");
		System.out.println("ordertotal" + calculateOrderTotal);
		order.setOrdertotal(calculateOrderTotal);
		order.setVendor(ofVendor);
		this.orderDao.saveOrder(order);
	}

	@Transactional
	@Override
	public List<Order> getOrdersForVendor(int id) {
		return this.orderDao.getOrderByVendorId(id);
	}

	@Transactional
	@Override
	public List<OrderItem> getOrderItems(int orderId) {
		Order order = this.orderDao.findById(orderId);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems = order.getProduct();
		return orderItems;
	}

	@Transactional
	@Override
	public void completeOrder(int completedOrderId) {
		//Change order status
		Order order = this.orderDao.findById(completedOrderId);
		order.setStatus("DONE");
		//change vendor counter
		Vendor vendor = order.getVendor();
		int waitCounter = vendor.getWaitingNumber();
		waitCounter--;
		vendor.setWaitingNumber(waitCounter);
		this.orderDao.saveOrder(order);
		
	}

	@Override
	@Transactional
	public boolean processPayment(String paymentToken,List<OrderItem> jsonOrders) {
		Stripe.apiKey = "sk_test_1RC9A5TaK8P3DKBuvPrv5TGs";
		System.out.println("The api key is "+ Stripe.apiKey);
		System.out.println("Charging "+ this.calculateOrderTotal(jsonOrders));
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		
		
		
		double price = 0;
		System.out.println("The price is 1st "+ price+ "order size"+jsonOrders.size());
		for(OrderItem x : jsonOrders){
			System.out.println("x is "+ x.getId());
			Product xp = this.productDao.getProductById(x.getId());
			
			if(xp == null){
				System.out.println("xp is bc null");
			}
			System.out.println("Product retrieved");
			System.out.println("Before getting baseprice");
			try{
			xp.getProductId();
			}
			catch(Exception e){
				e.printStackTrace();
				e.getCause();
			}
			System.out.println("After gETTING Bsae price");
			System.out.println("Base price is "+ xp.getBaseprice().doubleValue());
			price = price + xp.getBaseprice().doubleValue();
			System.out.println("Now price is "+ price +"for item "+x.getId());
			System.out.println("Size of option ids "+ x.getOptionIds().size());
			for(String po : x.getOptionIds()){
				System.out.println(po);
				String poId = po.substring(2);
				System.out.println("The option id is "+ poId);
				ProductOption prodopt = this.productOptionDao.getProductOptionById(Integer.parseInt(poId));
				System.out.println("prod opt is "+ prodopt.getProductOptionId());
				price = price + prodopt.getBaseprice().doubleValue();
				System.out.println("Now price is for item "+price+"for "+prodopt.getProductOptionId());
			}
			
			for(String pso : x.getProductSubOptions()){
				ProductSubOption prodsubopt = this.productSubOptionDao.getProductSubOptionById(Integer.parseInt(pso));
				price = price + prodsubopt.getBaseprice().doubleValue();
				System.out.println("Now price is for item "+price+"for "+prodsubopt.getSize());
			}
		}
		System.out.println("The price is 2nd "+ price);
		
		
		
		chargeParams.put("amount", (int)price*100); 
		chargeParams.put("currency", "usd");
		chargeParams.put("card", paymentToken);
		chargeParams.put("description", "Charge for Orderbuzz online order");
		System.out.println("Charging "+ this.calculateOrderTotal(jsonOrders));
		try {
			Boolean payment = Charge.create(chargeParams).getPaid();
			if (!payment)
				return false; 

		} catch (AuthenticationException e) {
			e.printStackTrace();
			return false; 
		} catch (InvalidRequestException e) {
			e.printStackTrace();
			return false; 
		} catch (APIConnectionException e) {
			e.printStackTrace();
			return false; 
		} catch (CardException e) {
			e.printStackTrace();
			return false; 
		} catch (APIException e) {
			e.printStackTrace();
			return false; 
		}
		return true;
	}
	
	public double calculateOrderTotal(List<OrderItem> jsonOrders){
		
		return 0;
	}

}
