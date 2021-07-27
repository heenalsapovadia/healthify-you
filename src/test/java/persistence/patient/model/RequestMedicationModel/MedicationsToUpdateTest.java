package persistence.patient.model.RequestMedicationModel;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * <pre>
 * Medication Update test method for Request Medication Tool
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class MedicationsToUpdateTest {
    @Test
    public void testConstructor() {
        MedicationsToUpdate actualMedicationsToUpdate = new MedicationsToUpdate("Medication Name", 1);
        assertEquals(1, actualMedicationsToUpdate.medicationLeft);
        assertEquals("Medication Name", actualMedicationsToUpdate.medicationName);
    }
}

