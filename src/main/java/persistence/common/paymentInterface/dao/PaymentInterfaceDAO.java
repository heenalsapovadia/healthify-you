package persistence.common.paymentInterface.dao;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.patient.model.Patient;

import java.util.List;

public interface PaymentInterfaceDAO {
    void insertPaymentInterfaceDetails(PaymentInterface paymentInterface);

    public List<PaymentInterface> getAllPaymentInterfaceDetails(Patient patient);
}
