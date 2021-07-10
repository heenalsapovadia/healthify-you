package persistence.patient.daoImpl;
import persistence.patient.dao.DoctorRecommendationDAO;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DoctorRecommendationDAOImpl implements DoctorRecommendationDAO{

    @Override
    public ArrayList<Integer> fetchDoctorList(String symptom) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rS = null;

        String sql = "select doctor_id from doctor_symptoms where symptom = \"" +symptom+ "\"" + ";";

        try {
            /* retrieves doctor list for the symptoms */
            rS = statement.executeQuery(sql);

            ArrayList<Integer> doctorIDList = new ArrayList<>();
            while (rS.next()) {
                doctorIDList.add(rS.getInt("doctor_id"));
            }

            for(Integer i : doctorIDList) {
                System.out.println(i);
            }

            return doctorIDList;

        } catch (SQLException se) {
            return null;
        }

    }

    @Override
    public ArrayList<String> getDoctorName(ArrayList<Integer> recf) {

        if(recf == null) {
            return null;
        }

        if(recf != null && recf.size() == 0) {
            return null;
        }

        return null;
    }

}