package persistence.doctor.model;

import java.sql.Date;

public class Prescription {
    private int prescriptionId;
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String doctorName;
    private String medicineName;
    private int morning;
    private int afternoon;
    private int evening;
    private int dosageDays;
    private Date prescriptionDate;
    private int billingId;

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getMorning() {
        return morning;
    }

    public void setMorning(int morning) {
        this.morning = morning;
    }

    public int getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(int afternoon) {
        this.afternoon = afternoon;
    }

    public int getEvening() {
        return evening;
    }

    public void setEvening(int evening) {
        this.evening = evening;
    }

    public int getDosageDays() { return dosageDays; }

    public void setDosageDays(int dosageDays) { this.dosageDays = dosageDays; }
    
    public Date getPrescriptionDate() {
		return prescriptionDate;
	}

	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public int getBillingId() {
		return billingId;
	}

	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}
}
