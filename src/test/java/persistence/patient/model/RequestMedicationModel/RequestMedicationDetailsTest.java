package persistence.patient.model.RequestMedicationModel;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * <pre>
 * RequestMedicationDetails Test method
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class RequestMedicationDetailsTest {
    @Test
    public void testConstructor() {
        RequestMedicationDetails actualRequestMedicationDetails = new RequestMedicationDetails(10.0, 42);
        assertEquals(42, actualRequestMedicationDetails.itemsLeft);
        assertEquals(10.0, actualRequestMedicationDetails.totalCost, 0.0);
    }
}

