package persistence.patient.model;

import java.sql.Date;
import java.sql.Time;

/**
 * <pre>
 * Model class for Immunization Booking.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class ImmunizationBooking {
	
	private int appointmentId;
	private int patientId;
	private int doctorId;
	private Date bookedForDate;
	private Time bookedForTime;
	private int vaccineId;
	
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public Date getBookedForDate() {
		return bookedForDate;
	}
	public void setBookedForDate(Date bookedForDate) {
		this.bookedForDate = bookedForDate;
	}
	public Time getBookedForTime() {
		return bookedForTime;
	}
	public void setBookedForTime(Time bookedForTime) {
		this.bookedForTime = bookedForTime;
	}
	public int getVaccineId() {
		return vaccineId;
	}
	public void setVaccineId(int vaccineId) {
		this.vaccineId = vaccineId;
	}
}
