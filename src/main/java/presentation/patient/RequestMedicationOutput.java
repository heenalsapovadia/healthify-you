package presentation.patient;

import persistence.doctor.model.Prescription;
import persistence.patient.dao.RequestMedicationDAO;
import persistence.patient.daoImpl.RequestMedicationDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.model.RequestMedicationModel.MedicationsToUpdate;
import persistence.patient.model.RequestMedicationModel.RequestMedicationDetails;
import persistence.patient.util.RequestMedicationUtil;
import persistence.patient.utilImpl.RequestMedicationUtilImpl;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import java.util.*;
/**
 * <pre>
 * Request Medication Presentation - output of all methods
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class RequestMedicationOutput {

    public static void requestMedicationDashboard() {

        PrintToConsole consoleObj = PrintToConsole.getInstance();
        /*
          Main dashboard for Request Medication options list
        */
        List<String> selectionOptions = Arrays.asList(ScreenFields.GETTING_PRESCRIPTION_ID, ScreenFields.EXIT_FROM_REQUEST_MEDICATION);

        while (true) {
            consoleObj.printHeader(ScreenTitles.REQUEST_MEDICATION);
            int option = consoleObj.printSelection(selectionOptions);
            System.out.println(ScreenFields.ENTER_PRESCRIPTION_ID);

            switch (option) {
                case 1:
                    requestMedicationDetails();
                case 2:
                    return;
            }
            consoleObj.printSingleNewLine();
        }
    }

    // this method displays list of medicine based on prescription id validation... and futher process
    public static String requestMedicationDetails() {
        RequestMedicationDAO requestMedication = new RequestMedicationDAOImpl();
        RequestMedicationUtil requestMedicationUtil = new RequestMedicationUtilImpl();

        // Enter valid prescription ID//
        Scanner sc = new Scanner(System.in);
        int current_PrescriptionId;
        current_PrescriptionId = sc.nextInt();

        List<Prescription> prescriptions = requestMedication.getPrescriptionDetails(current_PrescriptionId);
        double finalAmountForPayment = 0.0;
        ArrayList<MedicationsToUpdate> medicationsToUpdate = new ArrayList<>();
        System.out.println(ScreenFields.CURRENT_LOGGEDIN_PATIENT + Patient.instance().getPatientName());

        if (!prescriptions.isEmpty()) {
            for (Prescription currentPrescription : prescriptions) {
                RequestMedicationDetails requestMedicationDetails = requestMedicationUtil.processPrescription(currentPrescription, requestMedication);
                if (requestMedicationDetails.totalCost > 0.0) {
                    finalAmountForPayment += requestMedicationDetails.totalCost;
                    medicationsToUpdate.add(new MedicationsToUpdate(currentPrescription.getMedicineName(), requestMedicationDetails.itemsLeft));
                }
            }
        } else {
            System.out.println(ScreenFields.INCORRECT_PRESCRIPTION_ID);
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
