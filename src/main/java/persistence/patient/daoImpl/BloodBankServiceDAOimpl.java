package persistence.patient.daoImpl;

import persistence.doctor.daoImpl.PrescriptionDAOImpl;
import persistence.patient.dao.BloodBankServiceDAO;
import persistence.patient.model.BloodBankService;
import presentation.startup.DatabaseConnection;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

//Through this feature, the logged in patient can register for donating blood
// database entities
//● DonationId
//● PatientId
//● BloodGrp
//● Date
public class BloodBankServiceDAOimpl implements BloodBankServiceDAO {
    private static final Logger LOGGER = Logger.getLogger(BloodBankServiceDAOimpl.class.getName());

    @Override
    public void insertBloodBankServiceDetails(List<BloodBankService> bloodBankServiceList) {
        Connection conn = DatabaseConnection.getConnection();

        for ( BloodBankService bloodBankService : bloodBankServiceList )
        {

            String sql = "INSERT into blood_donations(donation_id, patient_id, blood_grp, blooddonation_date)" + "VALUES(123,124,A,24)";
            try( PreparedStatement ps = conn.prepareStatement(sql)){
               ps.setInt(1, bloodBankService.getDonationId());
               ps.setInt(2,bloodBankService.getPatientId());
               ps.setString(3, bloodBankService.getBloodGrp());
               ps.setDate(4, (Date) bloodBankService.getDate());

                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try{

                String sql2 = "SELECT  donation_id, patient_id, blood_grp, blooddonation_date FROM blood_donations";
                try(PreparedStatement ps = conn.prepareStatement(sql2)){
                    ResultSet rs = ps.executeQuery();
                    if(rs.next())
                        //Display values
                    System.out.print("donation_id:" + rs.getInt("donation_id"));
                    System.out.print("patient_id:" + rs.getInt("patient_id"));
                    System.out.print(", blood_grp: " + rs.getString("blood_grp"));
                    System.out.println(", blooddonation_date: " + rs.getDate("blooddonation_date"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } finally{
                //close statements, statement and resultset here..
            }
        }



    }



}
