package model.billing;

public class Payment {
	private String paymentType;
	private int paymentAmount;
	
	public Payment(String type, int amount) {
		paymentType = type;
		paymentAmount = amount;
	}
}
