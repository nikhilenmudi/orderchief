package com.orderchief.service;

import java.util.Collections;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderchief.dao.MenuDao;
import com.orderchief.domain.Product;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Transactional
	@Override
	public List<Product> getMenuById(int vendorId) {
		List<Product> menu = Collections.unmodifiableList(menuDao.getMenuForVendor(vendorId));
		return menu;
	}

}
