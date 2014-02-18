package com.orderchief.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id
	private int productId;
	private String name;
	
	@ElementCollection
	private Set<ProductOption> option = new HashSet<>();
	
	@ElementCollection
	private Set<ProductSubOption> productSubOption = new HashSet<>();
	

	

	

	


	public Set<ProductOption> getOption() {
		return option;
	}


	public void setOption(Set<ProductOption> option) {
		this.option = option;
	}


	public Set<ProductSubOption> getProductSubOption() {
		return productSubOption;
	}


	public void setProductSubOption(Set<ProductSubOption> productSubOption) {
		this.productSubOption = productSubOption;
	}


	public int getProductId() {
		return productId;
	}

	
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
