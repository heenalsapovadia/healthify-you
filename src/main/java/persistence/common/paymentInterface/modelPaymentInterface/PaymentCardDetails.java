package persistence.common.paymentInterface.modelPaymentInterface;

public class PaymentCardDetails {
    int cardNumber;

    public int getExpirtyDate() {
        return expirtyDate;
    }

    public void setExpirtyDate(int expirtyDate) {
        this.expirtyDate = expirtyDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    int expirtyDate;
    int cvvNumber;

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}
