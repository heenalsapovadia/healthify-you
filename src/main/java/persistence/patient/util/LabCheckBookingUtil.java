package persistence.patient.util;

import java.sql.Date;

public interface LabCheckBookingUtil {
    void makeBooking(int healthCheckId, Date bookingDate, int billingId);
}
