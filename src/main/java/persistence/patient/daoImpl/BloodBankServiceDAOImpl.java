package persistence.patient.daoImpl;
import persistence.patient.dao.BloodBankServiceDAO;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
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
public class BloodBankServiceDAOImpl implements BloodBankServiceDAO {
    private static final Logger LOGGER = Logger.getLogger(BloodBankServiceDAOImpl.class.getName());

    @Override
    public void insertBloodBankServiceDetails(BloodBankService bloodBankService) {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT into blood_donations(donation_id, patient_id, blood_grp, blooddonation_date)" + "VALUES(?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bloodBankService.getDonationId()); //bloodBankService.getDonationId()
            ps.setString(2, bloodBankService.getPatientId()); //bloodBankService.getPatientId()
            ps.setString(3, bloodBankService.getBloodGrp()); //bloodBankService.getBloodGrp()
            ps.setDate(4, new java.sql.Date(bloodBankService.getDate().getTime())); // (Date) bloodBankService.getDate()
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<BloodBankService> getAllBloodDonationsForPatient(Patient patient) {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM blood_donations where patient_id='" + patient.getPatientEmail() + "'";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            List<BloodBankService> bloodBankServices = new ArrayList<BloodBankService>();
            while (result.next()) {
                BloodBankService fetchedService = new BloodBankService();
                fetchedService.setPatientId(result.getString(2));
                fetchedService.setDonationId(result.getString(1));
                fetchedService.setDate(result.getDate(4));
                fetchedService.setBloodGrp(result.getString(3));
                bloodBankServices.add(fetchedService);
            }
            return bloodBankServices;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
