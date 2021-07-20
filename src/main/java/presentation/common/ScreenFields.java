package presentation.common;

/**
 * <pre>
 * Contains all fields required on all screens
 * within the scope of the application.
 * </pre>
 * 
 * @author G12
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

	/* Medicine Prescription fields */
	public static final String appointmentId = "Appointment ID";
	public static final String medicineNumber = "Number of medicines to prescribe";
	public static final String medicineName = "Medicine Name";
	public static final String morningDose = "Morning Dose";
	public static final String afternoonDose = "Afternoon Dose";
	public static final String eveningDose = "Evening Dose";
	public static final String medicinePrescribeMessage = "Medicines prescribed successfully!";

	/* Registration screen fields */
	public static final String emailInput = "Enter email address";
	public static final String firstNameInput = "Enter first name";
	public static final String lastNameInput = "Enter last name";
	public static final String passwordInput = "Enter password for account! (should contain atleast one digit, a lowercase alphabet, an uppercase alphabet, no white-spaces, at least 8 characters and at most 20 characters long, and any character from ?,=,.,*,[,@,#,$,%,^,&,-,+,=,(,)";
	public static final String joiningDateInput = "Enter doctor's joining date (YYYY-MM-DD)";
	public static final String degreeInput = "Enter doctor's graduation degree";
	public static final String specializationInput = "Enter doctor's specialization (if any, NA if not applicable)";
	public static final String birthDateInput = "Enter birth date (YYYY-MM-DD)";
	public static final String contactInput = "Enter contact number (902#######)";
	public static final String cityInput = "Enter city of residence";
	
    /* Login screen fields */
	public static final String userEmailInput = "Enter your email address";
	public static final String userPasswordInput = "Enter your password";
	public static final String emailIdOutput= "Email-ID";
	public static final String passwordOutput= "Password";
	public static final String gender= "Enter your Gender (M/F/O)";
	public static final String getInput = "Enter your details below";
	public static final String goBack = "Go back";
	public static final String proceed ="Confirm to proceed";
	public static final String contactBeginWith = "The contact number should begin with 902";
	public static final String contactLength = "Contact number should be 10 digits long!";
    public static final String successLogin = "Successfully logged in!";
    
	/*Immunization screenFields*/
	public static final String month = "Month";
	public static final String slots = "Slots assigned";
	public static final String immunizationmanagement = "Immunization Management";
	public static final String slotmanagement = "Slot Management";
	public static final String vaccinationstats = "Check immunization statistics";
	public static final String slotsassigned = "Slots successfully assigned";
	
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

	/* Main Menu Fields */
	public static final String login = "Login";
	public static final String signUp = "Sign Up";

	/* Admin Dashboard Fields */
	public static final String getInvoices = "Get Invoices";
	public static final String getRecommendations = "Check recommendations for blood bank management";
	public static final String registerDoctor = "Registration of accounts for doctors";
	public static final String logout = "Logout";
	public static final String logoutMessage = "You are being logged out!";
	public static final String applicationTerminationMessage = "The application will now stop!";

	/* Doctor Dashboard Fields */
	public static final String prescribeMedicine = "Prescribe medication";
	public static final String viewAppointment = "View appointments";

	/* Patient Dashboard Fields */
	public static final String book = "Make a Booking";
	public static final String invoices = "Invoices";
	public static final String requestMedication = "Request Medication";
	public static final String vouchers = "Redeemable Vouchers";

	/* Lab Test Booking fields */
	public static final String enterOption = "Enter your option";
	public static final String viewDetails = "View more details";
	public static final String backToBooking = "Go back to booking";
	public static final String checkId = "Enter the health check number";
	public static final String labCheckRecommendation = "Recommendations for LabChecks are";

	/* Booking Dashboard  Booking fields */
	public static final String appointmentWithDoctor = "Book an appointment with doctor";
	public static final String bookLabTest = "Book a Lab Test/Health Check Up";
	public static final String bookImmunization = "Book am Immunization";
	public static final String bookBloodBankService = "Book a Blood Bank service";
	public static final String yourBloodGroup = "Please enter your blood group:";

	/* Blood Bank Registration fields */
	public static final String registerPatientForBloodDonation = "1. Register for Blood Donation";
	public static final String viewDonationHistory = "2. My Donations";
	public static final String enterYourSelection = "Please enter your selection below:";
	public static final String checkingEligibility = "Checking Eligibility....";
	public static final String patientIsEligible = "Eligible...No previous donations found for the Patient!";
	public static final String registeringPatient = "Registering Patient!";
	public static final String tokenGenerated = "Your Token is: ";
	public static final String donationDate = "Donation Date: ";
	public static final String donationRecord = "Record of patient exists. Please enter valid blood group.";
	public static final String patientAlreadyDonated = "Patient has already Donated in last 6 months and is not eligible to donate again.";





}
