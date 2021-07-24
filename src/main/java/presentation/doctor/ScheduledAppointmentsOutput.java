package presentation.doctor;

import persistence.doctor.daoImpl.ScheduledaAppointmentsDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.PatientDetailsModel;
import presentation.common.CommonConstants;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ScheduledAppointmentsOutput {
    public String scheduledAppointmentsDetails() {

        ScheduledaAppointmentsDAOImpl scheduledaAppointmentsDAOimpl = new ScheduledaAppointmentsDAOImpl();
        Scanner sc = new Scanner(System.in);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.HEADING_CHAR);

        System.out.println();
        System.out.println(CommonConstants.TITLE_SPACE + CommonConstants.TITLE_SPACE + ScreenTitles.scheduledAppointments + CommonConstants.TITLE_SPACE);
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
        List<Appointment> appointments = scheduledaAppointmentsDAOimpl.getAppointmentsDetails(current_appointment_date);
        for (Appointment appointment: appointments) {
            //System.out.print("Appointment Id - " + appointment.getAppointment_id() + " ");
            PatientDetailsModel model = scheduledaAppointmentsDAOimpl.getPatient(appointment.getPatient_id());
            System.out.print("Appointment Id" + "    " + "Patient Name" +"    "+ "Patient Age ");
            System.out.println();
            System.out.print(appointment.getAppointment_id() +"               " + model.name + "               " + model.age );
            System.out.println();
        }
        System.out.println("No scheduled appointments on the entered date.");
        int selection;
        System.out.print("Enter 1 to Exit:");
        selection = sc.nextInt();
        if(selection == 1)
        {
            System.out.println(ScreenFields.LOGOUT_MESSAGE);
            System.out.println(ScreenFields.APPLICATION_TERMINATION_MESSAGE);
            System.exit(0);
        }
        else{
            System.out.println("Wrong input");
            System.out.println(ScreenFields.LOGOUT_MESSAGE);
            System.out.println(ScreenFields.APPLICATION_TERMINATION_MESSAGE);
            System.exit(0);
        }
        return "";
    }
}
