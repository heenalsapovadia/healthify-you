package persistence.doctor.daoImpl;

import persistence.doctor.dao.DoctorAvailabilityDAO;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorAvailabilityDAOImpl implements DoctorAvailabilityDAO {

    private static final Logger LOGGER = Logger.getLogger(DoctorAvailabilityDAOImpl.class.getName());

    @Override
    public List<String> getAvailabilityByDoctor(int doctorId) {
        Connection conn = DatabaseConnection.getConnection();

        List<String> daysList = new ArrayList<>();

        String sql = "SELECT * FROM doc_availability WHERE doctor_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, doctorId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                daysList.add(rs.getString("weekday"));
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return daysList;
    }
}
