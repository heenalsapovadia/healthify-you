package persistence.patient.daoImpl;

import org.junit.Test;
import persistence.patient.dao.LabCheckBookingDao;
import persistence.patient.model.LabCheckBooking;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class LabCheckBookingDaoImplTest {

    @Test
    public void insertBooking() {
        Date today = new Date(System.currentTimeMillis());

        // set patient id
        //dummy
        int patient_id = 12;
        LabCheckBooking labCheckBooking = new LabCheckBooking();
        // set the booking

        LabCheckBookingDao labCheckBookingDao = new LabCheckBookingDaoImpl();
        labCheckBookingDao.insertBooking(labCheckBooking);
        assertEquals(labCheckBookingDao.getBookingByDate(today).get(0), labCheckBooking);
    }

    @Test
    public void getAllBookings() {
        // set patient id
        //dummy
        int patient_id = 12;
        LabCheckBookingDao labCheckBookingDao = new LabCheckBookingDaoImpl();
        List<LabCheckBooking> labCheckBookingList = labCheckBookingDao.getAllBookings();
        assertTrue(labCheckBookingList.size()>0); // need to be modified for zero bookings
    }
}