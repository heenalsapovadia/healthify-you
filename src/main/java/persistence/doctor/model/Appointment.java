package persistence.doctor.model;

import java.sql.Date;

/**
 * <pre>
 * Model class to load Doctor Appointments from database.
 * </pre>
 *
 * @author Heenal Sapovadia
 *
 */
public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private Date bookedOnDate;
    private Date bookedForDate;
    private Date rescheduledDate;
    private int billingId;

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

    public Date getBookedOnDate() {
        return bookedOnDate;
    }

    public void setBookedOnDate(Date bookedOnDate) {
        this.bookedOnDate = bookedOnDate;
    }

    public Date getBookedForDate() {
        return bookedForDate;
    }

    public void setBookedForDate(Date bookedForDate) {
        this.bookedForDate = bookedForDate;
    }

    public Date getRescheduledDate() {
        return rescheduledDate;
    }

    public void setRescheduledDate(Date rescheduledDate) {
        this.rescheduledDate = rescheduledDate;
    }

    public int getBillingId() {
        return billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }
}
