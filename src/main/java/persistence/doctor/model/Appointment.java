package persistence.doctor.model;

import java.sql.Date;

public class Appointment {
    private int appointment_id;
    private int patient_id;
    private int doctor_id;
    private Date booked_on_date;
    private Date booked_for_date;
    private Date rescheduled_date;
    private int billing_id;

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

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getBooked_on_date() {
        return booked_on_date;
    }

    public void setBooked_on_date(Date booked_on_date) {
        this.booked_on_date = booked_on_date;
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
