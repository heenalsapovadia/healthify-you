package presentation.doctor;

import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.daoImpl.PrescriptionDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Prescription;
import persistence.doctor.utilImpl.PrescriptionValidationUtilImpl;
import presentation.CommonConstants;
import presentation.ScreenTitles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorMenuOutput {

    public static void prescribeMedication(){

        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace+CommonConstants.titleSpace+ ScreenTitles.prescription+CommonConstants.titleSpace);
        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.print("Appointment ID : ");
        int appointmentId = sc.nextInt();

        /*
        Validate appointment ID
         */
        PrescriptionValidationUtilImpl prescriptionValidationUtil = new PrescriptionValidationUtilImpl();
        Appointment validAppointment = prescriptionValidationUtil.validateAppointmentId(appointmentId);
        while(validAppointment==null){
            System.out.println("INVALID Appointment Id entered, Please try again...");
            System.out.print("Appointment ID : ");
            appointmentId = sc.nextInt();
            validAppointment = prescriptionValidationUtil.validateAppointmentId(appointmentId);
        }

        /*
        Prepopulate patient Id from the fetched appointment object
         */
        int patient_id = validAppointment.getPatient_id();

        System.out.print("Number of medicines to prescribe : ");
        int medicineNumber = sc.nextInt();
        List<Prescription> prescriptionList = new ArrayList<>();
        while(medicineNumber>0){
            System.out.print("Medicine Name : ");
            String medicineName = sc.next();
            System.out.print("Morning : ");
            int morning = sc.nextInt();
            System.out.print("Afternoon : ");
            int afternoon = sc.nextInt();
            System.out.print("Evening : ");
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
        PrescriptionDAO prescriptionDAO = new PrescriptionDAOImpl();
        prescriptionDAO.insertPrescription(prescriptionList);
        System.out.println("Medicines prescribed successfully!");
    }
}
