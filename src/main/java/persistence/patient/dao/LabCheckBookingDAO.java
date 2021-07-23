package persistence.patient.dao;

import persistence.patient.model.LabCheckBooking;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface LabCheckBookingDAO {
    void insertBooking(LabCheckBooking booking);

    List<LabCheckBooking> getAllBookings();

    List<LabCheckBooking> getBookingByDate(Date date);
    
    List<LabCheckBooking> getBookingByPatientId();
    
    Map<Integer, String> getHealthChecks(List<Integer> healthCheckIdList);
}
