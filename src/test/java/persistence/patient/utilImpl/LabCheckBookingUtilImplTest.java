package persistence.patient.utilImpl;

import org.junit.Test;
import persistence.patient.dao.LabCheckBookingDao;
import persistence.patient.daoImpl.LabCheckBookingDaoImpl;
import persistence.patient.util.LabCheckBookingUtil;

import java.sql.Date;

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
    }
}