package persistence.patient.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import persistence.admin.model.PharmaInvoice;
import persistence.doctor.model.Appointment;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;

/**
 * <pre>
 * Model class to set invoice details.
 * Sets objects for
 * 	1. Doctor's appointment
 * 	2. Request Medication
 * 	3. Immunization Booking
 * 	4. Lab Test Booking
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class Invoice {
	
	private int prescriptionId;
	private int billId;
	private String patientName;
	private String address;
	private String contactNumber;
	private Timestamp originalDatetime;
	private double amount;
	private double total;
	private int age;
	private char gender;
	private List<Appointment> appointmentList;
	private List<PharmaInvoice> pharmaInvoiceList;
	private Map<Integer, String> vaccineMap;
	private List<LabCheckBooking> labCheckBookingList;
	private Map<Integer, PaymentInterface> paymentMap;
	private Map<Integer, String> doctorDetail;
	private Map<Integer, String> labCheckMap;
	
	public int getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Timestamp getOriginalDatetime() {
		return originalDatetime;
	}
	public void setOriginalDatetime(Timestamp originalDatetime) {
		this.originalDatetime = originalDatetime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}
	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}
	public List<PharmaInvoice> getPharmaInvoiceList() {
		return pharmaInvoiceList;
	}
	public void setPharmaInvoiceList(List<PharmaInvoice> pharmaInvoiceList) {
		this.pharmaInvoiceList = pharmaInvoiceList;
	}
	public Map<Integer, String> getVaccineMap() {
		return vaccineMap;
	}
	public void setVaccineMap(Map<Integer, String> vaccineMap) {
		this.vaccineMap = vaccineMap;
	}
	public List<LabCheckBooking> getLabCheckBookingList() {
		return labCheckBookingList;
	}
	public void setLabCheckBookingList(List<LabCheckBooking> labCheckBookingList) {
		this.labCheckBookingList = labCheckBookingList;
	}
	public Map<Integer, PaymentInterface> getPaymentMap() {
		return paymentMap;
	}
	public void setPaymentMap(Map<Integer, PaymentInterface> paymentMap) {
		this.paymentMap = paymentMap;
	}
	public Map<Integer, String> getDoctorDetail() {
		return doctorDetail;
	}
	public void setDoctorDetail(Map<Integer, String> doctorDetail) {
		this.doctorDetail = doctorDetail;
	}
	public Map<Integer, String> getLabCheckMap() {
		return labCheckMap;
	}
	public void setLabCheckMap(Map<Integer, String> labCheckMap) {
		this.labCheckMap = labCheckMap;
	}
}
