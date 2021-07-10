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

	/* Doctor registration screen fields */
	public static final String emailInput = "Enter your email address";
	public static final String firstNameInput = "Enter your first name";
	public static final String lastNameInput = "Enter your first name";
	public static final String passwordInput = "Enter your password! (should contain atleast one digit, a lowercase alphabet, an uppercase alphabet, no white-spaces, at least 8 characters and at most 20 characters long, and any character from ?,=,.,*,[,@,#,$,%,^,&,-,+,=,(,)";
	public static final String joiningDateInput = "Enter your joining date (YYYY-MM-DD)";
	public static final String degreeInput = "Enter your graduation degree";
	public static final String specializationInput = "Enter your specialization (if any, NA if not applicable)";
	public static final String birthDateInput = "Enter your birth date (YYYY-MM-DD)";
	public static final String contactInput = "Enter your contact number (902######)";
	public static final String cityInput = "Enter your city of residence";
	
    /* Login screen fields */
	public static final String emailIdOutput= "Email-ID";
	public static final String passwordOutput= "Password";
	public static final String gender= "Enter your Gender (M/F/O)";
	public static final String getInput = "Enter your details below";
	public static final String goBack = "Go back";
	public static final String proceed ="Continue";
	public static final String contactBeginWith = "The contact number should begin with 902";
	public static final String contactLength = "Contact number should be 10 digits long!";

	/*Immunization screenFields*/
	public static final String month = "Month";
	public static final String slots = "Slots assigned";
	
	/* Doctor recommendation screen fields */
	public static final String selectionField= "Make selection from the options below:\n";
	public static final String selectionOption1= "1. Select the symptoms for recommendation";
	public static final String selectionOption2= "2. Exit";
	public static final String symptomSelectionField= "Select one of the options below:";
	public static final String symptomOption1= "1. Cough";
	public static final String symptomOption2= "2. Cold";
	public static final String symptomOption3= "3. Fever";
	public static final String symptomOption4= "4. Nausea";
	public static final String symptomOption5= "5. Digestive issues";
	public static final String supportInput= "Enter the support for recommendation (enter integer value only)";
	public static final String recommendationInput= "Enter the number of recommendations";
    
	
}
