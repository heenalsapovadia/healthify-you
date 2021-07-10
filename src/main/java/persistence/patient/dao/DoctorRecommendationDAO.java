package persistence.patient.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorRecommendationDAO {

    public ArrayList<Integer> fetchDoctorList(String symptom) throws SQLException;
    public ArrayList<String> getDoctorName(ArrayList<Integer> recf);

}
