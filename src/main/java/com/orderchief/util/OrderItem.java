package com.orderchief.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="order_items")
public class OrderItem {

	@Id
	@Column(name="OID")
	@GeneratedValue
	private int oid;
	
	@Column(name="PRODUCT_ID")
	private Integer id;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@ElementCollection
	@Column(name="PRODCUT_OPTION_NAME")
	private List<String> productOptions = new ArrayList<String>();
	
	@ElementCollection
	@Column(name="PRODUCT_SUB_OPTION")
	private List<String> productSubOptions = new ArrayList<String>();
	
	@Column(name="PRICE")
	private BigDecimal itemTotal;
	
	@ElementCollection
	@Column(name= "PRODUCT_OPTION_ID")
	private List<String> optionIds = new ArrayList<String>();
	
	@ElementCollection
	@Column(name= "PRODUCT_SUB_OPTION_ID")
	private List<Integer> subOptionIds = new ArrayList<Integer>();



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getOptionIds() {
		return optionIds;
	}

	public void setOptionIds(List<String> optionIds) {
		this.optionIds = optionIds;
	}

	public List<Integer> getSubOptionIds() {
		return subOptionIds;
	}

	public void setSubOptionIds(List<Integer> subOptionIds) {
		this.subOptionIds = subOptionIds;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(BigDecimal itemTotal) {
		this.itemTotal = itemTotal;
	}

	public List<String> getProductOptions() {
		return productOptions;
	}

	public void setProductOptions(List<String> productOptions) {
		this.productOptions = productOptions;
	}

	public List<String> getProductSubOptions() {
		return productSubOptions;
	}

	public void setProductSubOptions(List<String> productSubOptions) {
		this.productSubOptions = productSubOptions;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	

		
	
	
	
}
