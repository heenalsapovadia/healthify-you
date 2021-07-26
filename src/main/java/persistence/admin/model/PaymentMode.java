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
	
	Cash, Cheque, CreditCard, DebitCard, Other;
	
	String mop;
	
	PaymentMode(){}
	
	public static PaymentMode getMop(String paymentModeFromDB) {
		if("C".equals(paymentModeFromDB)) {
			return Cash;
		}
		else if("CH".equals(paymentModeFromDB)) {
			return Cheque;
		}
		else if("CC".equals(paymentModeFromDB)) {
			return CreditCard;
		}
		else if("DC".equals(paymentModeFromDB)) {
			return DebitCard;
		}
		
		return Other;
	}
}