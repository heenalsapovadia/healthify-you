package persistence.patient.model.RequestMedicationModel;

// Model for requesting medication for charges and itemsleft

public class RequestMedicationDetails {
    public double totalCost;
    public int itemsLeft;
    public RequestMedicationDetails(double totalCost, int itemsLeft) {
        this.totalCost = totalCost;
        this.itemsLeft = itemsLeft;
    }
}
