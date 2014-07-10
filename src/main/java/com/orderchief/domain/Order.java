package com.orderchief.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.orderchief.util.OrderItem;

@Entity
@Table(name="ORDERS")
public class Order {
	
	@Id
	@Column(name="OID")
	@GeneratedValue
	protected int orderid;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Column(name="ORDER_PRODUCTS")
	protected List<OrderItem> product = new ArrayList<OrderItem>();
	
	@Column(name="STATUS")
	protected String status;
	
	@Column(name="ORDER_TOTAL")
	protected BigDecimal ordertotal;
	
	@ManyToOne
	@JoinColumn(name="OfVendor")
	protected Vendor vendor;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}


	public BigDecimal getOrdertotal() {
		return ordertotal;
	}

	public List<OrderItem> getProduct() {
		return product;
	}

	public void setProduct(List<OrderItem> product) {
		this.product = product;
	}

	public void setOrdertotal(BigDecimal ordertotal) {
		this.ordertotal = ordertotal;
	}

	

	
}
