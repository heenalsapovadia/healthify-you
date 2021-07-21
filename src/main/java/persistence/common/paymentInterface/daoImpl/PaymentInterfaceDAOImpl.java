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
        String sql = "INSERT into payment_billing(patient_id,prescription_id,voucher_id,billing_date,billing_category,bill_amount,payment_mode,discount,created_on,status,voucher_redemption_date)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, paymentInterface.getBilling_id());
            ps.setInt(2, paymentInterface.getPatient_id());
            ps.setInt(3, paymentInterface.getPrescription_id());
            ps.setString(4, paymentInterface.getVoucher_id());
            ps.setDate(5, (Date) paymentInterface.getBilling_date());
            ps.setString(6, String.valueOf(paymentInterface.getBill_category()));
            ps.setDouble(7, paymentInterface.getBill_amount());
            ps.setString(8, String.valueOf(paymentInterface.getCurrentPaymentMode()));
            ps.setDouble(9, paymentInterface.getDiscount());
            ps.setDate(10, (Date) paymentInterface.getCreated_on());
            ps.setString(8, String.valueOf(paymentInterface.getStatusOfPayment()));
            ps.setDate(10, (Date) paymentInterface.getVoucher_redemption_date());
//            ps.executeUpdate();

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("billing_id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
                fetchedService.setPrescription_id(result.getInt(3));
                fetchedService.setVoucher_id(result.getString(4));
                fetchedService.setBilling_date(result.getDate(5));
                fetchedService.setBill_category(PaymentBillingCategory.valueOf(result.getString(6)));
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
