package persistence.patient.daoImpl;

import persistence.common.DatabaseConstants;
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

/**
 * @author Heenal Sapovadia
 *
 */
public class LabCheckDAOImpl implements LabCheckDAO {
    private static final Logger LOGGER = Logger.getLogger(LabCheckDAOImpl.class.getName());

    /*
    Fetch all the plans from database
     */
    @Override
    public List<LabCheck> getAvailablePlans() {
        Connection connection = DatabaseConnection.instance();

        List<LabCheck> labCheckList = new ArrayList<>();
        String sql = "SELECT * FROM labcheck_plans";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LabCheck labCheck = new LabCheck();
                labCheck.setCheckupId(resultSet.getInt(DatabaseConstants.CHECKUP_ID));
                labCheck.setCheckupName(resultSet.getString(DatabaseConstants.CHECKUP_NAME));
                labCheck.setCheckupType(resultSet.getString(DatabaseConstants.CHECKUP_TYPE));
                labCheck.setDescription(resultSet.getString(DatabaseConstants.DESCRIPTION));
                labCheck.setCharges(resultSet.getDouble(DatabaseConstants.CHARGES));
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
