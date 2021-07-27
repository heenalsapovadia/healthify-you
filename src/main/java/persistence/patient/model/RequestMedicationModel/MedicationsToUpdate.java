package persistence.patient.model.RequestMedicationModel;

// this methos is Medication to update model

public class MedicationsToUpdate {
    public String medicationName;
    public int medicationLeft;
    public MedicationsToUpdate(String medicationName, int medicationLeft) {
        this.medicationName = medicationName;
        this.medicationLeft = medicationLeft;
    }
}

