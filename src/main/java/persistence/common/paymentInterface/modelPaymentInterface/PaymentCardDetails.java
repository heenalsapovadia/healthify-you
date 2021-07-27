package persistence.common.paymentInterface.modelPaymentInterface;
/**
 * <pre>
 *
 * PaymentInterface Payment Card details model class
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class PaymentCardDetails {
    Long cardNumber;
    String expirtyDate;
    Long cvvNumber;

    public void setExpirtyDate(String expirtyDate) {
        this.expirtyDate = expirtyDate;
    }

    public void setCvvNumber(Long cvvNumber) {
        this.cvvNumber = cvvNumber; }

    public void setCardNumber(Long cardNumber) {
        this.cvvNumber = cardNumber;
    }
}
