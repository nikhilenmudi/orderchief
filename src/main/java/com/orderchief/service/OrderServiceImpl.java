package com.orderchief.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

}
