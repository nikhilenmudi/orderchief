package com.orderchief.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="prod_sub_option")
public class ProductSubOption {
	
	@Id
	@Column(name="product_sub_option_id")
	@GeneratedValue
	protected int productSubOptionId;
	
	@Column(name="product_size")
	protected String size;
	
	
	@JsonBackReference("Product-suboption")
	@ManyToOne()
	@JoinColumn(name="productId")
	protected Product product;
	
	@Column(name="price")
	protected BigDecimal baseprice;
	
	public BigDecimal getBaseprice() {
		return baseprice;
	}
	public void setBaseprice(BigDecimal baseprice) {
		this.baseprice = baseprice;
	}
	public int getProductSubOptionId() {
		return productSubOptionId;
	}
	public void setProductSubOptionId(int productSubOptionId) {
		this.productSubOptionId = productSubOptionId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
