package persistence.patient.util;


 public interface RegistrationUtil{
    public String ValidateDate(String date);
    public String ValidateContact(Long Contact);
    public String ValidateEmail(String email);
    public String ValidatePassword(String password);
    public String ValidateNames(String name);
    public String ValidateCity(String city);	
}

