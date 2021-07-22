package presentation.patient;

import persistence.doctor.model.Prescription;
import presentation.common.CommonConstants;
import presentation.common.ScreenTitles;

import java.util.Scanner;

public class RequestMedicationOutput {

    public static void RequestMedicationDetails(Prescription prescription) {

        Scanner sc = new Scanner(System.in);
        String bloodGroupInput;
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

        if(current_PrescriptionId == prescription.getPrescription_id()){
            System.out.println("Prescription Id validated");
        }

    }
}
