package main.java.com.cs388f13p1;

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
}
