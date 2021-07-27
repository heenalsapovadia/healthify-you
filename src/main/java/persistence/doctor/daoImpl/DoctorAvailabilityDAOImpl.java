package persistence.doctor.daoImpl;

import persistence.common.DatabaseConstants;
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

/**
 * @author Heenal Sapovadia
 *
 */
public class DoctorAvailabilityDAOImpl implements DoctorAvailabilityDAO {

    private static final Logger LOGGER = Logger.getLogger(DoctorAvailabilityDAOImpl.class.getName());

    @Override
    public List<String> getAvailabilityByDoctor(int doctorId) {
        Connection connection = DatabaseConnection.instance();

        List<String> daysList = new ArrayList<>();

        String sql = "SELECT * FROM doc_availability WHERE doctor_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, doctorId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                daysList.add(resultSet.getString(DatabaseConstants.WEEKDAY));
            }
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return daysList;
    }
}
