package persistence.doctor.model;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * <pre>
 *  Scheduled Appointments Model Test Method
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class ScheduledAppointmentsModelTest {
    @Test
    public void testConstructor() {
        ScheduledAppointmentsModel actualScheduledAppointmentsModel = new ScheduledAppointmentsModel("Name", 1);
        assertEquals(1, actualScheduledAppointmentsModel.age);
        assertEquals("Name", actualScheduledAppointmentsModel.name);
    }
}

