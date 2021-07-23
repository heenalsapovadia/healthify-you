package presentation.doctor;

import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.daoImpl.PrescriptionDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Doctor;
import persistence.doctor.model.Prescription;
import persistence.doctor.utilImpl.PrescriptionValidationUtilImpl;
import presentation.common.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrescribeMedicineOutput {

    PrintToConsole consoleObj = PrintToConsole.getInstance();

    public void prescribeMedication(){
        consoleObj.printHeader(ScreenTitles.prescription);

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
            prescription.setDate(Date.valueOf(LocalDate.now()));

            prescription.setDoctor_id(Doctor.getDoctor().getDoctor_id());
            prescription.setDoctor_name(Doctor.getDoctor().getFirst_name()+" "+Doctor.getDoctor().getLast_name());

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
