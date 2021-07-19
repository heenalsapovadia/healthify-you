package persistence.patient.daoImpl;

import org.junit.Test;
import persistence.patient.dao.LabCheckBookingDAO;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.util.LabCheckBookingUtil;
import persistence.patient.utilImpl.LabCheckBookingUtilImpl;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class LabCheckBookingDAOImplTest {

    @Test
    public void insertBooking() {
        Date date = Date.valueOf("2021-05-21");

        LabCheckBooking labCheckBooking = new LabCheckBooking();
        labCheckBooking.setPatient_id(1);
        labCheckBooking.setHealthcheck_id(2);
        labCheckBooking.setBooked_for_date(date);

        LabCheckBookingDAO labCheckBookingDao = new LabCheckBookingDAOImpl();
        labCheckBookingDao.insertBooking(labCheckBooking);
        assertNotNull(labCheckBookingDao.getBookingByDate(date));
    }

    @Test
    public void getAllBookings() {
        LabCheckBooking labCheckBooking = new LabCheckBooking();
        labCheckBooking.setPatient_id(1);
        labCheckBooking.setHealthcheck_id(2);
        labCheckBooking.setBooked_for_date(Date.valueOf("2021-05-21"));

        LabCheckBookingDAO labCheckBookingDao = new LabCheckBookingDAOImpl();
        labCheckBookingDao.insertBooking(labCheckBooking);

        LabCheckBookingUtil labCheckBookingUtil = new LabCheckBookingUtilImpl();
        List<LabCheckBooking> labCheckBookingList = labCheckBookingUtil.fetchBookings();
        assertTrue(labCheckBookingList.size() > 0);
    }
}