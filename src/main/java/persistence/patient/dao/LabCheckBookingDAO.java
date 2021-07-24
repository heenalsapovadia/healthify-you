package persistence.patient.dao;

import persistence.patient.model.LabCheckBooking;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface LabCheckBookingDAO {
    void insertBooking(LabCheckBooking booking);

    List<LabCheckBooking> getAllBookings();

    List<LabCheckBooking> getBookingByDate(Date date);
    
    /**
     * <pre>
     * Returns map of health plan names corresponding to their id.
     * </pre>
     * @param healthCheckIdList
     * @return map
     */
    Map<Integer, String> getHealthChecks(List<Integer> healthCheckIdList);
}
