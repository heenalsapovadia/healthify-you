package persistence.patient.util;

import java.util.ArrayList;

/**
* <pre>
* Perform operations for recommending doctor based on symptoms
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public interface DoctorRecommendationUtil {

  public ArrayList<String> getDoctorRecommendations(String symptom, int support, int numRec);
}
