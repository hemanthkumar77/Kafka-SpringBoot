package com.data.CustomSerializerDeserializer;

import java.io.Serializable;

public class Customer implements Serializable {

	private int customerId;
	private String customerName;
	private double price;
	private String productprice;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductprice() {
		return productprice;
	}
	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", price=" + price
				+ ", productprice=" + productprice + "]";
	}
	public Customer(int customerId, String customerName, double price, String productprice) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.price = price;
		this.productprice = productprice;
	}
	
}
