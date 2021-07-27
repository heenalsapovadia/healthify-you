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

  private ScreenFields() {
  }

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
  public static final String USER_EMAIL_INPUT = "Enter your email address";
  public static final String USER_PASSWORD_INPUT = "Enter your password";
  public static final String EMAILID_OUTPUT= "Email-ID";
  public static final String PASSWORD_OUTPUT = "Password";
  public static final String GENDER = "Enter your Gender (M/F/O)";
  public static final String GET_INPUT = "Enter your details below";
  public static final String GO_BACK = "Go back";
  public static final String PROCEED= "Confirm to proceed";
  public static final String CONTACT_BEGIN_WITH = "The contact number should begin with 902";
  public static final String CONTACT_LENGTH = "Contact number should be 10 digits long!";
  public static final String SUCCESS_LOGIN= "Successfully logged in!";

  /* Immunization screenFields */
  public static final String MONTH = "Month";
  public static final String SLOTS = "Slots assigned";
  public static final String IMMUNIZATION_MANAGEMENT = "Immunization Management";
  public static final String SLOT_MANAGEMENT = "Slot Management";
  public static final String VACCINATION_STATS = "Check immunization statistics";
  public static final String SLOTS_ASSIGNED= "Slots successfully assigned";
  public static final String AVAILABLE_VACCINES = "Vaccines available";
  public static final String DOSES = "Doses administered";
  public static final String AGE_GROUP = "Age group in years";
  public static final String ELIGIBILITY_CHECK = "Eligibility check ...";
  public static final String SUCCESS = "Success";
  public static final String FAIL = "Eligibility Fail";
  public static final String MOST_DEMANDED_VACCINE = "Most Demanded Vaccine";
  public static final String MOST_VACCINATED_AGEGROUP = "Most vaccinated Age Group";
  public static final String MOST_VACCINATED_GENDER = "Most vaccinated Gender";
  public static final String MOST_VACCINATED_AREA = "Most vaccinated Area";

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

  /* Booking Dashboard Booking fields */
  public static final String APPOINTMENT_WITH_DOCTOR = "Book an appointment with doctor";
  public static final String BOOK_LAB_TEST = "Book a Lab Test/Health Check Up";
  public static final String BOOK_IMMUNIZATION = "Book an Immunization";
  public static final String bookBloodBankService = "Book a Blood Bank service";
  public static final String yourBloodGroup = "Please enter your blood group:";

  /* Blood Bank Registration fields */
  public static final String REGISTER_PATIENT_FOR_BLOOD_DONATION = "Register for Blood Donation";
  public static final String VIEW_DONATION_HISTORY = "My Donations";
  public static final String EXIT_FROM_BLOOD_DONATION = "Exit";
  public static final String CHECK_ELIGIBILITY = "Checking Eligibility....";
  public static final String PATIENT_IS_ELIGIBLE = "Eligible...No previous donations found for the Patient!";
  public static final String REGISTERING_PATIENT = "Registering Patient!";
  public static final String TOKEN_GENERATED = "Your Token is: ";
  public static final String DONATION_DATE = "Donation Date: ";
  public static final String DONATION_RECORD = "Record of patient exists. Please enter valid blood group.";
  public static final String PATIENT_ALREADY_DONATED = "Patient has already Donated in last 6 months and is not eligible to donate again.";
  public static final String BLOOD_DONATION_CRITERIA = "Blood donation criteria: a) Minimum 6 month after previous donation, b) Blood report should be normal and available.";
  public static final String HOURS_OF_OPERATION = "We operate on Tuesdays and Sundays. Visit anytime.";
  public static final String REPORTS_ARE_NORMAL_FOR_BLOOD_DONATIONS = "Reports are not normal for Blood donation. Sorry please donate after recovery. ";
  public static final String NO_DONATION_RECORDS_FOUND = "No donation record exits for the patient.";
  public static final String PATIENT_DONATED_SIX_MONTH_BEFORE = "Patient donation history is more than 6 month so now registering them..";
  public static final String YOUR_BLOOD_GROUP = "Please enter your blood group: [Blood Group format - 'A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O' ]";


  /* Payment Interface fields */
  public static final String CARD_NUMBER = "Enter your card number(12 digit - start with 512): ";
  public static final String EXPIRY_DATE = "Enter expiry date(yyyy-mm): ";
  public static final String CVV_NUMBER = "Enter cvv number(3 digit - start with 9): ";
  public static final String CHECKOUT_AMOUNT = "Checkout Amount: ";
  public static final String REDEEM_VOUCHER = "Reedemable Voucher: ";
  public static final String VOUCHER_ID_OPTION1 = "1. Enter 1 to proceed without voucher and make payment through Credit Card. ";
  public static final String VOUCHER_ID_OPTION2 = "2. Enter 2 to proceed with voucher. ";
  public static final String ENTER_VOUCHERID = "Enter Voucher Id: ";
  public static final String CONTINUE_TO_PAYMENT = "Continue To Payment";
  public static final String VOUCHER_POINTS_USED_FOR_BILLING = " -- Voucher points used for billing and billing id is: ";
  public static final String BALANCE_REMAINING_FROM_CHECKOUT = "Remaining balance of ";
  public static final String REMAINING_BALANCE_PAID_THROUGH_CREDITCARD =  " paid through credit card and billing id is: ";
  public static final String BILLING_ID_GENERATED = "Payment Successful and billing id is: ";
  public static final String INVALID_VOUCHER = "Invalid Voucher. Please pay through credit card.";
  public static final String BILLING_ID =  "Billing id is: ";
  public static final String VOUCHER_NOT_AVAILABLE = "Voucher not available. Please enter details again.";
  public static final String BILL_SUCCESSFUL_FOR_LABTEST =  "Billing successful for Lab Health Check Booking. ";
  public static final String BILL_SUCCESSFUL_FOR_DOCTOR_APPOINTMENT = "Billing successful for Doctor Appointment.";
  public static final String BILL_SUCCESSFUL_FOR_REQUEST_MEDICATION = "Billing successful for Request Medication.";
  public static final String BILL_SUCCESSFUL_FOR_PHARMARCY = "Billing successful for Pharmacy.";

  /* Credit Card Validation field for Payment Interface*/
  public static final String ENTER_VALID_CREDITCARD_Number = "Should start with 512. Please enter again.";
  public static final String MIN_CARD_NUMBER = "Card number should be 12 digit long";
  public static final String WRONG_CARD_NUMBER = "Wrong credit card number. Please enter again";
  public static final String ENTER_VALID_CVV = "Should start with 9. Please enter again.";
  public static final String MIN_CVV_NUMBER = "Cvv number should be 3 digit long";
  public static final String WRONG_CVV_NUMBER = "Wrong Cvv. Please start with 9 and make sure its 3 digit long.";
  public static final String WRONG_DATE = "Wrong date. Please enter again";


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
  public static final String EXPIRY_DATE_OF_CARD = "Expiry Date";
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

  /* Doctor appointmnent booking fields */
  /* by name */
  public static final String DOCTOR_NAME_INPUT = "Enter doctor's firstname or lastname (case-insensitive)";
  public static final String DOCTOR_IDENTIFIER_INPUT = "Enter doctor identifier from the list given below";
  public static final String DOCTOR_AVAILABILITY_DATE_INPUT = "Next dates available for the requested doctor are as given below:";
  public static final String DOCTOR_AVAILABILITY_DATE_SELECTION = "Please enter your choice from the dates mentioned above";
  public static final String EMAIL_ADDRESS_INPUT = "Enter registered patient email address for whom the appointment should be booked!";

  /* by specialization */
  public static final String DOCTOR_SPECIALIZATION_INPUT = "Enter specialization (ENT SPECIALIST | CARDIOLOGIST | PHYSICIAN | OTHERS) (case-insensitive)";

  /* Request Medication fields */
  public static final String ENTER_PRESCRIPTION_ID = "Enter Prescription ID: [Only enter numbers]";
  public static final String GETTING_PRESCRIPTION_ID = "Enter 1 to insert Prescription ID";
  public static final String CURRENT_LOGGEDIN_PATIENT = "Current logged in Patient Name = ";
  public static final String INCORRECT_PRESCRIPTION_ID = "This is Incorrect prescription id for logged in patient. Please try again.";
  public static final String PRESCRIBED_LIST_OF_MEDICINE = "Prescribed list of above prescription id is below:";
  public static final String NAME_OF_MEDICINE = "Medicine Name: ";
  public static final String DOSE_OF_MEDICINE = "Medicine Dose: ";
  public static final String MEDICINE_DOSAGE_DAYS =  "Dosage is for : ";
  public static final String DAYS = " days";
  public static final String PAYMENT_NEEDED_FOR_PRESCRIPTION = "Payment needed of amount ";
  public static final String TOTAL_DOSE = "Total dose requirement - ";
  public static final String ENOUGH_DOSES_OF_MEDICINE_AVAILABLE = "Enough dose available in the pharmacy stock.";
  public static final String CHECKOUT_AMOUNT_NOT_ELIGIBLE = "Checkout amount is not eligible for payment.";
  public static final String NOT_ENOUGH_DOSES = "Not enough dose available in inventory. Unable to add to checkout amount.";
  public static final String EXIT_FROM_REQUEST_MEDICATION = "EXIT";

  /* Scheduled Appoinments fields */
  public static final String CURRENT_APPOINTMENT_DATE = "Enter the date of appointment(yyyy-mm-dd): ";
  public static final String WRONG_DATE_OF_APPOINTMENT = "Wrong date";
  public static final String INVALID_DATE_FORMAT_FOR_SCHEDULEDAPPOINTMENTS = "Invalid Date Format";
  public static final String NO_SCHEDULED_APPOINTMENTS = "No scheduled appointments on the entered date.";
  public static final String ENTER_TO_EXIT = "Enter 1 to Exit:";
  public static final String PATIENT_NAME_IN_APPOINTMENT  = "Patient Name";
  public static final String PATIENT_AGE  = "Patient Age ";


}
