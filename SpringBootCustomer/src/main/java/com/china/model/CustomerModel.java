package com.china.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name="CustomerModel")
public class CustomerModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String customerName;
	private String mobileNumber;
	
	private String emailId;
	private String password;
	public CustomerModel() {
		super();
	}
	public CustomerModel(int id, String customerName, String mobileNumber, String emailId, String password) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
