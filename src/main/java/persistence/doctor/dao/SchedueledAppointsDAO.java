package persistence.doctor.dao;

// author  - saloni raythatha
// Interface for all the database implementations

import persistence.doctor.model.Appointment;
import persistence.doctor.model.PatientDetailsModel;

import java.util.Date;
import java.util.List;

public interface SchedueledAppointsDAO {

    // method to get all appointment details from doctor appointment table
     List<Appointment> getAppointmentsDetails(Date appoitmentDate);

     // patient details from patient table
    public PatientDetailsModel getPatient(int patientId);
}
