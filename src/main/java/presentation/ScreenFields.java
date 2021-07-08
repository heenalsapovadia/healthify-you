package presentation;

/**
 * <pre>
 * Contains all fields required on all screens
 * within the scope of the application.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class ScreenFields {
	
	private ScreenFields() {}
	
	/* Pharma invoice screen fields */
	public static final String receiptNo = "Receipt No.";
	public static final String pharmaName = "Pharma Name";
	public static final String address = "Address";
	public static final String contact = "Contact No.";
	public static final String dateTime = "Date/Time";
	public static final String mop = "Payment Mode";
	public static final String description = "Description";
	public static final String quantity = "Quantity";
	public static final String unitprice = "Unit Price";
	public static final String total = "Total Amount";
	public static final String grandTotal = "Grand Total";
	public static final String descriptionExtras = "(Name, Dosage, Manufacturer)";
	
	/* Invoice screen fields */
	public static final String pharmSuppliesByDate = "Search pharmaceutical supplies by date";
	public static final String exit = "Exit";
	public static final String selection = "Please enter your selection below:";
	public static final String dateInput = "Please enter a date (yyyy-mm-dd):";

	public static final String appointmentId = "Appointment ID";
	public static final String medicineNumber = "Number of medicines to prescribe";
	public static final String medicineName = "Medicine Name";
	public static final String morningDose = "Morning Dose";
	public static final String afternoonDose = "Afternoon Dose";
	public static final String eveningDose = "Evening Dose";
	public static final String medicinePrescribeMessage = "Medicines prescribed successfully!";
}
