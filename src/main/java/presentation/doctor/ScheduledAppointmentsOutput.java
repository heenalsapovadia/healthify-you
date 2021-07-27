package presentation.doctor;

import persistence.doctor.daoImpl.ScheduledAppointmentsDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.PatientDetailsModel;
import persistence.doctor.utilImpl.ScheduledAppointmentsUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ScheduledAppointmentsOutput {
    public void scheduledAppointmentsDetails() {

        ScheduledAppointmentsDAOImpl scheduledAppointmentsDAOimpl = new ScheduledAppointmentsDAOImpl();
        Scanner sc = new Scanner(System.in);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.HEADING_CHAR);

        System.out.println();
        System.out.println(CommonConstants.TITLE_SPACE + ScreenTitles.scheduledAppointments + CommonConstants.TITLE_SPACE);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.HEADING_CHAR);
        System.out.println();
        System.out.println(ScreenFields.CURRENT_APPOINTMENT_DATE);


        // validating date for user input
        Date current_appointment_date;
        while(true) {
            try {
                current_appointment_date = Date.valueOf(sc.next());
                if(current_appointment_date != null) {
                    break;
                } else {
                    System.out.println(ScreenFields.WRONG_DATE);
                }
            }
            catch(IllegalArgumentException e) {
                System.out.println(ScreenFields.INVALID_DATE_FORMAT_FOR_SCHEDULEDAPPOINTMENTS);
            }
        }

        List<Appointment> appointments = scheduledaAppointmentsDAOimpl.getAppointmentsDetails(current_appointment_date);
        List<Appointment> appointments = scheduledAppointmentsDAOimpl.getAppointmentsDetails(current_appointment_date);
        if(!appointments.isEmpty()) {
	        for (Appointment appointment: appointments) {
                PatientDetailsModel model = scheduledaAppointmentsDAOimpl.getPatient(appointment.getPatientId());
                System.out.print(ScreenFields.APPOINTMENT_ID + CommonConstants.TITLE_SPACE + ScreenFields.PATIENT_NAME_IN_APPOINTMENT + CommonConstants.TITLE_SPACE + ScreenFields.PATIENT_AGE);
                System.out.println();
                System.out.print(appointment.getAppointmentId() + CommonConstants.TITLE_SPACE + model.name + CommonConstants.TITLE_SPACE + model.age);
                System.out.println();
            }
        }
        else {
            System.out.println(ScreenFields.NO_SCHEDULED_APPOINTMENTS);
            return;
        }
        int selection;
        System.out.print("Enter 1 to Exit:");
        selection = sc.nextInt();
        if(selection == 1) {
        	return;
        }
    }
}
