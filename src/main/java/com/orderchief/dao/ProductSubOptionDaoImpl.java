package com.orderchief.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.orderchief.domain.ProductSubOption;

@Repository
public class ProductSubOptionDaoImpl implements ProductSubOptionDAO {

	@PersistenceContext(unitName = "ocPersistenceUnit")
	EntityManager em;
	
	@Override
	public ProductSubOption getProductSubOptionById(int id) {
		return em.find(ProductSubOption.class, id);
	}

}
