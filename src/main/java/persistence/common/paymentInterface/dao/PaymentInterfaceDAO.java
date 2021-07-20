package persistence.common.dao;

import persistence.common.model.PaymentInterface;
import persistence.patient.model.Patient;

import java.util.List;

public interface PaymentInterfaceDAO {
     void insertPaymentInterfaceDetails(PaymentInterface paymentInterface);

    public List<PaymentInterface> getAllPaymentInterfaceDetails(Patient patient);
}
