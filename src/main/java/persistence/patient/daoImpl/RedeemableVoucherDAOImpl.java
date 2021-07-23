package persistence.patient.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.patient.dao.RedeemableVoucherDAO;
import persistence.patient.model.RedeemableVoucher;
import presentation.startup.DatabaseConnection;

/**
 * @author Gurleen Saluja
 *
 */
public class RedeemableVoucherDAOImpl implements RedeemableVoucherDAO {
	
	private static final Logger LOGGER = Logger.getLogger(RedeemableVoucherDAOImpl.class.getName());

	@Override
	public RedeemableVoucher getVoucherByBloodGroup(String bloodGroup) {
		Connection conn = DatabaseConnection.getConnection();
		RedeemableVoucher voucher = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from vouchers where blood_group = ?");
		try (PreparedStatement ps = conn.prepareStatement(sql.toString())){
			ps.setString(1, bloodGroup);
			rs = ps.executeQuery();
			if(rs.next()) {
				voucher = new RedeemableVoucher();
				voucher.setVoucherId(rs.getString("voucher_id"));
				voucher.setBloodGroup(rs.getString("blood_group"));
				voucher.setPoints(rs.getDouble("points"));
				voucher.setValidityInDays(rs.getInt("validity_in_days"));
			}
		}
		catch(SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString());
		}
		return voucher;
	}

	@Override
	public RedeemableVoucher getVoucherByPatient(int patientId) {
		Connection conn = DatabaseConnection.getConnection();
		RedeemableVoucher voucher = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from vouchers where voucher_id = (select voucher_id from patients where patient_id = ?)");
		try (PreparedStatement ps = conn.prepareStatement(sql.toString())){
			ps.setInt(1, patientId);
			rs = ps.executeQuery();
			if(rs.next()) {
				voucher = new RedeemableVoucher();
				voucher.setVoucherId(rs.getString("voucher_id"));
				voucher.setBloodGroup(rs.getString("blood_group"));
				voucher.setPoints(rs.getDouble("points"));
				voucher.setValidityInDays(rs.getInt("validity_in_days"));
			}
		}
		catch(SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString());
		}
		return voucher;
	}
}