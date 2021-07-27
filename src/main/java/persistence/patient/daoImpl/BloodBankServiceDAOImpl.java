package persistence.patient.daoImpl;

import persistence.patient.dao.BloodBankServiceDAO;
import persistence.patient.dao.PatientDAO;
import persistence.patient.dao.RedeemableVoucherDAO;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
import persistence.patient.model.RedeemableVoucher;
import presentation.startup.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 * <pre>
 * Blood Bank Service Database method implementation
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class BloodBankServiceDAOImpl implements BloodBankServiceDAO {
    private static final Logger LOGGER = Logger.getLogger(BloodBankServiceDAOImpl.class.getName());

    @Override
    public void insertBloodBankServiceDetails(BloodBankService bloodBankService) {
        Connection conn = DatabaseConnection.instance();
        String sql = "INSERT into blood_donations(donation_id, patient_id, blood_grp, blooddonation_date)" + "VALUES(?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bloodBankService.getDonationId());
            ps.setInt(2, bloodBankService.getPatientId());
            ps.setString(3, bloodBankService.getBloodGrp());
            ps.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
            if(ps.executeUpdate() > 0) {
                RedeemableVoucherDAO voucherDAO = new RedeemableVoucherDAOImpl();
                RedeemableVoucher voucher = voucherDAO.getVoucherByBloodGroup(bloodBankService.getBloodGrp());
                if(voucher != null) {
                    PatientDAO patientDAO = new PatientDAOImpl();
                    patientDAO.updateVouchersForPatients(voucher.getVoucherId(), new Timestamp(System.currentTimeMillis()),
                    bloodBankService.getPatientId());
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<BloodBankService> getAllBloodDonationsForPatient(Patient patient) {
        Connection conn = DatabaseConnection.instance();
        String sql = "SELECT * FROM blood_donations where patient_id='" + patient.getPatientId() + "'";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            List<BloodBankService> bloodBankServices = new ArrayList<BloodBankService>();
            while (result.next()) {
                BloodBankService fetchedService = new BloodBankService();
                fetchedService.setPatientId(result.getInt(2));
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
