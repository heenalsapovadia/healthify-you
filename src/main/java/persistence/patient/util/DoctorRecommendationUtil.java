package persistence.patient.util;

/**
* <pre>
* Perform operations for recommending doctor based on symptoms
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public interface DoctorRecommendationUtil {

  public void getDoctorRecommendations(String symptom, int support, int numRec);
}
