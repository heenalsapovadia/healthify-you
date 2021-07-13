package persistence.patient.model;


import java.util.List;

public class Patient {

    private static String PatientName;
    private static String PatientGender;
    private static String PatientDob;
    private static String PatientEmail;
    private static String PatientAddress;
    private static long PatientContact;
    private static String Password;
    private static String PatientType;

    public String getPatientName() {
        return PatientName;
    }
    public  void setPatientName(String patientName) {
        PatientName = patientName;
    }
    public  String getPatientGender() {
        return PatientGender;
    }
    public  void setPatientGender(String patientGender) {
        PatientGender = patientGender;
    }
    public  String getPatientDob() {
        return PatientDob;
    }
    public void setPatientDob(String patientDob) {
        PatientDob = patientDob;
    }
    public String getPatientEmail() {
        return PatientEmail;
    }
    public  void setPatientEmail(String patientEmail) {
        PatientEmail = patientEmail;
    }
    public String getPatientAddress() {
        return PatientAddress;
    }
    public void setPatientAddress(String patientAddress) {
        PatientAddress = patientAddress;
    }
    public  long getPatientContact() {
        return PatientContact;
    }
    public  void setPatientContact(long patientContact) {
        PatientContact = patientContact;
    }
    public  String getPassword() {
        return Password;
    }
    public  void setPassword(String password) {
        Password = password;
    }
    public  String getPatientType() {
        return PatientType;
    }
    public  void setPatientType(String patientType) {
        PatientType = patientType;
    }

}
