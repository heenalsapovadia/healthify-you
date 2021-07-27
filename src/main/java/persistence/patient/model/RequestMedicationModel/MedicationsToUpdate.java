package persistence.patient.model.RequestMedicationModel;
/**
 * <pre>
 * // this is MedicationsToUpdate model
 * // method tested at package persistence.patient.model.RequestMedicationModel;
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class MedicationsToUpdate {
    public String medicationName;
    public int medicationLeft;

    public MedicationsToUpdate(String medicationName, int medicationLeft) {
        this.medicationName = medicationName;
        this.medicationLeft = medicationLeft;
    }
}

