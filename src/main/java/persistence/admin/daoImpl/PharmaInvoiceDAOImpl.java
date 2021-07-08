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
		Connection conn = DatabaseConnection.getConnection();
		ResultSet rs = null;
		Map<String, List<PharmaInvoice>> invoicesMap = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from pharma_supplies where pharma_bill_date = ?");
		try (PreparedStatement ps = conn.prepareStatement(sql.toString())){
			ps.setDate(1, date);
			rs = ps.executeQuery();
			while(rs.next()) {
				PharmaInvoice invoice = new PharmaInvoice();
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
				if(invoicesMap.containsKey(invoice.getPharmaName()))
					invoicesMap.get(invoice.getPharmaName()).add(invoice);
				else {
					List<PharmaInvoice> invoiceList = new ArrayList<>();
					invoiceList.add(invoice);
					invoicesMap.put(invoice.getPharmaName(), invoiceList);
				}
			}
		}
		catch(SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString());
		}
		return invoicesMap;
	}
}
