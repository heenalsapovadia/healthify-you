package persistence.common.paymentInterface.modelPaymentInterface;

public class PaymentCardDetails {
    Long cardNumber;
    String expirtyDate;
    Long cvvNumber;

    public String getExpirtyDate() {
        return expirtyDate;
    }

    public void setExpirtyDate(String expirtyDate) {
        this.expirtyDate = expirtyDate;
    }

    public Long getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(Long cvvNumber) {
        this.cvvNumber = cvvNumber; }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cvvNumber = cardNumber;
    }
}
