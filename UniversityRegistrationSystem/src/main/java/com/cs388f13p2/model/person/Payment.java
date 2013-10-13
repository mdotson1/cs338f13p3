package com.cs388f13p2.model.person;

public class Payment {
	private int paymentId;
	private String paymentType;
	private int paymentAmount;
	
	public Payment(final int id, final String type, final int amount) {
		paymentId = id;
		paymentType = type;
		paymentAmount = amount;
	}
	
	public int getPaymentId() {
		return paymentId;
	}
	
	public void setPaymentId(final int id) {
		paymentId = id;
	}
	
	public int getPaymentAmount() {
		return paymentAmount;
	}
	
	public void setPaymentAmount(final int amount) {
		paymentAmount = amount;
	}
	
	public void setPaymentType(final String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentType(){
		return paymentType;
	}

}
