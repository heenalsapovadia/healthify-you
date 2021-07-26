package persistence.admin.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.admin.dao.PharmaInvoiceDAO;
import persistence.admin.model.PharmaInvoice;
import persistence.common.DatabaseConstants;
import presentation.startup.DatabaseConnection;

/**
 * @author Gurleen Saluja
 *
 */
public class PharmaInvoiceDAOImpl implements PharmaInvoiceDAO {
	
	private static final Logger LOGGER = Logger.getLogger(PharmaInvoiceDAOImpl.class.getName());
	
	@Override
	public Map<String, List<PharmaInvoice>> getInvoiceDetailsByDate(Date date) {
		Connection connection = DatabaseConnection.instance();
		ResultSet resultSet = null;
		Map<String, List<PharmaInvoice>> invoicesMap = new HashMap<>();
		StringBuilder sqlStatement = new StringBuilder();
		sqlStatement.append("select * from pharma_supplies where pharma_bill_date = ? order by pharma_billing_id asc");
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString())){
			preparedStatement.setDate(1, date);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				PharmaInvoice invoice = new PharmaInvoice();
				setInvoiceObject(resultSet, invoice);
				if(invoicesMap.containsKey(invoice.getPharmaName())) {
					invoicesMap.get(invoice.getPharmaName()).add(invoice);
				}
				else {
					List<PharmaInvoice> invoiceList = new ArrayList<>();
					invoiceList.add(invoice);
					invoicesMap.put(invoice.getPharmaName(), invoiceList);
				}
			}
		}
		catch(SQLException exception) {
			LOGGER.log(Level.SEVERE, exception.toString());
		}
		return invoicesMap;
	}
	
	@Override
	public List<PharmaInvoice> getPharmaSupplies(List<String> medicineNameList) {
		Connection connection = DatabaseConnection.instance();
		ResultSet resultSet = null;
		List<PharmaInvoice> invoicesList = new ArrayList<>();
		StringBuilder sqlStatement = new StringBuilder();
		String wildcard = "?,".repeat(medicineNameList.size());
		sqlStatement.append("select * from pharma_supplies where pharma_item_name in ("+ wildcard.substring(0, wildcard.length()-1)+")");
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString())){
			for(int i=0; i<medicineNameList.size(); i++) {
				preparedStatement.setString(i+1, medicineNameList.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				PharmaInvoice invoice = new PharmaInvoice();
				setInvoiceObject(resultSet, invoice);
				invoicesList.add(invoice);
			}
		}
		catch(SQLException exception) {
			LOGGER.log(Level.SEVERE, exception.toString());
		}
		return invoicesList;
	}

	private void setInvoiceObject(ResultSet resultSet, PharmaInvoice invoice) throws SQLException {
		invoice.setInvoiceId(resultSet.getInt(DatabaseConstants.PHARMA_BILLING_ID));
		invoice.setPharmaName(resultSet.getString(DatabaseConstants.PHARMA_NAME));
		invoice.setPharmaAddress(resultSet.getString(DatabaseConstants.PHARMA_ADDRESS));
		invoice.setPharmaContact(resultSet.getString(DatabaseConstants.PHARMA_CONTACT));
		invoice.setPaymentMode(resultSet.getString(DatabaseConstants.PAYMENT_MODE));
		invoice.setItemName(resultSet.getString(DatabaseConstants.PHARMA_ITEM_NAME));
		invoice.setItemDosage(resultSet.getString(DatabaseConstants.PHARMA_ITEM_DOSAGE));
		invoice.setItemManufacturer(resultSet.getString(DatabaseConstants.PHARMA_MANUFACTURER));
		invoice.setItemQuantity(resultSet.getInt(DatabaseConstants.PHARMA_ITEM_QUANTITY));
		invoice.setItemUnitPrice(resultSet.getDouble(DatabaseConstants.PHARMA_ITEM_UNIT_PRICE));
		invoice.setDate(resultSet.getDate(DatabaseConstants.PHARMA_BILL_DATE));
		invoice.setTime(resultSet.getTime(DatabaseConstants.PHARMA_BILL_TIME));
		invoice.setItemUpdatedQuantity(resultSet.getInt(DatabaseConstants.PHARMA_ITEM_UPDATED_QUANTITY));
		invoice.setExpiryDate(resultSet.getDate(DatabaseConstants.EXPIRY_DATE));
	}
	
	@Override
	public Set<String> getMedicineList(){
		Connection connection = DatabaseConnection.instance();
		Set<String> medicineSet = new HashSet<>();

		String sql = "SELECT * FROM pharma_supplies";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String medicineName = resultSet.getString(DatabaseConstants.PHARMA_ITEM_NAME);
				medicineSet.add(medicineName.toLowerCase());
			}
		}
		catch (SQLException sqlException){
			LOGGER.log(Level.SEVERE, sqlException.toString());
			System.out.println("SQL ERROR:"+sqlException.getMessage());
		}
		return medicineSet;
	}
}