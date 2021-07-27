package persistence.common.paymentInterface.dao;
/**
 * <pre>
 *
 * PaymentInterface Database DAO
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.patient.model.Patient;
import java.util.List;
import java.util.Map;

public interface PaymentInterfaceDAO {

    int insertPaymentInterfaceDetails(PaymentInterface paymentInterface);

    public List<PaymentInterface> getAllPaymentInterfaceDetails(Patient patient);

    /**
     * <pre>
     * Calculates and returns the points redeemed by a particular patient.
     * </pre>
     *
     * @param patientId
     * @return voucher points
     */
    public int getVoucherRedemptionPoints(int patientId);

    public int findMaxBillingId();
    
    /**
     * <pre>
     * Generates map of Payment corresponding to billing id.
     * </pre>
     * 
     * @param billingIdList
     * @return map
     */
    public Map<Integer, PaymentInterface> getPaymentDetails(List<Integer> billingIdList);
}
