package presentation.patient;

import persistence.doctor.model.Prescription;
import persistence.patient.daoImpl.RequestMedicationDAOImpl;
import persistence.patient.model.MedicationsToUpdate;
import persistence.patient.model.Patient;
import persistence.patient.model.RequestMedicationDetails;
import persistence.patient.utilImpl.RequestMedicationUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.ScreenTitles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RequestMedicationOutput {

    public String requestMedicationDetails() {
        RequestMedicationDAOImpl requestMedication = new RequestMedicationDAOImpl();
        RequestMedicationUtilImpl requestMedicationUtil = new RequestMedicationUtilImpl();

        Scanner sc = new Scanner(System.in);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.HEADING_CHAR);

        System.out.println();
        System.out.println(ScreenTitles.REQUEST_MEDICATION + CommonConstants.TITLE_SPACE);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.HEADING_CHAR);
        System.out.println();
        System.out.println("Enter Prescription ID:");
        int current_PrescriptionId = sc.nextInt();

        List<Prescription> prescriptions = requestMedication.getPrescriptionDetails(current_PrescriptionId);
        double finalAmountForPayment = 0.0;
        ArrayList<MedicationsToUpdate> medicationsToUpdate = new ArrayList<>();
        System.out.println("Current logged in Patient Name = " + Patient.instance().getPatientName());

        if (!prescriptions.isEmpty()) {
            for (Prescription currentPrescription : prescriptions) {
                RequestMedicationDetails requestMedicationDetails = requestMedicationUtil.processPrescription(currentPrescription, requestMedication);
                if (requestMedicationDetails.totalCost > 0.0) {
                    finalAmountForPayment += requestMedicationDetails.totalCost;
                    medicationsToUpdate.add(new MedicationsToUpdate(currentPrescription.getMedicineName(), requestMedicationDetails.itemsLeft));
                }
            }
        } else {
            System.out.println("This is Incorrect prescription id for logged in patient.");
        }
        System.out.println("\n");
        if (finalAmountForPayment > 0) {
            requestMedicationUtil.makePaymentForPrescriptionsWithAmount(finalAmountForPayment,
                    medicationsToUpdate,
                    requestMedication,
                    current_PrescriptionId);
        }
        return null;
    }
}