package com.orderchief.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Product")
public class Product {
	@Id
	@Column(name="productId")
	@GeneratedValue
	protected int productId;
	@Column(name="Product_Name")
	protected String name;
	
	@Column(name="price")
	protected BigDecimal baseprice;
	
	
	@OneToMany(cascade = javax.persistence.CascadeType.ALL,mappedBy="product")
	@JsonManagedReference("Product-option")
	protected List<ProductOption> productoption = new ArrayList<ProductOption>();
	
	@OneToMany(cascade = javax.persistence.CascadeType.ALL,mappedBy="product")
	@JsonManagedReference("Product-suboption")
	protected List<ProductSubOption> productSubOption = new ArrayList<ProductSubOption>();
	
	
	@ManyToOne
	@JoinColumn(name="ofVendor")
	@JsonBackReference("Product-list")
	protected Vendor vendor;
	
	@JsonIgnore
	public List<String> getProductOptionsAsListOfStrings(){
		List<String> productoptions = new ArrayList<String>();
		for(ProductOption poption : this.productoption){
			productoptions.add(poption.getTopping());
		}
		return productoptions;
	}
	
	@JsonIgnore
	public List<String> getProductSubOptionsAsListOfStrings(){
		List<String> productsuboptions = new ArrayList<String>();
		for(ProductSubOption psoption : this.productSubOption){
			productsuboptions.add(psoption.getSize());
		}
		return productsuboptions;
	}
	
	@JsonIgnore
	public BigDecimal getProductTotalPrice(){
		BigDecimal prod_total = this.baseprice;
		for(ProductOption productoption : this.productoption){
			prod_total = prod_total.add(productoption.baseprice); 
		}
		for(ProductSubOption productsuboption : this.productSubOption){
			prod_total = prod_total.add(productsuboption.baseprice); 
		}
		return prod_total;
	}
	
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public BigDecimal getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(BigDecimal baseprice) {
		this.baseprice = baseprice;
	}

	public List<ProductSubOption> getProductSubOption() {
		return productSubOption;
	}

	public void setProductSubOption(List<ProductSubOption> productSubOption) {
		this.productSubOption = productSubOption;
	}

	public List<ProductOption> getProductoption() {
		return productoption;
	}

	public void setProductoption(List<ProductOption> productoption) {
		this.productoption = productoption;
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
