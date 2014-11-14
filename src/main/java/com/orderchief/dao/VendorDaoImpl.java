package com.orderchief.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;

import org.springframework.stereotype.Repository;

import com.orderchief.domain.ProductSubOption;
import com.orderchief.domain.UserVendor;
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

	
	
	@Override
	public List<Vendor> getListByLocation(double latitude, double longitude) {
		List<Vendor> vendors = new ArrayList<Vendor>();
		Query query = em.createNativeQuery("SELECT vendor.VENDOR_ID, ( 6371 * acos( cos( radians(45.422) ) * "
				+"cos( radians( VENDOR_LATITUDE ) ) * cos( radians( VENDOR_LONGITUDE ) - radians(-75.66) )"
				+" + sin( radians(45.422) ) * sin(radians(VENDOR_LATITUDE)) ) )"
				+" AS distance FROM vendor HAVING distance < 10 ORDER BY distance");
		 
		
		List<Object[]> vendorObjects = (List<Object[]>)query.getResultList();
		BigDecimal bd;
		for(Object[] obj : vendorObjects){
			Vendor vendor = this.findById(Integer.parseInt(obj[0].toString()));
			bd = new BigDecimal(Float.parseFloat(obj[1].toString()));
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			vendor.setDistance(bd.doubleValue());
			vendors.add(vendor);
		}
		if(vendors!= null & vendors.size() > 0){
			return vendors;
		}
		else{
			return null;
		}
	}



	@Override
	public Vendor findById(int id) {
		
		return em.find(Vendor.class, id);
	}



	@Override
	public UserVendor checkVendor(String userName, String password) {
		Query query =  em.createQuery("from UserVendor where login=:username and password =:password");
		query.setParameter("username", userName);
		query.setParameter("password", password);
		
		UserVendor user = (UserVendor) query.getSingleResult();
		if(user != null){
			return user;
		}
		
		return null;
	}
	
//	public ProductSubOption getPS(){
//		ProductSubOption result1 = (ProductSubOption)em.createQuery("from "+ProductSubOption.class.getName()+" where productId=?").getSingleResult();
//		return result1;
//	}

}
