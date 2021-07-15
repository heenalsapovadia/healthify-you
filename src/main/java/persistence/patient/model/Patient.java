package persistence.patient.model;

import persistence.patient.dao.PatientDAO;
import persistence.patient.daoImpl.PatientDAOImpl;

public class Patient {

	private int patientId;
	private String PatientName;
	private String PatientGender;
	private String PatientDob;
	private String PatientEmail;
	private String PatientAddress;
	private long PatientContact;
	private String Password;
	private String PatientType;
	
	private Patient() {}
  
	private static Patient patient;
  
	public static void setPatient(String email) {
		if(patient == null) {
			patient = new Patient();
			patient.setPatientEmail(email);
			PatientDAO patientDAO = new PatientDAOImpl();
			patient = patientDAO.getPatient(patient);
		}
	}
	
	public static Patient getPatient() {
		return patient;
	}
  
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getPatientGender() {
		return PatientGender;
	}
	public void setPatientGender(String patientGender) {
		PatientGender = patientGender;
	}
	public String getPatientDob() {
		return PatientDob;
	}
	public void setPatientDob(String patientDob) {
		PatientDob = patientDob;
	}
	public String getPatientEmail() {
		return PatientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		PatientEmail = patientEmail;
	}
	public String getPatientAddress() {
		return PatientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		PatientAddress = patientAddress;
	}
	public long getPatientContact() {
		return PatientContact;
	}
	public void setPatientContact(long patientContact) {
		PatientContact = patientContact;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPatientType() {
		return PatientType;
	}
	public void setPatientType(String patientType) {
		PatientType = patientType;
	}

}
