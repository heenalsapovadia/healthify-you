package presentation.doctor;

import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.daoImpl.PrescriptionDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Prescription;
import persistence.doctor.utilImpl.PrescriptionValidationUtilImpl;
import presentation.common.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrescribeMedicineOutput {

    PrintToConsole consoleObj = PrintToConsole.getInstance();

    public void prescribeMedication(){
        consoleObj.printHeader(ScreenTitles.prescription);

        Scanner scanner = new Scanner(System.in);
        System.out.print(ScreenFields.appointmentId + CommonConstants.commonTextSeparator);
        int appointmentId = scanner.nextInt();

        /*
        Call for Validation of appointment ID
         */
        PrescriptionValidationUtilImpl prescriptionValidationUtil = new PrescriptionValidationUtilImpl();
        Appointment validAppointment = prescriptionValidationUtil.validateAppointmentId(appointmentId);

        while(validAppointment==null){
            System.out.println(CommonErrors.INVALID_APPOINTMENT_ID);
            System.out.print(ScreenFields.appointmentId + CommonConstants.commonTextSeparator);
            appointmentId = scanner.nextInt();
            validAppointment = prescriptionValidationUtil.validateAppointmentId(appointmentId);
        }

        /*
        Prepopulate patient Id from the fetched appointment object
         */
        int patient_id = validAppointment.getPatient_id();

        System.out.print(ScreenFields.medicineNumber + CommonConstants.commonTextSeparator);
        int medicineNumber = scanner.nextInt();
        List<Prescription> prescriptionList = new ArrayList<>();

        /*
        Take user input for all medicines
         */
        while(medicineNumber>0){
            System.out.print(ScreenFields.medicineName + CommonConstants.commonTextSeparator);
            String medicineName = scanner.next();
            System.out.print(ScreenFields.morningDose + CommonConstants.commonTextSeparator);
            int morning = scanner.nextInt();
            System.out.print(ScreenFields.afternoonDose + CommonConstants.commonTextSeparator);
            int afternoon = scanner.nextInt();
            System.out.print(ScreenFields.eveningDose + CommonConstants.commonTextSeparator);
            int evening = scanner.nextInt();

            Prescription prescription = new Prescription();
            prescription.setAppointmentId(appointmentId);
            prescription.setMedicineName(medicineName);
            prescription.setMorning(morning);
            prescription.setAfternoon(afternoon);
            prescription.setEvening(evening);
            prescription.setPatientId(patient_id);

            //dummy, needs to be replaced with current logged in DOCTOR USER's details
            prescription.setDoctorId(123);
            prescription.setDoctorName("Test");

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
