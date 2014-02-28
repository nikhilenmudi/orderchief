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

@Entity
@Table(name="ORDERS")
public class Order {
	
	@Id
	@Column(name="OID")
	@GeneratedValue
	protected int orderid;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Column(name="PRODUCTS")
	protected List<Product> product = new ArrayList<Product>();
	
	@Column(name="PRODUCT_TOTAL")
	protected BigDecimal producttotal;
	
	@Column(name="STATUS")
	protected String status;
	
	@Column(name="ORDER_TOTAL")
	protected BigDecimal ordertotal;
	
	@ManyToOne
	@JoinColumn(name="OfVendor")
	protected Vendor vendor;
	
	public BigDecimal calcOrderTotal()
	{
		BigDecimal ordertotal = null;
		for(Product product : this.product){
			ordertotal = ordertotal.add(product.getProductTotalPrice());
		}
		
		return ordertotal;
	}

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

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public BigDecimal getProducttotal() {
		return producttotal;
	}

	public void setProducttotal(BigDecimal producttotal) {
		this.producttotal = producttotal;
	}

	public BigDecimal getOrdertotal() {
		return ordertotal;
	}

	public void setOrdertotal(BigDecimal ordertotal) {
		this.ordertotal = calcOrderTotal();
	}

	
}
