package persistence.patient.daoImpl;

import persistence.admin.model.PharmaInvoice;
import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.model.Prescription;
import presentation.startup.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestMedicationDAOImpl {

        private static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());

        // As per Prescription Id validation getting prescription name, dose per day
        public List<Prescription> getPrescriptionDetails(int prescriptionId) {
            List<Prescription> prescriptionList = new ArrayList<>();
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM prescription WHERE prescription_id = ?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, prescriptionId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Prescription prescription = new Prescription();
                    prescription.setPrescriptionId(prescriptionId);
                    prescription.setPatientId(rs.getInt("patient_id"));
                    prescription.setMedicineName(rs.getString("medicine_name"));
                    prescription.setMorning(rs.getInt("morning_dose"));
                    prescription.setAfternoon(rs.getInt("afternoon_dose"));
                    prescription.setEvening(rs.getInt("evening_dose"));
                    prescription.setDosageDays(rs.getInt("dosage_days"));
                    prescriptionList.add(prescription);
                }
            }
            catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.toString());
                System.out.println("SQL ERROR:"+e.getMessage());
            }
            return prescriptionList;
        }

    // In order to check stock based on medicine name
    public PharmaInvoice getPharmaInvoice(String medicationName) {
        Connection conn = DatabaseConnection.getConnection();
        ResultSet rs = null;
        PharmaInvoice invoice = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from pharma_supplies where pharma_item_name = ?");
        try (PreparedStatement ps = conn.prepareStatement(sql.toString())){
            ps.setString(1, medicationName);
            rs = ps.executeQuery();
            if(rs.next()) {
                invoice = new PharmaInvoice();
                invoice.setInvoiceId(rs.getInt("pharma_billing_id"));
                invoice.setPharmaName(rs.getString("pharma_name"));
                invoice.setPharmaAddress(rs.getString("pharma_address"));
                invoice.setPharmaContact(rs.getString("pharma_contact"));
                invoice.setPaymentMode(rs.getString("payment_mode"));
                invoice.setItemName(rs.getString("pharma_item_name"));
                invoice.setItemDosage(rs.getString("pharma_item_dosage"));
                invoice.setItemManufacturer(rs.getString("pharma_manufacturer"));
                invoice.setItemQuantity(rs.getInt("pharma_item_quantity"));
                invoice.setItemUnitPrice(rs.getDouble("pharma_item_unit_price"));
                invoice.setDate(rs.getDate("pharma_bill_date"));
                invoice.setTime(rs.getTime("pharma_bill_time"));
                invoice.setItemUpdatedQuantity(rs.getInt("pharma_item_updated_quantity"));
            }
        }
        catch(SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return invoice;
    }

    // In order to update the stock based on medicine that is requested by patient
    public void updatePharmaInvoice(String medication, int remainingQuantity) {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE pharma_supplies SET pharma_item_updated_quantity = ? where pharma_item_name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, remainingQuantity);
            ps.setString(2, medication);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getLocalizedMessage();
        }
    }

}
