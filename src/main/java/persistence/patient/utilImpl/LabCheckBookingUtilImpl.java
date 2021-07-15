package persistence.patient.utilImpl;

import persistence.patient.dao.LabCheckBookingDao;
import persistence.patient.daoImpl.LabCheckBookingDaoImpl;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.model.Patient;
import persistence.patient.util.LabCheckBookingUtil;
import presentation.admin.PharmaInvoiceOutput;
import presentation.common.CommonConstants;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class LabCheckBookingUtilImpl implements LabCheckBookingUtil {
    /*
    take user input, set LabCheckBooking obj, call insertBooking(obj)
     */
    PrintToConsole consoleObj = PrintToConsole.getInstance();

    @Override
    public void makeBooking(int healthCheckId, Date bookingDate) {
        LabCheckBooking labCheckBooking = new LabCheckBooking();

        labCheckBooking.setPatient_id(Patient.getPatient().getPatientId());
        labCheckBooking.setHealthcheck_id(healthCheckId);
        labCheckBooking.setBooked_for_date(bookingDate);

        LabCheckBookingDao labCheckBookingDao = new LabCheckBookingDaoImpl();
        labCheckBookingDao.insertBooking(labCheckBooking);
    }

    @Override
    public List<LabCheckBooking> fetchBookings() {
        return null;
    }
}
