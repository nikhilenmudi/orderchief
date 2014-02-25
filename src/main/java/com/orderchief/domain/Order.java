package com.orderchief.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {
	
	@Id
	@Column(name="OID")
	protected int orderid;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="order")
	@Column(name="PRODUCTS")
	protected Product product;
	
	@Column(name="PRODUCT_TOTAL")
	protected BigDecimal producttotal;
	
	@Column(name="ORDER_TOTAL")
	protected BigDecimal ordertotal;
	
//	public BigDecimal getProductTotal()
//	{
//	}
}
