package presentation.doctor;

import persistence.doctor.daoImpl.ScheduledAppointmentsDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.PatientDetailsModel;
import presentation.common.CommonConstants;
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
        System.out.println("Enter the date of appointment(yyyy-mm-dd): ");


        // validating date for user input
        Date current_appointment_date;
        while(true) {
            try {
                current_appointment_date = Date.valueOf(sc.next());
                if(current_appointment_date != null) {
                    break;
                } else {
                    System.out.println("Wrong date");
                }
            }
            catch(IllegalArgumentException e) {
                System.out.println("Invalid Date Format");
            }
        }
        List<Appointment> appointments = scheduledAppointmentsDAOimpl.getAppointmentsDetails(current_appointment_date);
        if(!appointments.isEmpty()) {
	        for (Appointment appointment: appointments) {
	            //System.out.print("Appointment Id - " + appointment.getAppointment_id() + " ");
	            PatientDetailsModel model = scheduledAppointmentsDAOimpl.getPatient(appointment.getPatientId());
	            System.out.print("Appointment Id" + "    " + "Patient Name" +"    "+ "Patient Age ");
	            System.out.println();
	            System.out.print(appointment.getAppointmentId() +"               " + model.name + "               " + model.age );
	            System.out.println();
	        }
        }
        System.out.println("No scheduled appointments on the entered date.");
        int selection;
        System.out.print("Enter 1 to Exit:");
        selection = sc.nextInt();
        if(selection == 1) {
        	return;
        }
    }
}
