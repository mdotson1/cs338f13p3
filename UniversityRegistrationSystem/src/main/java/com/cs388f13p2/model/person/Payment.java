package com.cs388f13p2.model.person;

public class Payment {
	private String paymentType;
	private int paymentAmount;
	
	public Payment(final String type, final int amount) {
		paymentType = type;
		paymentAmount = amount;
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
