package persistence.patient.model;

public class RequestMedicationDetails {
    public double totalCost;
    public int itemsLeft;
    public RequestMedicationDetails(double totalCost, int itemsLeft) {
        this.totalCost = totalCost;
        this.itemsLeft = itemsLeft;
    }
}