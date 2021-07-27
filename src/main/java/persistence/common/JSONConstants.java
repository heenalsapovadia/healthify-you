package persistence.common;

/**
 * <pre>
 * Contains all the common constants representing JSON file keys
 * required throughout the application.
 * </pre>
 *
 * @author G12
 *
 */
public class JSONConstants {

    private JSONConstants() {}

    /* Ideal reports Constants */
    public static final String IDEAL_REPORTS_FILENAME = "IdealReports.json";
    public static final String RBC_MIN = "RBCMin";
    public static final String WBC_MIN = "WBCMin";
    public static final String PLATELETS_MIN = "plateletsMin";
    public static final String HAEMOGLOBIN_MIN = "HaemoglobinMin";
    public static final String HEMATOCRIT_MIN = "HematocritMin";
    public static final String RBC_MAX = "RBCMax";
    public static final String WBC_MAX = "WBCMax";
    public static final String PLATELETS_MAX = "plateletsMax";
    public static final String HAEMOGLOBIN_MAX = "HaemoglobinMax";
    public static final String HEMATOCRIT_MAX = "HematocritMax";
    public static final String BUN_MIN = "BUNMin";
    public static final String BUN_MAX = "BUNMax";
    public static final String CREATININE_MAX = "CreatinineMax";
    public static final String ALBUMIN_MIN = "AlbuminMin";
    public static final String BILIRUBIN_MIN = "BilirubinMin";
    public static final String ALT_MAX = "ALTMax";
    public static final String AST_MAX = "ASTMax";
    public static final String ALP_MAX = "ALPMax";

    /* Patient reports Constants */
    public static final String PATIENTS_REPORTS_FILENAME = "patientHistory.json";
    public static final String PATIENT = "patient";
    public static final String ID = "id";
    public static final String BLOOD = "Blood";
    public static final String DATE = "Date";
    public static final String RBC = "RBC";
    public static final String CBC = "CBC";
    public static final String WBC = "WBC";
    public static final String PLATELETS = "Platelets";
    public static final String HAEMOGLOBIN = "Haemoglobin";
    public static final String HEMATOCRIT = "Hematocrit";
    public static final String DATE_OF_COLLECTION = "DateOfCollection";
    public static final String KIDNEY = "Kidney";
    public static final String CREATININE = "Creatinine";
    public static final String BUN = "BUN";
    public static final String LIVER = "Liver";
    public static final String ALBUMIN = "Albumin";
    public static final String BILIRUBIN = "Bilirubin";
    public static final String ALT = "ALT";
    public static final String AST = "AST";
    public static final String ALP = "ALP";
    public static final String VISION = "Vision";
    public static final String COVID = "Covid";
    public static final String RTPCR = "RTPCR";
    public static final String SARS_COV2 = "SARs-CoV2";
    
    /* Tests constant */
    public static final String TESTS = "tests";
}
