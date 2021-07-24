package persistence.admin.model;

import presentation.startup.SHA_Hash;
import java.util.Locale;

/**
* <pre>
* Model class for doctor registration
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class DoctorRegistration {

  private String doctorID;
  private String fname;
  private String lname;
  private String jDate;
  private String degree;
  private String specialization;
  private String bdate;
  private Long contactNumber;
  private String city;
  private String email;
  private String password;

  public String getDoctorID() {
        return doctorID;
    }

  public String getFirstName() {
        return fname;
    }

  public String getLastName() {
        return lname;
    }

  public String getJoiningDate() {
        return jDate;
    }

  public String getDegree() {
        return degree;
    }

  public String getSpecialization() {
        return specialization;
    }

  public String getBirthDate() {
        return bdate;
    }

  public Long getContactNumber() {
        return contactNumber;
    }

  public String getCity() {
        return city;
    }

  public String getEmail() { return email;}

  public String getPassword() { return password; }

  public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

  public void setFirstName(String fname) {
        this.fname = fname.toUpperCase(Locale.ROOT);
    }

  public void setLastName(String lname) {
        this.lname = lname.toUpperCase(Locale.ROOT);
    }

  public void setJoiningDate(String jDate) {
        this.jDate = jDate;
    }

  public void setDegree(String degree) {
        this.degree = degree;
    }

  public void setSpecialization(String specialization) {
    this.specialization = specialization.toUpperCase(Locale.ROOT);
  }

  public void setBirthDate(String bdate) {
        this.bdate = bdate;
    }

  public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

  public void setCity(String city) {
        this.city = city;
    }

  public void setEmail(String email) {
        this.email = email;
    }

  public void setPassword(String password) {
    SHA_Hash sha = new SHA_Hash();
    String p = sha.getSHA(password);
    this.password = p;
  }

}
