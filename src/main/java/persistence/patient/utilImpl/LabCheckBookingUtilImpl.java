package persistence.patient.utilImpl;

import persistence.patient.dao.LabCheckBookingDAO;
import persistence.patient.daoImpl.LabCheckBookingDAOImpl;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckBookingUtil;
import java.sql.Date;

/**
 * @author Heenal Sapovadia
 *
 */
public class LabCheckBookingUtilImpl implements LabCheckBookingUtil {

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

}
