package presentation.doctor;

import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.daoImpl.PrescriptionDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Prescription;
import persistence.doctor.utilImpl.PrescriptionValidationUtilImpl;
import presentation.CommonConstants;
import presentation.CommonErrors;
import presentation.ScreenFields;
import presentation.ScreenTitles;
import presentation.admin.DoctorRegistrationOutput;
import presentation.admin.InvoiceOutput;
import presentation.startup.ApplicationOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class DoctorMenuOutput {

    private static final Logger LOGGER = Logger.getLogger(DoctorMenuOutput.class.getName());

    private DoctorMenuOutput(){}

    private static DoctorMenuOutput doctorMenuOutput;

    public static DoctorMenuOutput getInstance() {
        if(doctorMenuOutput == null)
            doctorMenuOutput = new DoctorMenuOutput();
        return doctorMenuOutput;
    }

    public void displayOutput() {
        loadHeader();
        loadScreenOptions(new Scanner(System.in));
    }

    private void loadHeader() {
        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace+ScreenTitles.doctorDashboard+CommonConstants.titleSpace);
        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);
        System.out.println();
    }

    private int loadScreenOptions(Scanner sc) {
        int sel = -1;
        System.out.println("1. "+ScreenFields.prescribeMedicine);
        System.out.println("2. "+ScreenFields.viewAppointment);
        System.out.println("3. "+ScreenFields.logout);
        System.out.println(ScreenFields.selection);
        if(sc.hasNextInt())
            sel = sc.nextInt();
        else {
            System.err.println(CommonErrors.invalidSelection);
            sel = loadScreenOptions(new Scanner(System.in));
        }
        if(sel == 1) {
            System.out.println("Prescribing medssss");

            PrescribeMedicineOutput prescribeMedicineOutput = new PrescribeMedicineOutput();
            prescribeMedicineOutput.prescribeMedication();

        }
        else if(sel == 2) {
            //add code for View Appointment for Doctor here
        }
        else if(sel == 3) {
            System.out.println(ScreenFields.logoutMessage);
            System.out.println(ScreenFields.applicationTerminationMessage);
            sc.close();
            System.exit(0);
        }
        else {
            System.out.println(CommonErrors.invalidSelection);
            sel = loadScreenOptions(new Scanner(System.in));
        }
        return sel;
    }

}
