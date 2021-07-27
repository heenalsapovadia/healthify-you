package persistence.patient.model.RequestMedicationModel;
/**
 * <pre>
 // Model for requesting medication for charges and items left
 // test method RequestMedicationDetailsDetailsTest written at package persistence.patient.model.RequestMedicationModel;
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class RequestMedicationDetails {
    public double totalCost;
    public int itemsLeft;
    public RequestMedicationDetails(double totalCost, int itemsLeft) {
        this.totalCost = totalCost;
        this.itemsLeft = itemsLeft;
    }
}
