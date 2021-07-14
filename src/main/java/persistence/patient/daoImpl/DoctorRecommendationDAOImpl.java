package persistence.patient.daoImpl;
import persistence.patient.dao.DoctorRecommendationDAO;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * <pre>
 * DAO class for doctor recommendation
 * </pre>
 *
 * @author Samiksha Salgaonkar
 *
 **/

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

        String inClause = "";
        int size = recf.size();
        for(int i =0; i < size; i++) {
            if(i <= size-2) {
                inClause = inClause + recf.get(i) + ",";
            } else {
                inClause = inClause + recf.get(i);
            }
        }

        Connection conn = DatabaseConnection.getConnection();
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException throwables) {
            System.err.println("Database connection failed!");
            return null;
        }
        ResultSet rS = null;

        String sql = "select first_name, last_name from doctors where doctor_id in (" +inClause+ ");";

        try {
            /* retrieves doctor list for the symptoms */
            rS = statement.executeQuery(sql);

            ArrayList<String> doctorList = new ArrayList<>();
            while (rS.next()) {
                String name = "";
                name = rS.getString("first_name") + " " +rS.getString("last_name");
                doctorList.add(name);
            }

            return doctorList;

        } catch (SQLException se) {
            return null;
        }
    }

}