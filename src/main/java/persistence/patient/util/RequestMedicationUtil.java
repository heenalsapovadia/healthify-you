package persistence.patient.util;

/**
 * <pre>
 * Request Medication Interface
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
import persistence.doctor.model.Prescription;
import persistence.patient.daoImpl.RequestMedicationDAOImpl;
import persistence.patient.model.RequestMedicationModel.MedicationsToUpdate;
import persistence.patient.model.RequestMedicationModel.RequestMedicationDetails;

import java.util.ArrayList;

public interface RequestMedicationUtil {

    // this method is for collection precription id and process the payment based on stock, name, dosage, charges..
    public RequestMedicationDetails processPrescription(Prescription currentPrescription,
                                                        RequestMedicationDAOImpl requestMedication);

    // this method is for processing payment all the data from prescription and pharma table collected
    public void makePaymentForPrescriptionsWithAmount(double amount,
                                                      ArrayList<MedicationsToUpdate> medicationsToUpdate,
                                                      RequestMedicationDAOImpl requestMedication,
                                                      int current_PrescriptionId);

}
