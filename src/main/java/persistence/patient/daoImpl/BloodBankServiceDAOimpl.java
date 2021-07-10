package persistence.patient.daoImpl;

import persistence.doctor.daoImpl.PrescriptionDAOImpl;
import persistence.patient.dao.BloodBankServiceDAO;
import persistence.patient.model.BloodBankService;
import presentation.startup.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
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
    public void insertBloodBankServiceDetails(List<BloodBankService> bloodBankServiceList ) {

        Connection conn = DatabaseConnection.getConnection();

        for ( BloodBankService bloodBankService : bloodBankServiceList ) {

            String sql = "INSERT into blood_donations(donation_id, patient_id, blood_grp, blooddonation_date)" + "VALUES(?,?,?,?)";
            try ( PreparedStatement ps = conn.prepareStatement(sql) ) {
                ps.setInt(1, 12345); //bloodBankService.getDonationId()
                ps.setInt(2, 3456); //bloodBankService.getPatientId()
                ps.setString(3, "A+"); //bloodBankService.getBloodGrp()
                ps.setDate(4, Date.valueOf("24-12-2021")); // (Date) bloodBankService.getDate()

                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

//            try {
//                // to view the values in the table
//
//                String sql2 = "SELECT  * FROM blood_donations";
//
//                Statement statement = conn.createStatement();
//                ResultSet result = statement.executeQuery(sql);
//
//                int count = 0;
//
//                while (result.next()) {
//                    int donationId = result.getInt(2);
//                    int patientid = result.getInt(3);
//                    String bloodgroup = result.getString("blood_grp");
//                    Date blooddonationdate = result.getDate("blooddonation_date");
//
//                    String output = "User #%d: %s - %s - %s - %s";
//                    System.out.println(String.format(output, ++count, donationId, patientid, bloodgroup, blooddonationdate));
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
        }

    }


    public static void main(String[] args) {

//        // Creating an ArrayList Object of Integer type
//        ArrayList<Integer> list = new ArrayList<>();
//
//        // Inserting some Integer values in ArrayList
//        list.add(3);
//        list.add(57);
//        list.add(7);



        try {
            // to view the values in the table

            String sql2 = "SELECT  * FROM blood_donations";
            Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql2);

            int count = 0;

            while (result.next()) {
                int donationId = result.getInt(2);
                int patientid = result.getInt(3);
                String bloodgroup = result.getString("blood_grp");
                Date blooddonationdate = result.getDate("blooddonation_date");

                String output = "User #%d: %s - %s - %s - %s";
                System.out.println(String.format(output, ++count, donationId, patientid, bloodgroup, blooddonationdate));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }
}
