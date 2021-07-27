package persistence.patient.model;

import persistence.patient.dao.PatientDAO;
import persistence.patient.daoImpl.PatientDAOImpl;

/**
 * Model class to set patient details.
 * 
 * @author Deeksha Sareen
 *
 */
public class Patient {

	private int patientId;
	private String patientName;
	private String patientGender;
	private String patientDob;
	private String patientEmail;
	private String patientAddress;
	private long patientContact;
	private String password;
	private String patientType;

	private Patient() {
	}

	private static Patient patient;

	public static void setPatient(String email) {
		if (patient == null || patient.getPatientId() == 0) {
			patient = new Patient();
			patient.setPatientEmail(email);
			PatientDAO patientDAO = new PatientDAOImpl();
			patient = patientDAO.getPatient(patient);
		}
	}

	public static Patient instance() {
		return patient;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientDob() {
		return patientDob;
	}

	public void setPatientDob(String patientDob) {
		this.patientDob = patientDob;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public long getPatientContact() {
		return patientContact;
	}

	public void setPatientContact(long patientContact) {
		this.patientContact = patientContact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public static void resetPatient() {
		if(patient != null)
			patient = null;
	}
}
