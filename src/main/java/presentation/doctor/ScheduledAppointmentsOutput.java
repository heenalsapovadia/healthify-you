package presentation.doctor;

import persistence.doctor.dao.ScheduledAppointmentsDAO;
import persistence.doctor.daoImpl.ScheduledAppointmentsDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.ScheduledAppointmentsModel;
import presentation.common.CommonConstants;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
/**
 * <pre>
 *  Scheduled Appointments output class
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class ScheduledAppointmentsOutput {

    // below method will fetch appointments from doctor appointment table
    public void scheduledAppointmentsDetails() {
        Scanner sc = new Scanner(System.in);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.HEADING_CHAR);
        System.out.println();
        System.out.println(CommonConstants.TITLE_SPACE + ScreenTitles.scheduledAppointments + CommonConstants.TITLE_SPACE);

        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.HEADING_CHAR);
        System.out.println();
        System.out.println(ScreenFields.CURRENT_APPOINTMENT_DATE);

        // validating date that user inputs
        Date current_appointment_date;
        while(true) {
            try {
                current_appointment_date = Date.valueOf(sc.next());
                if(current_appointment_date != null) {
                    break;
                } else {
                    System.out.println(ScreenFields.WRONG_DATE_OF_APPOINTMENT);
                }
            }
            catch(IllegalArgumentException e) {
                System.out.println(ScreenFields.INVALID_DATE_FORMAT_FOR_SCHEDULEDAPPOINTMENTS);
            }
        }

        // if not appointment found returns and if found loops to given date and goes to processScheduledAppointment method
        ScheduledAppointmentsDAO scheduledaAppointmentsDAOimpl = new ScheduledAppointmentsDAOImpl();
        List<Appointment> appointments = scheduledaAppointmentsDAOimpl.getAppointmentsDetails(current_appointment_date);
        if(!appointments.isEmpty()) {
            for (Appointment appointment: appointments) {
                processScheduledAppointment(appointment);
            }
        }
        else {
            System.out.println(ScreenFields.NO_SCHEDULED_APPOINTMENTS);
            return;
        }
        int selection;
        selection = sc.nextInt();
        if(selection == 1) {
            return;
        }
    }

    // check appointments on the mentioned date and display result
    void processScheduledAppointment(Appointment appointment) {
        ScheduledAppointmentsDAO scheduledaAppointmentsDAOimpl = new ScheduledAppointmentsDAOImpl();
        ScheduledAppointmentsModel model = scheduledaAppointmentsDAOimpl.getPatient(appointment.getPatientId());
        System.out.print(ScreenFields.APPOINTMENT_ID + CommonConstants.TITLE_SPACE + ScreenFields.PATIENT_NAME_IN_APPOINTMENT + CommonConstants.TITLE_SPACE + ScreenFields.PATIENT_AGE);
        System.out.println();
        System.out.print(appointment.getAppointmentId() + CommonConstants.TITLE_SPACE + model.name + CommonConstants.TITLE_SPACE + model.age);
        System.out.println();
    }
}
