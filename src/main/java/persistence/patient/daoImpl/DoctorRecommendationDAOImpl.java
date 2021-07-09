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

        return null;
    }

}
