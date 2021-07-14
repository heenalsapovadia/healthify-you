package persistence.patient.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <pre>
 * DAO interface for doctor recommendation
 * </pre>
 *
 * @author Samiksha Salgaonkar
 *
 **/

public interface DoctorRecommendationDAO {

    public ArrayList<Integer> fetchDoctorList(String symptom) throws SQLException;
    public ArrayList<String> getDoctorName(ArrayList<Integer> recf);

}
