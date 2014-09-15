package com.orderchief.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="vendor")
public class Vendor {
	
	@Id
	@Column(name="VENDOR_ID")
	@GeneratedValue
	protected int vendorId;
	
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,mappedBy="vendor")
	@Column(name="Product")
	@JsonManagedReference("Product-list")
	protected List<Product> product = new ArrayList<Product>();
	
	@Column(name="VENDOR_NAME")
	protected String vendorName;
	
	@Column(name="COUNTER")
	protected int waitingNumber;
	
	@Column(name="VENDOR_LATITUDE")
	protected double vendorLatitude;
	
	@Column(name="VENDOR_LONGITUDE")
	protected double vendorLongitude;
	
	@Transient
	protected double distance;
	
	@Column(name="LOGO")
	protected String logo;
	
	@JsonIgnore
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,mappedBy="vendor")
	@Column(name="CURRENTORDERS")
	protected List<Order> currentorders = new ArrayList<Order>();
	
	
	
	public List<Order> getCurrentorders() {
		return currentorders;
	}
	public void setCurrentorders(List<Order> currentorders) {
		this.currentorders = currentorders;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public int getWaitingNumber() {
		return waitingNumber;
	}
	public void setWaitingNumber(int waitingNumber) {
		this.waitingNumber = waitingNumber;
	}
	
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public double getVendorLatitude() {
		return vendorLatitude;
	}
	public void setVendorLatitude(double vendorLatitude) {
		this.vendorLatitude = vendorLatitude;
	}
	public double getVendorLongitude() {
		return vendorLongitude;
	}
	public void setVendorLongitude(double vendorLongitude) {
		this.vendorLongitude = vendorLongitude;
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
}
