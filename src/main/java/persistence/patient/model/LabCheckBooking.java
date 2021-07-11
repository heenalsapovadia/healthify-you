package persistence.patient.model;

import java.sql.Date;

public class LabCheckBooking {
    int appointment_id;
    int patient_id;
    int healthcheck_id;
    Date booked_for_date;
    Date rescheduled_date;
    int billing_id;

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getHealthcheck_id() {
        return healthcheck_id;
    }

    public void setHealthcheck_id(int healthcheck_id) {
        this.healthcheck_id = healthcheck_id;
    }

    public Date getBooked_for_date() {
        return booked_for_date;
    }

    public void setBooked_for_date(Date booked_for_date) {
        this.booked_for_date = booked_for_date;
    }

    public Date getRescheduled_date() {
        return rescheduled_date;
    }

    public void setRescheduled_date(Date rescheduled_date) {
        this.rescheduled_date = rescheduled_date;
    }

    public int getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(int billing_id) {
        this.billing_id = billing_id;
    }
}
