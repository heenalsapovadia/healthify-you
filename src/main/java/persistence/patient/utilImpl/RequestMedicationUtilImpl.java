package persistence.patient.utilImpl;

import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.model.Prescription;
import persistence.patient.daoImpl.DoctorAppointmentBookingByNameDAOImpl;

import java.sql.SQLException;

public class RequestMedicationUtilImpl {

    class MedicationsToUpdate {
        String medicationName;
        int medicationLeft;
        public MedicationsToUpdate(String medicationName, int medicationLeft) {
            this.medicationName = medicationName;
            this.medicationLeft = medicationLeft;
        }
    }

}
