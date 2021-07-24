package persistence.patient.daoImpl;

import persistence.patient.dao.LabCheckDAO;
import persistence.patient.model.LabCheck;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LabCheckDAOImpl implements LabCheckDAO {
    private static final Logger LOGGER = Logger.getLogger(LabCheckDAOImpl.class.getName());

    /*
    Fetch all the plans from database
     */
    @Override
    public List<LabCheck> getAvailablePlans() {
        Connection connection = DatabaseConnection.getConnection();

        List<LabCheck> labCheckList = new ArrayList<>();
        String sql = "SELECT * FROM labcheck_plans";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LabCheck labCheck = new LabCheck();
                labCheck.setCheckupId(resultSet.getInt("checkup_id"));
                labCheck.setCheckupName(resultSet.getString("checkup_name"));
                labCheck.setCheckupType(resultSet.getString("checkup_type"));
                labCheck.setDescription(resultSet.getString("description"));
                labCheck.setCharges(resultSet.getDouble("charges"));
                labCheckList.add(labCheck);
            }
            return labCheckList;
        }
        catch (SQLException sqlException){
            LOGGER.log(Level.SEVERE, sqlException.toString());
            System.out.println("SQL ERROR:"+sqlException.getMessage());
        }
        return null;
    }
}
