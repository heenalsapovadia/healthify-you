package persistence.common.paymentInterface.daoImpl;

import persistence.common.paymentInterface.dao.PaymentInterfaceDAO;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentInterfaceDAOImpl implements PaymentInterfaceDAO {

    private static final Logger LOGGER = Logger.getLogger(PaymentInterfaceDAOImpl.class.getName());

    public int insertPaymentInterfaceDetails(PaymentInterface paymentInterface) {
        Connection conn = DatabaseConnection.getConnection();

        int billingId = findMaxBillingId() + 1;

        String sql = "INSERT into payment_billing(billing_id,patient_id,voucher_id,billing_date,bill_category,bill_amount,payment_mode,discount,created_on,status,voucher_redemption_date)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billingId);
            ps.setInt(2, paymentInterface.getPatient_id());
            ps.setString(3, paymentInterface.getVoucher_id());
            ps.setDate(4, new java.sql.Date(paymentInterface.getBilling_date().getTime()));
            ps.setString(5, String.valueOf(paymentInterface.getBill_category()));
            ps.setDouble(6, paymentInterface.getBill_amount());
            ps.setString(7, String.valueOf(paymentInterface.getCurrentPaymentMode()));
            ps.setDouble(8, paymentInterface.getDiscount());
            ps.setDate(9, new java.sql.Date(paymentInterface.getCreated_on().getTime()));
            ps.setString(10, String.valueOf(paymentInterface.getStatusOfPayment()));
            ps.setDate(11, new java.sql.Date(paymentInterface.getVoucher_redemption_date().getTime()));
            ps.executeUpdate();
            return billingId;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int findMaxBillingId() {
        Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT MAX(billing_id) FROM payment_billing";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt("MAX(billing_id)");
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.toString());
            System.out.println("SQL ERROR:"+e.getMessage());
        }
        return 0;
    }

    public List<PaymentInterface> getAllPaymentInterfaceDetails(Patient patient) {
        Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM payment_billing where patient_id=" + patient.getPatientId();
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            List<PaymentInterface> paymentInterfaceServices = new ArrayList<PaymentInterface>();
            while (result.next()) {
                PaymentInterface fetchedService = new PaymentInterface();
                fetchedService.setBilling_id(result.getInt(1));
                fetchedService.setPatient_id(result.getInt(2));
                fetchedService.setVoucher_id(result.getString(3));
                fetchedService.setBilling_date(result.getDate(4));
                fetchedService.setBill_category(PaymentBillingCategory.valueOf(result.getString(5)));
                fetchedService.setBill_amount(result.getDouble(6));
                fetchedService.setCurrentPaymentMode(PaymentInterface.payment_mode.valueOf(result.getString(7)));
                fetchedService.setDiscount(result.getDouble(8));
                fetchedService.setCreated_on(result.getDate(9));
                fetchedService.setStatusOfPayment(PaymentInterface.status.valueOf(result.getString(10)));
                fetchedService.setVoucher_redemption_date(result.getDate(11));
                paymentInterfaceServices.add(fetchedService);
            }
            return paymentInterfaceServices;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public int getVoucherRedemptionPoints(int patientId) {
        Connection conn = DatabaseConnection.getConnection();

        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(points) as pointSummation from vouchers where voucher_id in ");
        sql.append("(select voucher_id from payment_billing where patient_id = ?)");
        try (PreparedStatement ps = conn.prepareStatement(sql.toString())){
            ps.setInt(1, patientId);
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("pointSummation");
            }
        }
        catch(SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return -1;
    }
}

