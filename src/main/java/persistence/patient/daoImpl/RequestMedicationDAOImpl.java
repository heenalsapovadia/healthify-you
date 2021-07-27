package persistence.patient.daoImpl;

import persistence.admin.model.PharmaInvoice;
import persistence.common.DatabaseConstants;
import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.model.Prescription;
import persistence.patient.dao.RequestMedicationDAO;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * <pre>
 * Request Medication Database method implementation
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class RequestMedicationDAOImpl implements RequestMedicationDAO {

    private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

    public List<Prescription> getPrescriptionDetails(int prescriptionId) {
        List<Prescription> prescriptionList = new ArrayList<>();
        Connection conn = DatabaseConnection.instance();
        String sql = "SELECT * FROM prescription WHERE prescription_id = ? and patient_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, prescriptionId);
            ps.setInt(2, Patient.instance().getPatientId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Prescription prescription = new Prescription();
                prescription.setPrescriptionId(prescriptionId);
                prescription.setPatientId(rs.getInt(DatabaseConstants.PATIENT_ID));
                prescription.setMedicineName(rs.getString(DatabaseConstants.MEDICINE_NAME));
                prescription.setMorning(rs.getInt(DatabaseConstants.MORNING_DOSE));
                prescription.setAfternoon(rs.getInt(DatabaseConstants.AFTERNOON_DOSE));
                prescription.setEvening(rs.getInt(DatabaseConstants.EVENING_DOSE));
                prescription.setDosageDays(rs.getInt(DatabaseConstants.DOSAGE_DAYS));
                prescriptionList.add(prescription);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return prescriptionList;
    }

    public PharmaInvoice getPharmaInvoice(String medicationName) {
        Connection conn = DatabaseConnection.instance();
        ResultSet rs = null;
        PharmaInvoice invoice = null;
        StringBuilder sql = new StringBuilder();

        sql.append("select * from pharma_supplies where pharma_item_name = ?");
        try (PreparedStatement ps = conn.prepareStatement(sql.toString())){
            ps.setString(1, medicationName);
            rs = ps.executeQuery();
            if(rs.next()) {
                invoice = new PharmaInvoice();
                invoice.setInvoiceId(rs.getInt(DatabaseConstants.PHARMA_BILLING_ID));
                invoice.setPharmaName(rs.getString(DatabaseConstants.PHARMA_NAME));
                invoice.setPharmaAddress(rs.getString(DatabaseConstants.PHARMA_ADDRESS));
                invoice.setPharmaContact(rs.getString(DatabaseConstants.PHARMA_CONTACT));
                invoice.setPaymentMode(rs.getString(DatabaseConstants.PAYMENT_MODE));
                invoice.setItemName(rs.getString(DatabaseConstants.PHARMA_ITEM_NAME));
                invoice.setItemDosage(rs.getString(DatabaseConstants.PHARMA_ITEM_DOSAGE));
                invoice.setItemManufacturer(rs.getString(DatabaseConstants.PHARMA_MANUFACTURER));
                invoice.setItemQuantity(rs.getInt(DatabaseConstants.PHARMA_ITEM_QUANTITY));
                invoice.setItemUnitPrice(rs.getDouble(DatabaseConstants.PHARMA_ITEM_UNIT_PRICE));
                invoice.setDate(rs.getDate(DatabaseConstants.PHARMA_BILL_DATE));
                invoice.setTime(rs.getTime(DatabaseConstants.PHARMA_BILL_TIME));
                invoice.setItemUpdatedQuantity(rs.getInt(DatabaseConstants.PHARMA_ITEM_UPDATED_QUANTITY));
            }
        }
        catch(SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return invoice;
    }

    public void updatePharmaInvoice(String medication, int remainingQuantity) {
        Connection conn = DatabaseConnection.instance();
        String sql = "UPDATE pharma_supplies SET pharma_item_updated_quantity = ? where pharma_item_name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, remainingQuantity);
            ps.setString(2, medication);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getLocalizedMessage();
        }
    }

    public void updatePrescription(int prescription_id, int billing_id) {
        Connection conn = DatabaseConnection.instance();
        String sql = "UPDATE prescription SET billing_id = ? where prescription_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billing_id);
            ps.setInt(2, prescription_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getLocalizedMessage();
        }
    }
}
