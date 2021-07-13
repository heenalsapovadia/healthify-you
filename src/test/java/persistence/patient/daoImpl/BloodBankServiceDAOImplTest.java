package persistence.patient.daoImpl;

import org.junit.Test;
import static org.junit.Assert.*;

import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
import persistence.patient.utilImpl.BloodBankServiceUtilImpl;
import presentation.patient.BloodBankServiceOutput;
import presentation.startup.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BloodBankServiceDAOImplTest {
          private String patientName = "SaloniRay";
          private String patientAddress = "India";
          private String PatientDob= "1990/05/24";
          private String PatientGender= "M";
          private String Password = "parrrot88";
          private String PatientEmail = "salonimr";


    @Test
    public void getAllBloodDonationsForPatientTest() throws SQLException {
    }
}
