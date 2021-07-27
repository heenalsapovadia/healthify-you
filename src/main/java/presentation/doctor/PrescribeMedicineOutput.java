package presentation.doctor;

import persistence.admin.dao.PharmaInvoiceDAO;
import persistence.admin.daoImpl.PharmaInvoiceDAOImpl;
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
import java.util.Set;

/**
 * <pre>
 * Loads Prescribe Medication in the application.
 * </pre>
 *
 * @author Heenal Sapovadia
 *
 */
public class PrescribeMedicineOutput {

    private PrintToConsole consoleObj;

    public PrescribeMedicineOutput() {
        consoleObj = PrintToConsole.getInstance();
    }

    public void prescribeMedication(){
        consoleObj.printHeader(ScreenTitles.MEDICINE_PRESCRIPTION);

        Scanner scanner = new Scanner(System.in);
        System.out.print(ScreenFields.APPOINTMENT_NO + CommonConstants.COMMON_TEXT_SEPARATOR);
        int appointmentId = scanner.nextInt();

        PrescriptionValidationUtilImpl prescriptionValidationUtil = new PrescriptionValidationUtilImpl();
        Appointment validAppointment = prescriptionValidationUtil.validateAppointmentId(appointmentId);

        while(validAppointment==null){
            consoleObj.printError(CommonErrors.INVALID_APPOINTMENT_ID);
            System.out.print(ScreenFields.APPOINTMENT_ID + CommonConstants.COMMON_TEXT_SEPARATOR);
            appointmentId = scanner.nextInt();
            validAppointment = prescriptionValidationUtil.validateAppointmentId(appointmentId);
        }

        /*
        Prepopulate patient Id from the fetched appointment object
         */
        int patient_id = validAppointment.getPatientId();

        System.out.print(ScreenFields.MEDICINE_NUMBER + CommonConstants.COMMON_TEXT_SEPARATOR);
        int medicineNumber = scanner.nextInt();
        List<Prescription> prescriptionList = new ArrayList<>();

        PharmaInvoiceDAO pharmaInvoiceDAO = new PharmaInvoiceDAOImpl();
        Set<String> medicineList = pharmaInvoiceDAO.getMedicineList();

        /*
        Take user input for all medicines
         */
        while(medicineNumber>0){
            System.out.print(ScreenFields.MEDICINE_NAME + CommonConstants.COMMON_TEXT_SEPARATOR);
            String medicineName = scanner.next();
            if(!prescriptionValidationUtil.validateMedicineName(medicineName, medicineList)) {
                consoleObj.printError(CommonErrors.MEDICINE_NAME_NOT_FOUND);
                continue;
            }
            System.out.print(ScreenFields.MORNING_DOSE + CommonConstants.COMMON_TEXT_SEPARATOR);
            int morning = scanner.nextInt();
            System.out.print(ScreenFields.AFTERNOON_DOSE + CommonConstants.COMMON_TEXT_SEPARATOR);
            int afternoon = scanner.nextInt();
            System.out.print(ScreenFields.EVENING_DOSE + CommonConstants.COMMON_TEXT_SEPARATOR);
            int evening = scanner.nextInt();
            System.out.print(ScreenFields.DOSAGE_DAYS + CommonConstants.COMMON_TEXT_SEPARATOR);
            int dosageDays = scanner.nextInt();

            Prescription prescription = new Prescription();
            prescription.setAppointmentId(appointmentId);
            prescription.setMedicineName(medicineName);
            prescription.setMorning(morning);
            prescription.setAfternoon(afternoon);
            prescription.setEvening(evening);
            prescription.setPatientId(patient_id);
            prescription.setDosageDays(dosageDays);
            prescription.setDate(Date.valueOf(LocalDate.now()));

            prescription.setDoctorId(Doctor.instance().getDoctorId());
            prescription.setDoctorName(Doctor.instance().getFirstName()+" "+Doctor.instance().getLastName());

            prescriptionList.add(prescription);
            medicineNumber--;
        }

        /*
        Insert the prescriptions
         */
        PrescriptionDAO prescriptionDAO = new PrescriptionDAOImpl();
        prescriptionDAO.insertPrescription(prescriptionList);

        System.out.println(ScreenFields.MEDICINE_PRESCRIBE_MESSAGE);
    }
}
