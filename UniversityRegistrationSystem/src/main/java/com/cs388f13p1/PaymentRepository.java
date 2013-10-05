package com.cs388f13p1;

import java.util.Iterator;

public class PaymentRepository implements Repository<Payment> {

	private PaymentDAO paymentDAO;
	
	private static class SingletonHolder { 
		public static final PaymentRepository INSTANCE = new PaymentRepository();
	}

	public static PaymentRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private PaymentRepository() {
		paymentDAO = new PaymentDAO();
	}
	
	public void add(Payment obj) {
		try {
			paymentDAO.addPayment(obj);
	    } catch (Exception se) {
	      System.err.println("PaymentRepository: Threw a Exception retrieving student.");
	      System.err.println(se.getMessage());
	    }
	}

	public Payment findById(int id) {
		try {
			Payment payment = paymentDAO.findPaymentById(id);
	    	return payment;
	    } catch (Exception se) {
	      System.err.println("PaymentRepository: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}

	public Iterator<Payment> getAll() {
		return paymentDAO.getAllPayments();
	}
	
	public boolean contains(int id) {
		if (findById(id) == null) {
			return false;
		} else {
			return true;
		}
	}

}
