package persistence.common.daoImpl;


import persistence.common.model.PaymentInterface;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PaymentInterfaceDAOImpl {
    private static final Logger LOGGER = Logger.getLogger(PaymentInterfaceDAOImpl.class.getName());

    public void insertPaymentInterfaceDetails(PaymentInterface paymentInterface) {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT into payment_billing(billing_id, patient_id,prescription_id,voucher_id,billing_date,billing_category,bill_amount,payment_mode,discount,created_on,status,voucher_redemption_date)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, paymentInterface.getBilling_id());
            ps.setInt(2, paymentInterface.getPatient_id());
            ps.setInt(3, paymentInterface.getPrescription_id());
            ps.setInt(4, paymentInterface.getVoucher_id());
            ps.setDate(5, (Date) paymentInterface.getBilling_date());
            ps.setString(6, paymentInterface.getBilling_category());
            ps.setDouble(7, paymentInterface.getBill_amount());
            ps.setString(8, String.valueOf(paymentInterface.getCurrentPaymentMode()));
            ps.setDouble(9, paymentInterface.getDiscount());
            ps.setDate(10, (Date) paymentInterface.getCreated_on());
            ps.setString(8, String.valueOf(paymentInterface.getStatusOfPayment()));
            ps.setDate(10, (Date) paymentInterface.getVoucher_redemption_date());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<PaymentInterface> getAllPaymentInterfaceDetails(Patient patient) {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM payment_billing where patient_id='" + patient.getPatientId() + "'";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            List<PaymentInterface> paymentInterfaceServices = new ArrayList<PaymentInterface>();
            while (result.next()) {
                PaymentInterface fetchedService = new PaymentInterface();
                fetchedService.setBilling_id(result.getInt(1));
                fetchedService.setPatient_id(result.getInt(2));
                fetchedService.setPrescription_id(result.getInt(3));
                fetchedService.setVoucher_id(result.getInt(4));
                fetchedService.setBilling_date(result.getDate(5));
                fetchedService.setBilling_category(result.getString(6));
                fetchedService.setBill_amount(result.getDouble(7));
                fetchedService.setCurrentPaymentMode(PaymentInterface.payment_mode.valueOf(result.getString(8)));
                fetchedService.setDiscount(result.getDouble(9));
                fetchedService.setCreated_on(result.getDate(10));
                fetchedService.setStatusOfPayment(PaymentInterface.status.valueOf(result.getString(11)));
                fetchedService.setVoucher_redemption_date(result.getDate(12));
                paymentInterfaceServices.add(fetchedService);
            }
            return paymentInterfaceServices;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
