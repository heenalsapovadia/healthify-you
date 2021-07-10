package persistence.patient.utilImpl;

import org.junit.Test;
import static org.junit.Assert.*;

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


}
