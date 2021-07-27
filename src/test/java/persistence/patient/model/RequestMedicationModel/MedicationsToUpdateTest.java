package persistence.patient.model.RequestMedicationModel;
/**
 * <pre>
 * Medication Update test method
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MedicationsToUpdateTest {
    @Test
    public void testConstructor() {
        MedicationsToUpdate actualMedicationsToUpdate = new MedicationsToUpdate("Medication Name", 1);

        assertEquals(1, actualMedicationsToUpdate.medicationLeft);
        assertEquals("Medication Name", actualMedicationsToUpdate.medicationName);
    }
}

