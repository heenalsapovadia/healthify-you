package persistence.patient.model;

import java.sql.Date;

/**
 * <pre>
 * Model class to load Lab Check Bookings from database.
 * </pre>
 *
 * @author Heenal Sapovadia
 *
 */
public class LabCheckBooking {
    int appointmentId;
    int patientId;
    int healthcheckId;
    Date bookedForDate;
    Date rescheduledDate;
    int billingId;

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

    public int getHealthcheckId() {
        return healthcheckId;
    }

    public void setHealthcheckId(int healthcheckId) {
        this.healthcheckId = healthcheckId;
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
