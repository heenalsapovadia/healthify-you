package persistence.patient.utilImpl;

import org.junit.Test;
import persistence.patient.dao.LabCheckBookingDao;
import persistence.patient.daoImpl.LabCheckBookingDaoImpl;
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
        LabCheckBookingUtil labCheckBookingUtil = new LabCheckBookingUtilImpl();
        labCheckBookingUtil.makeBooking(healthCheckId, bookingdate);

        LabCheckBookingDao labCheckBookingDao = new LabCheckBookingDaoImpl();
        assertNotNull(labCheckBookingDao.getBookingByDate(bookingdate));
    }

    @Test
    public void fetchBookings() {
        LabCheckBooking labCheckBooking = new LabCheckBooking();
        labCheckBooking.setPatient_id(1);
        labCheckBooking.setHealthcheck_id(2);
        labCheckBooking.setBooked_for_date(Date.valueOf("2021-05-21"));

        LabCheckBookingDao labCheckBookingDao = new LabCheckBookingDaoImpl();
        labCheckBookingDao.insertBooking(labCheckBooking);

        LabCheckBookingUtil labCheckBookingUtil = new LabCheckBookingUtilImpl();
        List<LabCheckBooking> labCheckBookingList = labCheckBookingUtil.fetchBookings();
        assertTrue(labCheckBookingList.size() > 0);
    }
}