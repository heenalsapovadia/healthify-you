package persistence.patient.dao;

import persistence.patient.model.LabCheckBooking;

import java.util.List;

public interface LabCheckBookingDao {
    void insertBooking(LabCheckBooking booking);

    List<LabCheckBooking> getAllBookings();
}
