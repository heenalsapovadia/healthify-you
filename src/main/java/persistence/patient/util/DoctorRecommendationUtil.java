package persistence.patient.util;

import java.util.ArrayList;

/**
 * <pre>
 * Util interface for doctor recommendation
 * </pre>
 *
 * @author Samiksha Salgaonkar
 *
 **/

public interface DoctorRecommendationUtil {

    public ArrayList<String> getDoctorRecommendations(String symptom, int support, int numRec);

}
