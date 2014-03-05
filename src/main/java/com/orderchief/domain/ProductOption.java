package com.orderchief.domain;



import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="ProductOption")
public class ProductOption{
	
	
	
	@Id
	@Column(name="product_option_id")
	@GeneratedValue
	protected int productOptionId;
	
	
	
	@ManyToOne()
	@JsonBackReference("Product-option")
	protected Product product;		
	
	@Column(name="ProductTopping")
	private String topping;
			
	@Column(name="price")
	protected BigDecimal baseprice;
			

	public int getProductOptionId() {
		return productOptionId;
	}

	public void setProductOptionId(int productOptionId) {
		this.productOptionId = productOptionId;
	}

	public BigDecimal getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(BigDecimal baseprice) {
		this.baseprice = baseprice;
	}
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	public String getTopping() {
		return topping;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}

	
	
	

	
	
}
