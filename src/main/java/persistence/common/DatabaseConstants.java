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
    public static final String BOOKED_ON_DATE = "booked_on_date";
    public static final String BOOKED_FOR_DATE = "booked_for_date";
    public static final String RESCHEDULED_DATE = "rescheduled_date";

    /* Patient Table constants */
    public static final String PATIENT_ID = "patient_id";
    public static final String PATIENT_GENDER = "patient_gender";
    public static final String PATIENT_DOB = "patient_dob";
    public static final String PATIENT_ADDRESS = "patient_address";

    /* Doctor Table constants */
    public static final String DOCTOR_ID = "doctor_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    /* Doctor Availability Table constants */
    public static final String WEEKDAY = "weekday";

    /* Prescription Table Constants */
    public static final String DOCTOR_NAME = "doctor_name";
    public static final String MEDICINE_NAME = "medicine_name";
    public static final String MORNING_DOSE = "morning_dose";
    public static final String AFTERNOON_DOSE = "afternoon_dose";
    public static final String EVENING_DOSE = "evening_dose";
    public static final String DOSAGE_DAYS = "dosage_days";
    public static final String PRESCRIPTION_DATE = "prescription_date";
    public static final String PRESCRIPTION_ID = "prescription_id";

    /* Lab Check Table Constants */
    public static final String HEALTHCHECK_ID = "healthcheck_id";
    public static final String CHECKUP_NAME = "checkup_name";
    public static final String CHECKUP_TYPE = "checkup_type";
    public static final String DESCRIPTION = "description";
    public static final String CHARGES = "charges";

    /* Lab Check Booking Table Constants */
    public static final String CHECKUP_ID = "checkup_id";

    /* Vaccine Table constants */
    public static final String VACCINE_NAME = "vaccine_name";
    public static final String VACCINE_ID = "vaccine_id";
    public static final String AGE_GROUP_IN_YEARS = "age_group_in_years";

    /* Billing table constants */
    public static final String BILLING_ID = "billing_id";
}
