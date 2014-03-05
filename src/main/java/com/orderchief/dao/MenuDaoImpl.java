package com.orderchief.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.orderchief.domain.Product;

@Repository
public class MenuDaoImpl implements MenuDao {
	
	@PersistenceContext(unitName = "ocPersistenceUnit")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getMenuForVendor(int vendorId) {
		List<Product> result = em.createQuery("from "+Product.class.getName()+" where ofVendor = :vendorId").setParameter("vendorId", vendorId).getResultList();
		System.out.println(result.size());
		return result;
	}

	
}
