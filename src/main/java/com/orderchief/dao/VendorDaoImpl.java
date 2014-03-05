package com.orderchief.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.orderchief.domain.Vendor;


@Repository
public class VendorDaoImpl implements VendorDao {
	
	@PersistenceContext(unitName = "ocPersistenceUnit")
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> getListOfVendors() {
		List<Vendor> result = em.createQuery("from "+Vendor.class.getName()).getResultList();
		return result;
	}

}
