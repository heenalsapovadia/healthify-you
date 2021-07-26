package persistence.patient.utilImpl;

import org.junit.Test;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
* <pre>
* Perform tests for recommending doctor based on symptoms
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class DoctorRecommendationUtilImplTest {

    /* Input validation test cases start here */

    @Test
    public void getDoctorRecommendations_IV1() {

        /* Input validations for symptoms */
        /* Incorrect inputs */

        DoctorRecommendationUtilImpl doctorRecommendationUtil = new DoctorRecommendationUtilImpl();

        /* symptom is an empty string */
        assertEquals(null, doctorRecommendationUtil.getDoctorRecommendations("", 2, 3));

        /* symptom is a string with null value */
        assertEquals(null, doctorRecommendationUtil.getDoctorRecommendations(null, 2, 3));


        /* symptom is a string with value not in the list of options */
        assertEquals(null, doctorRecommendationUtil.getDoctorRecommendations("Diabetes", 2, 3));

    }

    @Test
    public void getDoctorRecommendations_IV2() {

        /* Input validations for numRec */
        /* Incorrect inputs */

        DoctorRecommendationUtilImpl doctorRecommendationUtil = new DoctorRecommendationUtilImpl();

        /* numRec is 0 */
        assertEquals(null, doctorRecommendationUtil.getDoctorRecommendations("Cough", 2, 0));

        /* numRec is a negative number */
        assertEquals(null, doctorRecommendationUtil.getDoctorRecommendations("Cough", 2, -4));

    }

    @Test
    public void getDoctorRecommendations_IV3() {

        /* Input validations for support */
        /* Incorrect inputs */

        DoctorRecommendationUtilImpl doctorRecommendationUtil = new DoctorRecommendationUtilImpl();

        /* support is a negative number */
        assertEquals(null, doctorRecommendationUtil.getDoctorRecommendations("Cough", -2, 4));

    }


    /* Control Flow test cases start here */

    /* Recommendations - without any ties of frequencies at the boundary */
    @Test
    public void getDoctorRecommendations_CF1() {

        DoctorRecommendationUtilImpl doctorRecommendationUtil = new DoctorRecommendationUtilImpl();
        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.instance();

        ArrayList<String> doctorIDList = new ArrayList<>();
        doctorIDList.add("Samiksha Salgaonkar");
        doctorIDList.add("heenal sapovadia");
        doctorIDList.add("Karolina Blix");
        doctorIDList.add("christine hanstrom");

        /* numRec is 4 */
        assertEquals(doctorIDList, doctorRecommendationUtil.getDoctorRecommendations("Cough", 2, 4));

    }

    /* Recommendations - with ties of frequencies at the boundary (numRec includes the tied entries) */
    @Test
    public void getDoctorRecommendations_CF2() {

        DoctorRecommendationUtilImpl doctorRecommendationUtil = new DoctorRecommendationUtilImpl();
        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.instance();

        ArrayList<String> doctorIDList = new ArrayList<>();
        doctorIDList.add("Samiksha Salgaonkar");
        doctorIDList.add("heenal sapovadia");
        doctorIDList.add("Karolina Blix");

        /* numRec is 3 */
        assertEquals(doctorIDList, doctorRecommendationUtil.getDoctorRecommendations("Cough", 2, 3));

    }

    /* Recommendations - with ties of frequencies at the boundary (numRec excludes the tied entries) */
    @Test
    public void getDoctorRecommendations_CF3() {

        DoctorRecommendationUtilImpl doctorRecommendationUtil = new DoctorRecommendationUtilImpl();
        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.instance();

        ArrayList<String> doctorIDList = new ArrayList<>();
        doctorIDList.add("Samiksha Salgaonkar");
        doctorIDList.add("heenal sapovadia");
        doctorIDList.add("Karolina Blix");

        /* numRec is 2 */
        assertEquals(doctorIDList, doctorRecommendationUtil.getDoctorRecommendations("Cough", 2, 2));

    }

    /* Recommendations - without ties of frequencies at the boundary and support 0 */
    @Test
    public void getDoctorRecommendations_CF4() {

        DoctorRecommendationUtilImpl doctorRecommendationUtil = new DoctorRecommendationUtilImpl();
        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.instance();

        ArrayList<String> doctorIDList = new ArrayList<>();
        doctorIDList.add("Samiksha Salgaonkar");

        /* numRec is 1 */
        assertEquals(doctorIDList, doctorRecommendationUtil.getDoctorRecommendations("Cough", 0, 1));

    }

}
