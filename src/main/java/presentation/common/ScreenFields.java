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
	public static final String RECEIPT_NO = "Receipt No.";
	public static final String PHARMA_NAME = "Pharma Name";
	public static final String ADDRESS = "Address";
	public static final String CONTACT = "Contact No.";
	public static final String DATETIME = "Date/Time";
	public static final String MOP = "Payment Mode";
	public static final String DESCRIPTION = "Description";
	public static final String QUANTITY = "Quantity";
	public static final String UNIT_PRICE = "Unit Price";
	public static final String TOTAL = "Total Amount";
	public static final String GRAND_TOTAL = "Grand Total";
	public static final String DESCRIPTION_EXTRAS = "(Name, Dosage, Manufacturer)";
	
	/* Invoice screen fields */
	public static final String PHARMA_SUPPLIES_BY_DATE = "Search pharmaceutical supplies by date";
	public static final String EXIT = "Exit";
	public static final String SELECTION = "Please enter your selection below:";
	public static final String DATEINPUT = "Please enter a date (yyyy-mm-dd):";

	/* Medicine Prescription fields */
	public static final String APPOINTMENT_ID = "Appointment ID";
	public static final String MEDICINE_NUMBER = "Number of medicines to prescribe";
	public static final String MEDICINE_NAME = "Medicine Name";
	public static final String MORNING_DOSE = "Morning Dose";
	public static final String AFTERNOON_DOSE = "Afternoon Dose";
	public static final String EVENING_DOSE = "Evening Dose";
	public static final String DOSAGE_DAYS = "Number of days";
	public static final String MEDICINE_PRESCRIBE_MESSAGE = "Medicines prescribed successfully!";

	/* Registration screen fields */
	public static final String EMAIL_INPUT = "Enter email address";
	public static final String FIRST_NAME_INPUT = "Enter first name";
	public static final String LAST_NAME_INPUT = "Enter last name";
	public static final String PASSWORD_INPUT = "Enter password for account! (should contain atleast one digit, a lowercase alphabet, an uppercase alphabet, no white-spaces, at least 8 characters and at most 20 characters long, and any character from ?,=,.,*,[,@,#,$,%,^,&,-,+,=,(,)";
	public static final String JOINING_DATE_INPUT = "Enter doctor's joining date (YYYY-MM-DD)";
	public static final String DEGREE_INPUT = "Enter doctor's graduation degree";
	public static final String SPECIALIZATION_INPUT = "Enter doctor's specialization (ENT SPECIALIST | CARDIOLOGIST | PHYSICIAN | OTHERS)";
	public static final String BIRTH_DATE_INPUT = "Enter birth date (YYYY-MM-DD)";
	public static final String CONTACT_INPUT = "Enter contact number (902#######)";
	public static final String CITY_INPUT = "Enter city of residence";
	
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
	public static final String availablevaccines = "Vaccines available";
	public static final String doses = "Doses administered";
	public static final String agegroup= "Age group in years";
	public static final String vaccinename = "Name of Vaccine";
	public static final String elibilitycheck = "Eligibility check ...";
	public static final String success = "Success";
	public static final String fail = "Eligibility Fail";
	public static final String mostDemandedVaccine = "Most Demanded Vaccine";
	public static final String mostVaccinatedAge = "Most vaccinated Age Group";
	public static final String mostVaccinatedGender = "Most vaccinated Gender";
	public static final String mostVaccinatedArea = "Most vaccinated Area";

	/* Doctor recommendation screen fields */
	public static final String SELECTION_FIELD = "Make selection from the options below:\n";
	public static final String SELECTION_OPTION_1 = "1. Select the symptoms for recommendation";
	public static final String SELECTION_OPTION_2 = "2. Exit";
	public static final String SYMPTOM_SELECTION_FIELD = "Select one of the options below:";
	public static final String SYMPTOM_OPTION_1 = "1. Cough";
	public static final String SYMPTOM_OPTION_2 = "2. Cold";
	public static final String SYMPTOM_OPTION_3 = "3. Fever";
	public static final String SYMPTOM_OPTION_4 = "4. Nausea";
	public static final String SYMPTOM_OPTION_5 = "5. Digestive issues";
	public static final String SUPPORT_INPUT = "Enter the support for recommendation (enter integer value only)";
	public static final String RECOMMENDATION_INPUT = "Enter the number of recommendations";

	/* Main Menu Fields */
	public static final String LOGIN = "Login";
	public static final String SIGNUP = "Sign Up";

	/* Admin Dashboard Fields */
	public static final String GET_INVOICES = "Get Invoices";
	public static final String GET_RECOMMENDATIONS = "Check recommendations for blood bank management";
	public static final String REGISTER_DOCTOR = "Registration of accounts for doctors";
	public static final String LOGOUT = "Logout";
	public static final String LOGOUT_MESSAGE = "You are being logged out!";
	public static final String APPLICATION_TERMINATION_MESSAGE = "The application will now stop!";

	/* Doctor Dashboard Fields */
	public static final String PRESCRIBE_MEDICATION = "Prescribe medication";
	public static final String VIEW_APPOINTMENTS = "View appointments";

	/* Patient Dashboard Fields */
	public static final String BOOK = "Make a Booking";
	public static final String INVOICES = "Invoices";
	public static final String REQUEST_MEDICATION = "Request Medication";
	public static final String VOUCHERS = "Redeemable Vouchers";

	/* Lab Test Booking fields */
	public static final String ENTER_OPTION = "Enter your option";
	public static final String VIEW_DETAILS = "View more details";
	public static final String BACK_TO_BOOKING = "Go back to booking";
	public static final String HEALTH_CHECK_NUMBER = "Enter the health check number";
	public static final String LAB_CHECK_RECOMMENDATION = "Recommendations for LabChecks are";
	public static final String HEALTH_CHECK_ID = "HealthCheck ID";

	/* Booking Dashboard  Booking fields */
	public static final String APPOINTMENT_WITH_DOCTOR = "Book an appointment with doctor";
	public static final String BOOK_LAB_TEST = "Book a Lab Test/Health Check Up";
	public static final String bookImmunization = "Book an Immunization";
	public static final String bookBloodBankService = "Book a Blood Bank service";

	/* Blood Bank Registration fields */
	public static final String REGISTER_PATIENT_FOR_BLOOD_DONATION = "Register for Blood Donation";
	public static final String VIEW_DONATION_HISTORY = "My Donations";
	public static final String EXIT_FROM_BLOOD_DONATION = "Exit";
	public static final String ENTER_YOUR_SELECTION = "Please enter your selection below:";
	public static final String CHECK_ELIGIBILITY = "Checking Eligibility....";
	public static final String PATIENT_IS_ELIGIBLE = "Eligible...No previous donations found for the Patient!";
	public static final String REGISTERING_PATIENT = "Registering Patient!";
	public static final String TOKEN_GENERATED = "Your Token is: ";
	public static final String DONATION_DATE = "Donation Date: ";
	public static final String DONATION_RECORD = "Record of patient exists. Please enter valid blood group.";
	public static final String PATIENT_ALREADY_DONATED = "Patient has already Donated in last 6 months and is not eligible to donate again.";
	public static final String BLOOD_DONATION_CRITERIA = "Blood donation criteria: a) Minimum 6 month after previous donation, b) Blood report should be normal.";
	public static final String REPORT_NORMAL = "Eligible....Patient test reports are normal...";
	public static final String HOURS_OF_OPERATION = "We operate on Tuesdays and Sundays. Visit anytime.";
	public static final String REPORTS_ARE_NORMAL_FOR_BLOOD_DONATIONS = "Reports are not normal for Blood donation. Sorry please donate after recovery. ";
	public static final String NO_DONTION_RECORDS_FOUND = "No donation record exits for the patient.";
	public static final String PATIENT_DONATED_SIX_MONTH_BEFORE = "Patient donation history is more than 6 month so now registering them..";
	public static final String YOUR_BLOOD_GROUP = "Please enter your blood group:";


	/* Payment Interface fields */
	public static final String cardNumber = "Enter your card number(12 digit - start with 512): ";
    public static final String expirydate = "Enter expiry date(yyyy-mm): ";
    public static final String cvvNumber = "Enter cvv number(3 digit - start with 9): ";
    public static final String checkoutAmount = "Checkout Amount: ";
    public static final String redeemVoucher = "Reedemable Voucher: ";
	public static final String voucherIdOption1= "1. Enter 1 to proceed without voucher and make payment through Credit Card. ";
	public static final String voucherIdOption2= "2. Enter 2 to proceed with voucher. ";
	public static final String enterVoucherId= "Enter Voucher Id: ";
    public static final String paymentExit = "3. Exit: ";
    public static final String voucherId = "1. Enter voucher id or enter 1 to proceed: ";
    public static final String CONTINUE_TO_PAYMENT = "Continue To Payment";

	/* Blood Bank recommendation screen fields */
	public static final String BLOOD_GROUP_INPUT = "Please enter the primary blood group to be ordered from the list - A+, AB+, B+, O+, A-, AB-, B-, O- (case-insensitive)";
	public static final String NUM_REC_INPUT = "Please enter the number of recommendations";
	public static final String BLOOD_BANK_MESSAGE = "The blood groups displayed are the in the order of most frequently ordered to least frequently ordered!";

	/* Redeemable Voucher Fields */
	public static final String VIEW_VOUCHER = "View Redeemable Vouchers";
	public static final String POINTS_AVAILABLE = "Points Available";
	public static final String POINTS_REDEEMED = "Points Redeemed";
	
	/* View Reports Fields */
	public static final String VIEW_REPORTS = "View Reports";
	public static final String VIEW_REPORTS_BY_TEST = "View Reports by test";
	public static final String VIEW_REPORTS_BY_DATE_RANGE = "View Reports by date range";
	public static final String VIEW_REPORTS_BY_PARTICULAR_DATE = "View Reports by a particular date";
	public static final String START_DATE = "Enter start date (yyyy-mm-dd)";
	public static final String END_DATE = "Enter end date (yyyy-mm-dd)";
	public static final String DATE = "Date";
	public static final String DATE_OF_COLLECTION = "Date of Collection";
	
	/* View Reports by Test Fields */
	public static final String BLOOD_TEST = "Blood Test";
	public static final String KIDNEY_TEST = "Kidney Test";
	public static final String LIVER_TEST = "Liver Test";
	public static final String VISION_TEST = "Vision Test";
	public static final String COVID_TEST = "Covid Test";
	
	/* Patient Invoice Fields */
	public static final String APPT_BY_DATE = "Search appointment by date (yyyy-mm-dd)";
	public static final String PHARMA_BY_DATE = "Search pharmacy receipt by date (yyyy-mm-dd)";
	public static final String IMMUNIZATION_BY_DATE = "Search immunization appointment receipt by date (yyyy-mm-dd)";
	public static final String LAB_TEST_BY_DATE = "Search laboratory test receipt by date (yyyy-mm-dd)";
	public static final String APPOINTMENT_NO = "Appointment No.";
	public static final String PRESCRIPTION_ID = "Prescription No.";
	public static final String IMMUNIZATION_APPT_ID = "Immunization Appointment No.";
	public static final String LAB_TEST_ID = "Laboratory Test No.";
	public static final String BILL_ID = "Bill No.";
	public static final String PATIENT_NAME = "Patient's Name";
	public static final String AGE_SEX = "Age/Sex";
	public static final String DOCTOR_NAME = "Doctor's Name";
	public static final String DEPARTMENT = "Department";
	public static final String BILL_AMT = "Bill Amount (CAD)";
	public static final String AMOUNT = "Amount (CAD)";
	public static final String CREATED_ON = "Created On";
	public static final String EXPIRY_DATE = "Expiry Date";
	public static final String VACCINE_NAME = "Vaccine Name";
	public static final String LAB_TEST_NAME = "Laboratory Test Name";

	/* Vaccine Demand Statistics fields */
	public static final String CHECK_DOSE_STATISTICS = "Do you want to check the statistics of doses administered ?";
	public static final String ENTER_MONTHS = "Enter number of months";

	/* Doctor Availability fields */
	public static final String DOCTOR_AVAILABILITY = "Doctor is available on the following dates : ";
	public static final String DATES_NEXT_WEEK = "Do you want dates for the week after ?";
	public static final String APPOINTMENT_ID_TO_RESCHEDULE = "Enter appointment Id to reschedule : ";
	public static final String UPCOMING_APPOINTMENTS = "Your upcoming doctor appointments are : ";
	public static final String APPOINTMENT_DATE = "DoctorName";
	public static final String APPOINTMENT_TO_RESCHEDULE = "Appointment to be rescheduled";

}
