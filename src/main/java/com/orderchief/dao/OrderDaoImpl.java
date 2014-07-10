package com.orderchief.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
