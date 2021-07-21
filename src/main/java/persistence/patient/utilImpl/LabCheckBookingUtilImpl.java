package persistence.patient.utilImpl;

import persistence.patient.dao.LabCheckBookingDAO;
import persistence.patient.daoImpl.LabCheckBookingDAOImpl;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckBookingUtil;
import presentation.common.PrintToConsole;
import java.sql.Date;
import java.util.List;

public class LabCheckBookingUtilImpl implements LabCheckBookingUtil {
    /*
    take user input, set LabCheckBooking obj, call insertBooking(obj)
     */
    PrintToConsole consoleObj = PrintToConsole.getInstance();

    @Override
    public void makeBooking(int healthCheckId, Date bookingDate, int billingId) {
        LabCheckBooking labCheckBooking = new LabCheckBooking();

        labCheckBooking.setPatient_id(Patient.getPatient().getPatientId());
        labCheckBooking.setHealthcheck_id(healthCheckId);
        labCheckBooking.setBooked_for_date(bookingDate);
        labCheckBooking.setBilling_id(billingId);

        LabCheckBookingDAO labCheckBookingDao = new LabCheckBookingDAOImpl();
        labCheckBookingDao.insertBooking(labCheckBooking);
    }

    @Override
    public List<LabCheckBooking> fetchBookings() {
        LabCheckBookingDAO labCheckBookingDao = new LabCheckBookingDAOImpl();
        return labCheckBookingDao.getAllBookings();
    }
}
