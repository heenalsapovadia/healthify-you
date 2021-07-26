package persistence.common;

/**
 * <pre>
 * Contains all the common constants representing Database columns
 * required throughout the application.
 * </pre>
 *
 * @author G12
 *
 */
public class DatabaseConstants {

    private DatabaseConstants() {}

    /* Common Appointment constants */
    public static final String APPOINTMENT_ID = "appointment_id";
    public static final String DOCTOR_ID = "doctor_id";
    public static final String BOOKED_ON_DATE = "booked_on_date";
    public static final String BOOKED_FOR_DATE = "booked_for_date";
    public static final String RESCHEDULED_DATE = "rescheduled_date";

    /* Patient Table constants */
    public static final String PATIENT_ID = "patient_id";
    public static final String PATIENT_GENDER = "patient_gender";
    public static final String PATIENT_DOB = "patient_dob";
    public static final String PATIENT_ADDRESS = "patient_address";

    /* Vaccine Table constants */
    public static final String VACCINE_NAME = "vaccine_name";
    public static final String VACCINE_ID = "vaccine_id";
    public static final String AGE_GROUP_IN_YEARS = "age_group_in_years";

    /* Billing table constants */
    public static final String BILLING_ID = "billing_id";

    /* Blood Donation Table Constants*/

}
