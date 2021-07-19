package persistence.patient.util;

import persistence.patient.model.LabCheckBooking;
import java.sql.Date;
import java.util.List;

public interface LabCheckBookingUtil {
    void makeBooking(int healthCheckId, Date bookingDate);

    List<LabCheckBooking> fetchBookings();
}
