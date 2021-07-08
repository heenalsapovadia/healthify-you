/**
 * 
 */
package persistence.admin.model;


/**
 * <pre>
 * Enum to capture mode of payment.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public enum PaymentMode {
	Cash, Cheque, Other;
	String mop;
	PaymentMode(){}
	public static PaymentMode getMop(String paymentModeFromDB) {
		if("C".equals(paymentModeFromDB))
			return Cash;
		else if("CH".equals(paymentModeFromDB))
			return Cheque;
		return Other;
	}
}