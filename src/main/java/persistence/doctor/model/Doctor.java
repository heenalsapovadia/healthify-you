package persistence.doctor.model;

import persistence.doctor.daoImpl.DoctorDAOImpl;
import java.util.Date;

/**
 * Model class to set doctor details.
 *
 * @author Heenal Sapovadia
 *
 */
public class Doctor {
    private int doctorId;
    private String firstName;
    private String lastName;
    private Date joiningDate;
    private String degree;
    private String specialization;
    private Date birthDate;
    private String contactNumber;
    private String city;
    private String email;

    private static Doctor doctor;

    private Doctor(){}

    public static Doctor instance(){
        return doctor;
    }

    public static void setDoctor(String email){
        doctor = new Doctor();
        doctor.setEmail(email);
        DoctorDAOImpl doctorDAO = new DoctorDAOImpl();
        doctor = doctorDAO.getDoctor(doctor);
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
