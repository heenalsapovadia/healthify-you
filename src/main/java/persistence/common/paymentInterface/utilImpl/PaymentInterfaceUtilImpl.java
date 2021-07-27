package persistence.common.paymentInterface.utilImpl;

import persistence.common.paymentInterface.daoImpl.PaymentInterfaceDAOImpl;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.common.paymentInterface.util.PaymentInterfaceUtil;
import persistence.patient.dao.PatientDAO;
import persistence.patient.daoImpl.PatientDAOImpl;
import persistence.patient.model.Patient;
import java.util.Date;
import persistence.common.paymentInterface.dao.PaymentInterfaceDAO;
/**
 * <pre>
 *
 * Payment Interface UtilImpl
 * this is class for implementing all the functionalities for payment interface
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class PaymentInterfaceUtilImpl implements PaymentInterfaceUtil {

    PaymentInterfaceDAOImpl paymentPersistence = new PaymentInterfaceDAOImpl();

    public int processPayment(PaymentBillingCategory billingCategory,
                              double remainingAmount, String voucherID) {
        PaymentInterface paymentDetails = new PaymentInterface();
        paymentDetails.setCurrentPaymentMode(persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface.payment_mode.C);
        paymentDetails.setStatusOfPayment(persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface.status.C);
        paymentDetails.setPatient_id(Patient.instance().getPatientId());
        paymentDetails.setVoucher_id(voucherID);

        // current billing date
        Date d1 = new Date();
        paymentDetails.setBilling_date(d1);

        // setting billing category
        paymentDetails.setBill_category(billingCategory);
        paymentDetails.setBill_amount(remainingAmount);

        // current payment mode
        paymentDetails.setCurrentPaymentMode(PaymentInterface.payment_mode.CC);

        // discount if applied any
        double discount = 0;
        paymentDetails.setDiscount(discount);
        paymentDetails.setCreated_on(d1);

        //status of payment
        paymentDetails.setStatusOfPayment(PaymentInterface.status.C);

        //voucher redemption date
        paymentDetails.setVoucher_redemption_date(d1);
        int updatedRecords = paymentPersistence.insertPaymentInterfaceDetails(paymentDetails);
        if (updatedRecords > 0 && voucherID != null && !voucherID.isEmpty() && !voucherID.isBlank()) {
            PatientDAO patientDAO = new PatientDAOImpl();
            patientDAO.updateVouchersForPatients("", null, Patient.instance().getPatientId());
        }
        return updatedRecords;
    }

	@Override
	public int getPointsRedeemed(int patientId) {
		PaymentInterfaceDAO paymentDAO = new PaymentInterfaceDAOImpl();
		return paymentDAO.getVoucherRedemptionPoints(patientId);
	}
}