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

  private String doctorID;    /* stores the doctor identifier of the doctor */
  private String fname;    /* stores the first name of the doctor */
  private String lname;    /* stores the last name of the doctor */
  private String jDate;    /* stores the joining date of the doctor */
  private String degree;    /* stores the degree of the doctor */
  private String specialization;    /* stores the specialization of the doctor */
  private String bdate;    /* stores the birth date of the doctor */
  private Long contactNumber;    /* stores the contact number of the doctor */
  private String city;    /* stores the city of the doctor */
  private String email;    /* stores the email address of the doctor */
  private String password;    /* stores the password of the doctor */

  /* getter method to get doctorID */
  public String getDoctorID() {
    return doctorID;
  }

  /* getter method to get first name */
  public String getFirstName() {
    return fname;
  }

  /* getter method to get last name */
  public String getLastName() {
    return lname;
  }

  /* getter method to get joining date */
  public String getJoiningDate() {
    return jDate;
  }

  /* getter method to get degree */
  public String getDegree() {
    return degree;
  }

  /* getter method to get specialization */
  public String getSpecialization() {
    return specialization;
  }

  /* getter method to get birth date */
  public String getBirthDate() {
    return bdate;
  }

  /* getter method to get contact number */
  public Long getContactNumber() {
    return contactNumber;
  }

  /* getter method to get city */
  public String getCity() {
    return city;
  }

  /* getter method to get email */
  public String getEmail() { return email;}

  /* getter method to get password */
  public String getPassword() { return password; }

  /* setter method to get doctorID */
  public void setDoctorID(String doctorID) {
    this.doctorID = doctorID;
  }

  /* setter method to set first name */
  public void setFirstName(String fname) {
    this.fname = fname.toUpperCase(Locale.ROOT);
  }

  /* setter method to set last name */
  public void setLastName(String lname) {
    this.lname = lname.toUpperCase(Locale.ROOT);
  }

  /* setter method to set joining date */
  public void setJoiningDate(String jDate) {
    this.jDate = jDate;
  }

  /* setter method to set degree */
  public void setDegree(String degree) {
    this.degree = degree;
  }

  /* setter method to set specialization */
  public void setSpecialization(String specialization) {
    this.specialization = specialization.toUpperCase(Locale.ROOT);
  }

  /* getter method to set birth date */
  public void setBirthDate(String bdate) {
    this.bdate = bdate;
  }

  /* setter method to set contact number */
  public void setContactNumber(Long contactNumber) {
    this.contactNumber = contactNumber;
  }

  /* setter method to set city */
  public void setCity(String city) {
    this.city = city;
  }

  /* setter method to set email */
  public void setEmail(String email) {
    this.email = email;
  }

  /* setter method to set password */
  public void setPassword(String password) {
    SHA_Hash sha = new SHA_Hash();
    String p = sha.getSHA(password);
    this.password = p;
  }

}
