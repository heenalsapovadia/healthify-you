package persistence.patient.utilImpl;

import persistence.patient.dao.LabCheckBookingDAO;
import persistence.patient.daoImpl.LabCheckBookingDAOImpl;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckBookingUtil;
import presentation.common.PrintToConsole;
import java.sql.Date;
import java.util.List;

/**
 * @author Heenal Sapovadia
 *
 */
public class LabCheckBookingUtilImpl implements LabCheckBookingUtil {
    /*
    take user input, set LabCheckBooking obj, call insertBooking(obj)
     */
    PrintToConsole consoleObj = PrintToConsole.getInstance();

    @Override
    public void makeBooking(int healthCheckId, Date bookingDate, int billingId) {
        LabCheckBooking labCheckBooking = new LabCheckBooking();

        labCheckBooking.setPatientId(Patient.instance().getPatientId());
        labCheckBooking.setHealthcheckId(healthCheckId);
        labCheckBooking.setBookedForDate(bookingDate);
        labCheckBooking.setBillingId(billingId);

        LabCheckBookingDAO labCheckBookingDao = new LabCheckBookingDAOImpl();
        labCheckBookingDao.insertBooking(labCheckBooking);
    }

    @Override
    public List<LabCheckBooking> fetchBookings() {
        LabCheckBookingDAO labCheckBookingDao = new LabCheckBookingDAOImpl();
        return labCheckBookingDao.getAllBookings();
    }
}
