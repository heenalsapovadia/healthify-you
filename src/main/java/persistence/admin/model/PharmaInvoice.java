package persistence.admin.model;

import java.sql.Date;
import java.sql.Time;

/**
 * <pre>
 * Model class for pharmaceutical invoices.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class PharmaInvoice {
	
	private int invoiceId;
	private String pharmaName;
	private String pharmaAddress;
	private String pharmaContact;
	private String paymentMode;
	private String createdOn;
	private String itemName;
	private String itemDosage;
	private String itemManufacturer;
	private int itemQuantity;
	private double itemUnitPrice;
	private String totalAmount;
	private String grandTotal;
	private Date date;
	private Time time;
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getPharmaName() {
		return pharmaName;
	}
	public void setPharmaName(String pharmaName) {
		this.pharmaName = pharmaName;
	}
	public String getPharmaAddress() {
		return pharmaAddress;
	}
	public void setPharmaAddress(String pharmaAddress) {
		this.pharmaAddress = pharmaAddress;
	}
	public String getPharmaContact() {
		return pharmaContact;
	}
	public void setPharmaContact(String pharmaContact) {
		this.pharmaContact = pharmaContact;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDosage() {
		return itemDosage;
	}
	public void setItemDosage(String itemDosage) {
		this.itemDosage = itemDosage;
	}
	public String getItemManufacturer() {
		return itemManufacturer;
	}
	public void setItemManufacturer(String itemManufacturer) {
		this.itemManufacturer = itemManufacturer;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public double getItemUnitPrice() {
		return itemUnitPrice;
	}
	public void setItemUnitPrice(double unitPrice) {
		this.itemUnitPrice = unitPrice;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
}
