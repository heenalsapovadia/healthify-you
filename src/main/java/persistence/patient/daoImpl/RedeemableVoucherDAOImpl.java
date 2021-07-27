package persistence.patient.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.common.DatabaseConstants;
import persistence.patient.dao.RedeemableVoucherDAO;
import persistence.patient.model.Patient;
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
		Connection connection = DatabaseConnection.instance();
		RedeemableVoucher voucher = null;
		ResultSet resultSet = null;
		StringBuilder sqlStatement = new StringBuilder();
		sqlStatement.append("select * from vouchers where blood_group = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString())){
			preparedStatement.setString(1, bloodGroup);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				voucher = new RedeemableVoucher();
				setRedeemableVoucherObject(resultSet, voucher);
			}
		}
		catch(SQLException exception) {
			LOGGER.log(Level.SEVERE, exception.toString());
		}
		return voucher;
	}

	@Override
	public RedeemableVoucher getVoucherByPatient() {
		Connection connection = DatabaseConnection.instance();
		RedeemableVoucher voucher = null;
		ResultSet resultSet = null;
		StringBuilder sqlStatement = new StringBuilder();
		sqlStatement.append("select * from vouchers where voucher_id = (select voucher_id from patients where patient_id = ?)");
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString())){
			preparedStatement.setInt(1, Patient.instance().getPatientId());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				voucher = new RedeemableVoucher();
				setRedeemableVoucherObject(resultSet, voucher);
			}
		}
		catch(SQLException exception) {
			LOGGER.log(Level.SEVERE, exception.toString());
		}
		return voucher;
	}
	
	private void setRedeemableVoucherObject(ResultSet resultSet, RedeemableVoucher voucher) throws SQLException {
		voucher.setVoucherId(resultSet.getString(DatabaseConstants.VOUCHER_ID));
		voucher.setBloodGroup(resultSet.getString(DatabaseConstants.BLOOD_GROUP));
		voucher.setPoints(resultSet.getDouble(DatabaseConstants.POINTS));
		voucher.setValidityInDays(resultSet.getInt(DatabaseConstants.VALIDITY_IN_DAYS));
	}
}