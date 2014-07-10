package com.orderchief.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.orderchief.domain.ProductOption;

@Repository
public class ProductOptionDaoImpl implements ProductOptionDAO {

	@PersistenceContext(unitName = "ocPersistenceUnit")
	EntityManager em;
	
	@Override
	public ProductOption getProductOptionById(int id) {
		return em.find(ProductOption.class, id);
	}

}
