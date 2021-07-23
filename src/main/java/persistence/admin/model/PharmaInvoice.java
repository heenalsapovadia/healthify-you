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
	private String itemName;
	private String itemDosage;
	private String itemManufacturer;
	private int itemQuantity;
	private double itemUnitPrice;
	private Date date;
	private Time time;
	private int itemUpdatedQuantity;
	private Date expiryDate;
	private int orderedQuantity;

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

	public int getItemUpdatedQuantity() {
		return itemUpdatedQuantity;
	}

	public void setItemUpdatedQuantity(int itemUpdatedQuantity) {
		this.itemUpdatedQuantity = itemUpdatedQuantity;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		PharmaInvoice invoice = (PharmaInvoice) obj;
		return compareAllFields(invoice);
	}

	public boolean compareAllFields(PharmaInvoice invoice) {
		return this.invoiceId == invoice.invoiceId && this.pharmaName.equals(invoice.pharmaName)
				&& this.pharmaAddress.equals(invoice.pharmaAddress) && this.pharmaContact.equals(invoice.pharmaContact)
				&& this.paymentMode.equals(invoice.paymentMode) && this.itemName.equals(invoice.itemName)
				&& this.itemDosage.equals(invoice.itemDosage) && this.itemManufacturer.equals(invoice.itemManufacturer)
				&& this.itemQuantity == invoice.itemQuantity && this.itemUnitPrice == invoice.itemUnitPrice
				&& this.date.equals(invoice.date) && this.time.equals(invoice.time);
	}
}
