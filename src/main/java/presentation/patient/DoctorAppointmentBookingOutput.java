package presentation.patient;

import com.sun.tools.jdeprscan.scan.Scan;
import persistence.doctor.dao.AppointmentDAO;
import persistence.doctor.dao.DoctorAvailabilityDAO;
import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.daoImpl.DoctorAvailabilityDAOImpl;
import persistence.doctor.model.Appointment;
import presentation.common.PrintToConsole;
import presentation.common.ScreenTitles;

import java.util.*;

public class DoctorAppointmentBookingOutput {
    PrintToConsole consoleObj = PrintToConsole.getInstance();

    public void dashboard() {
        consoleObj.printHeader(ScreenTitles.doctorAppointment);

        List<String> options = Arrays.asList(ScreenTitles.bookAppointment, ScreenTitles.rescheduleAppointment);
        int option = consoleObj.printSelection(options);

        switch (option){
            case 1:
                //book doc appt code
                break;
            case 2:
                rescheduleAppointment();
                break;
        }
    }

    public void rescheduleAppointment(){
        consoleObj.printHeader(ScreenTitles.rescheduleAppointment);

        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
        List<Appointment> appointmentList = appointmentDAO.fetchAppointmentsForPatient();
        Map<Integer, Appointment> appointmentMap = new HashMap<>();

        System.out.println("Your upcoming doctor appointments are : ");
        consoleObj.printSingleNewLine();
        for(Appointment appointment : appointmentList){
            System.out.println("AppointmentId : " + appointment.getAppointment_id()
                    + "DoctorId : " + appointment.getDoctor_id()
                    + "AppointmentDate : " + appointment.getBooked_for_date()
                    + "BillingId : " + appointment.getBilling_id());
            appointmentMap.put(appointment.getAppointment_id(), appointment);
        }
        consoleObj.printSingleNewLine();

        int appointmentId = validateAppointmentIdToReschedule(appointmentMap);
        consoleObj.printSingleNewLine();

        System.out.println("Appointment to be rescheduled : ");
        System.out.println("Appointment Id : "+appointmentId);
        System.out.println("Doctor Id : "+appointmentMap.get(appointmentId).getDoctor_id());
        System.out.println("Appointment Date : "+appointmentMap.get(appointmentId).getBooked_for_date());
        consoleObj.printSingleNewLine();

        Scanner sc = new Scanner(System.in);
        doctorAvailability(appointmentMap.get(appointmentId).getDoctor_id());
        consoleObj.printSingleNewLine();

    }

    public int validateAppointmentIdToReschedule(Map<Integer, Appointment> appointmentMap){
        Scanner sc = new Scanner(System.in);
        int appointmentId;
        while(true) {
            System.out.print("Enter appointment Id to reschedule : ");
            if (sc.hasNextInt()) {
                appointmentId = sc.nextInt();
                if(appointmentMap.containsKey(appointmentId))
                    break;
                else
                    System.out.println("Invalid Appointment Id.. Please try again");
            }
            else
                System.out.println("Invalid Appointment Id.. Please try again");
        }
        return appointmentId;
    }

    public void doctorAvailability(int doctorId){
        System.out.println("Doctor is available on the following days of week : ");
        DoctorAvailabilityDAO doctorAvailabilityDAO = new DoctorAvailabilityDAOImpl();
        List<String> daysAvailable = doctorAvailabilityDAO.getAvailabilityByDoctor(doctorId);
        for(String day : daysAvailable)
            System.out.println(day);
    }
}
