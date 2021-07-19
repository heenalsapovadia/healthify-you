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
        Connection conn = DatabaseConnection.getConnection();

        List<LabCheck> labCheckList = new ArrayList<>();
        String sql = "SELECT * FROM labcheck_plans";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LabCheck labCheck = new LabCheck();
                labCheck.setCheckup_id(rs.getInt("checkup_id"));
                labCheck.setCheckup_name(rs.getString("checkup_name"));
                labCheck.setCheckup_type(rs.getString("checkup_type"));
                labCheck.setDescription(rs.getString("description"));
                labCheckList.add(labCheck);
            }
            return labCheckList;
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return null;
    }
}
