package models.person;

public class Payment {
	private int paymentId;
	private String paymentType;
	private double paymentAmount;
	
	// when retrieving from database, has id
	public Payment(final int id, final String type, final double amount) {
		paymentId = id;
		paymentType = type;
		paymentAmount = amount;
	}
	
	// when creating before adding to database
	public Payment(final String type, final double amount) {
		paymentId = -1;
		paymentType = type;
		paymentAmount = amount;
	}
	
	public int getPaymentId() {
		return paymentId;
	}
	
	public void setPaymentId(final int id) {
		paymentId = id;
	}
	
	public double getPaymentAmount() {
		return paymentAmount;
	}
	
	public void setPaymentAmount(final double amount) {
		paymentAmount = amount;
	}
	
	public void setPaymentType(final String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentType(){
		return paymentType;
	}
	
	@Override
	public String toString() {
		return paymentId + ": " + paymentAmount;
	}

}
