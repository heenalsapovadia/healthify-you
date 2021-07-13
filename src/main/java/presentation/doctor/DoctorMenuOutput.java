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
            /* TBD
            PrescribeMedicineOutput prescribeMedicineOutput = new PrescribeMedicineOutput();
            PrescribeMedicineOutput.display();
             */
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
    public static void prescribeMedication(){

        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace+CommonConstants.titleSpace+ ScreenTitles.prescription+CommonConstants.titleSpace);
        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.print(ScreenFields.appointmentId + CommonConstants.commonTextSeparator);
        int appointmentId = sc.nextInt();

        /*
        Call for Validation of appointment ID
         */
        PrescriptionValidationUtilImpl prescriptionValidationUtil = new PrescriptionValidationUtilImpl();
        Appointment validAppointment = prescriptionValidationUtil.validateAppointmentId(appointmentId);

        while(validAppointment==null){
            System.out.println(CommonErrors.invalidAppointmentId);
            System.out.print(ScreenFields.appointmentId + CommonConstants.commonTextSeparator);
            appointmentId = sc.nextInt();
            validAppointment = prescriptionValidationUtil.validateAppointmentId(appointmentId);
        }

        /*
        Prepopulate patient Id from the fetched appointment object
         */
        int patient_id = validAppointment.getPatient_id();

        System.out.print(ScreenFields.medicineNumber + CommonConstants.commonTextSeparator);
        int medicineNumber = sc.nextInt();
        List<Prescription> prescriptionList = new ArrayList<>();

        /*
        Take user input for all medicines
         */
        while(medicineNumber>0){
            System.out.print(ScreenFields.medicineName + CommonConstants.commonTextSeparator);
            String medicineName = sc.next();
            System.out.print(ScreenFields.morningDose + CommonConstants.commonTextSeparator);
            int morning = sc.nextInt();
            System.out.print(ScreenFields.afternoonDose + CommonConstants.commonTextSeparator);
            int afternoon = sc.nextInt();
            System.out.print(ScreenFields.eveningDose + CommonConstants.commonTextSeparator);
            int evening = sc.nextInt();

            Prescription prescription = new Prescription();
            prescription.setAppointment_id(appointmentId);
            prescription.setMedicine_name(medicineName);
            prescription.setMorning(morning);
            prescription.setAfternoon(afternoon);
            prescription.setEvening(evening);
            prescription.setPatient_id(patient_id);

            //dummy, needs to be replaced with current logged in DOCTOR USER's details
            prescription.setDoctor_id(123);
            prescription.setDoctor_name("Test");

            prescriptionList.add(prescription);
            medicineNumber--;
        }

        /*
        Insert the prescriptions
         */
        PrescriptionDAO prescriptionDAO = new PrescriptionDAOImpl();
        prescriptionDAO.insertPrescription(prescriptionList);

        System.out.println(ScreenFields.medicinePrescribeMessage);
    }
}
