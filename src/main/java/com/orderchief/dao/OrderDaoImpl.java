package com.orderchief.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.orderchief.domain.Order;

@Repository
public class OrderDaoImpl implements OrderDAO {


	@PersistenceContext(unitName = "ocPersistenceUnit")
	EntityManager em;
	
	@Override
	public void saveOrder(Order order) {
		this.em.persist(order);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderByVendorId(int id) {
		Query query = em.createQuery("from Order where ofVendor = ?0 and status = ?1");
		query.setParameter(0, id);
		query.setParameter(1, "PENDING");
		return query.getResultList();
	}

	@Override
	public Order findById(int orderId) {
		return em.find(Order.class, orderId);
	}

}
