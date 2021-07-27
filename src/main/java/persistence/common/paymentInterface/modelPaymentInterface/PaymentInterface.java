package persistence.common.paymentInterface.modelPaymentInterface;

import java.util.Date;
/**
 * <pre>
 *
 * PaymentInterface model class
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class PaymentInterface {
    private int billing_id;
    private int patient_id;
    private String voucher_id;
    private Date billing_date;
    public static PaymentBillingCategory bill_category;
    private double bill_amount;
    public enum payment_mode{C,CC,DC};
    public payment_mode currentPaymentMode;
    private double discount;
    private Date created_on;
    public enum status{A,C,R};
    public status statusOfPayment;
    private Date voucher_redemption_date;


    public payment_mode getCurrentPaymentMode() {
        return currentPaymentMode;
    }

    public void setCurrentPaymentMode(payment_mode currentPaymentMode) {
        this.currentPaymentMode = currentPaymentMode;
    }

    public status getStatusOfPayment() {
        return statusOfPayment;
    }

    public void setStatusOfPayment(status statusOfPayment) {
        this.statusOfPayment = statusOfPayment;
    }

    public int getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(int billing_id) {
        this.billing_id = billing_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public Date getBilling_date() {
        return billing_date;
    }

    public void setBilling_date(Date billing_date) {
        this.billing_date = billing_date;
    }

    public PaymentBillingCategory getBill_category() {
        return bill_category;
    }

    public void setBill_category(PaymentBillingCategory bill_category) {
        this.bill_category = bill_category;
    }

    public double getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(double bill_amount) {
        this.bill_amount = bill_amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getVoucher_redemption_date() {
        return voucher_redemption_date;
    }

    public void setVoucher_redemption_date(Date voucher_redemption_date) {
        this.voucher_redemption_date = voucher_redemption_date;
    }
}
