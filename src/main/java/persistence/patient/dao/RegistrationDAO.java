package persistence.patient.dao;

public interface RegistrationDAO {
    public String addPatientDetails( String name, String DOB, double contact, String gender, String city, String email, String password, String type);
}
