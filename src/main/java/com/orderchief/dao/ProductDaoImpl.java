package com.orderchief.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.orderchief.domain.Product;

@Repository
public class ProductDaoImpl implements ProductDAO {

	@PersistenceContext(unitName = "ocPersistenceUnit")
	EntityManager em;

	
	@Override
	public Product getProductById(int id) {
		return em.find(Product.class, id);
	}

}
