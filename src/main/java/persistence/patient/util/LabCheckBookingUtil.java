package persistence.patient.util;

import persistence.patient.model.LabCheckBooking;

import java.util.List;

public interface LabCheckBookingUtil {
    void makeBooking();

    List<LabCheckBooking> fetchBookings();
}
