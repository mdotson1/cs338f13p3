package controllers.services;

import models.database.dao.concrete.PaymentRepository;
import models.person.Payment;

import java.sql.SQLException;
import java.util.Map;

public class PaymentService {
    public static int createPayment(Map<String,String> data)
            throws SQLException {

        final Payment p = new Payment(data.get("Payment Type"),
                Double.parseDouble(data.get("Payment Amount (USD)")));

        return PaymentRepository.getInstance().add(p);
    }
}
