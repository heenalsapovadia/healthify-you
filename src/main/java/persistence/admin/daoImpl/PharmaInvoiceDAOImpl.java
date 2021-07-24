package persistence.admin.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.admin.dao.PharmaInvoiceDAO;
import persistence.admin.model.PharmaInvoice;
import presentation.startup.DatabaseConnection;

public class PharmaInvoiceDAOImpl implements PharmaInvoiceDAO {
	
	private static final Logger LOGGER = Logger.getLogger(PharmaInvoiceDAOImpl.class.getName());
	
	@Override
	public Map<String, List<PharmaInvoice>> getInvoiceDetailsByDate(Date date) {
		Connection connection = DatabaseConnection.getConnection();
		ResultSet resultSet = null;
		Map<String, List<PharmaInvoice>> invoicesMap = new HashMap<>();
		StringBuilder sqlStatement = new StringBuilder();
		sqlStatement.append("select * from pharma_supplies where pharma_bill_date = ? order by pharma_billing_id asc");
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString())){
			preparedStatement.setDate(1, date);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				PharmaInvoice invoice = new PharmaInvoice();
				invoice.setInvoiceId(resultSet.getInt("pharma_billing_id"));
				invoice.setPharmaName(resultSet.getString("pharma_name"));
				invoice.setPharmaAddress(resultSet.getString("pharma_address"));
				invoice.setPharmaContact(resultSet.getString("pharma_contact"));
				invoice.setPaymentMode(resultSet.getString("payment_mode"));
				invoice.setItemName(resultSet.getString("pharma_item_name"));
				invoice.setItemDosage(resultSet.getString("pharma_item_dosage"));
				invoice.setItemManufacturer(resultSet.getString("pharma_manufacturer"));
				invoice.setItemQuantity(resultSet.getInt("pharma_item_quantity"));
				invoice.setItemUnitPrice(resultSet.getDouble("pharma_item_unit_price"));
				invoice.setDate(resultSet.getDate("pharma_bill_date"));
				invoice.setTime(resultSet.getTime("pharma_bill_time"));
				invoice.setItemQuantity(resultSet.getInt("pharma_item_updated_quantity"));
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
		Connection connection = DatabaseConnection.getConnection();
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
				invoice.setInvoiceId(resultSet.getInt("pharma_billing_id"));
				invoice.setPharmaName(resultSet.getString("pharma_name"));
				invoice.setPharmaAddress(resultSet.getString("pharma_address"));
				invoice.setPharmaContact(resultSet.getString("pharma_contact"));
				invoice.setPaymentMode(resultSet.getString("payment_mode"));
				invoice.setItemName(resultSet.getString("pharma_item_name"));
				invoice.setItemDosage(resultSet.getString("pharma_item_dosage"));
				invoice.setItemManufacturer(resultSet.getString("pharma_manufacturer"));
				invoice.setItemQuantity(resultSet.getInt("pharma_item_quantity"));
				invoice.setItemUnitPrice(resultSet.getDouble("pharma_item_unit_price"));
				invoice.setDate(resultSet.getDate("pharma_bill_date"));
				invoice.setTime(resultSet.getTime("pharma_bill_time"));
				invoice.setExpiryDate(resultSet.getDate("expiry_date"));
				invoice.setOrderedQuantity(resultSet.getInt("ordered_quantity"));
				invoicesList.add(invoice);
			}
		}
		catch(SQLException exception) {
			LOGGER.log(Level.SEVERE, exception.toString());
		}
		return invoicesList;
	}
}