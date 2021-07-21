package persistence.patient.utilImpl;

import org.junit.Test;
import persistence.patient.dao.LabCheckBookingDAO;
import persistence.patient.daoImpl.LabCheckBookingDAOImpl;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.util.LabCheckBookingUtil;
import java.sql.Date;
import java.util.List;
import static org.junit.Assert.*;

public class LabCheckBookingUtilImplTest {

    @Test
    public void makeBooking() {
        int healthCheckId = 2;
        Date bookingdate = Date.valueOf("2021-07-30");
        int billingId = 123;
        LabCheckBookingUtil labCheckBookingUtil = new LabCheckBookingUtilImpl();
        labCheckBookingUtil.makeBooking(healthCheckId, bookingdate, billingId);

        LabCheckBookingDAO labCheckBookingDao = new LabCheckBookingDAOImpl();
        assertNotNull(labCheckBookingDao.getBookingByDate(bookingdate));
    }

    @Test
    public void fetchBookings() {
        LabCheckBooking labCheckBooking = new LabCheckBooking();
        labCheckBooking.setPatient_id(1);
        labCheckBooking.setHealthcheck_id(2);
        labCheckBooking.setBooked_for_date(Date.valueOf("2021-05-21"));
        labCheckBooking.setBilling_id(123);

        LabCheckBookingDAO labCheckBookingDao = new LabCheckBookingDAOImpl();
        labCheckBookingDao.insertBooking(labCheckBooking);

        LabCheckBookingUtil labCheckBookingUtil = new LabCheckBookingUtilImpl();
        List<LabCheckBooking> labCheckBookingList = labCheckBookingUtil.fetchBookings();
        assertTrue(labCheckBookingList.size() > 0);
    }
}