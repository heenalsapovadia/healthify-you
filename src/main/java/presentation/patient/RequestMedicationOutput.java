package presentation.patient;

import persistence.doctor.model.Prescription;
import persistence.patient.model.Patient;
import presentation.common.CommonConstants;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import java.util.Scanner;

public class RequestMedicationOutput {

    public static String RequestMedicationDetails() {
        Prescription prescription1 =  new Prescription();
        int prescriptionIndDatabase = prescription1.getPrescription_id();
        Scanner sc = new Scanner(System.in);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace + CommonConstants.titleSpace + ScreenTitles.REQUEST_MEDICATION + CommonConstants.titleSpace);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        int current_PrescriptionId;
        System.out.println("Enter Prescription ID:");
        current_PrescriptionId = sc.nextInt();

        System.out.println("Validating prescrption with Patient_id");
        if(current_PrescriptionId == Patient.getPatient().getPatientId()){
            System.out.println("Given prescription matches with Patient_ID " + Patient.getPatient().getPatientId());
            System.out.println("Prescription Id validated");
        }

        int userSelection = 0;
        userSelection = sc.nextInt();
        if (userSelection == 1) {
            System.out.println("Check/view medicine stock.");
        }
        if (userSelection == 2) {
            System.out.println("EXIT!");
        }
        return null;
    }
}
