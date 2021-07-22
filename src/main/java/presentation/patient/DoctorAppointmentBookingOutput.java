package presentation.patient;

import persistence.doctor.dao.AppointmentDAO;
import persistence.doctor.dao.DoctorAvailabilityDAO;
import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.daoImpl.DoctorAvailabilityDAOImpl;
import persistence.doctor.model.Appointment;
import presentation.common.CommonConstants;
import presentation.common.PrintToConsole;
import presentation.common.ScreenTitles;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
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
        Map<Integer, Appointment> appointmentMap = displayUpcomingAppointments(appointmentList);
        consoleObj.printSingleNewLine();

        int appointmentId = validateAppointmentIdToReschedule(appointmentMap);
        consoleObj.printSingleNewLine();

        System.out.println("Appointment to be rescheduled ------------------------ ");
        System.out.println("Appointment Id : "+appointmentId);
        System.out.println("Doctor Id : "+appointmentMap.get(appointmentId).getDoctor_id());
        System.out.println("Appointment Date : "+appointmentMap.get(appointmentId).getBooked_for_date());
        Appointment appointmentToReschedule = appointmentMap.get(appointmentId);
        consoleObj.printSingleNewLine();

        Scanner sc = new Scanner(System.in);
        List<String> daysAvailable = doctorAvailability(appointmentToReschedule.getDoctor_id());
        List<String> datesOptions = datesGenerator(daysAvailable, 0);
        System.out.println("Doctor is available on the following dates : ");
        for(String availableDate : datesOptions){
            if(availableDate.equals(appointmentToReschedule.getBooked_for_date().toString()))
                datesOptions.remove(availableDate);
            else
                System.out.println(availableDate);
        }
        System.out.println("Do you want dates for the week after ?");
        System.out.println("1. Yes\n2. No");
        if(sc.hasNextInt()) {
            int option = sc.nextInt();
            if (option == 1) {
                List<String> datesForNextWeek = datesGenerator(daysAvailable, 1);
                datesOptions.addAll(datesForNextWeek);
            }
        }
        int dateOption = consoleObj.printSelection(datesOptions);
        Date rescheduleDate = Date.valueOf(datesOptions.get(dateOption));
        System.out.println("Rescheduling to Date"+CommonConstants.commonTextSeparator+rescheduleDate);
        consoleObj.printSingleNewLine();

        appointmentToReschedule.setRescheduled_date(rescheduleDate);

        appointmentDAO.updateAppointment(appointmentToReschedule);
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

    public Map<Integer, Appointment> displayUpcomingAppointments(List<Appointment> appointmentList){
        Map<Integer, Appointment> appointmentMap = new HashMap<>();
        System.out.println("Your upcoming doctor appointments are : ");
        consoleObj.printSingleNewLine();
        for(Appointment appointment : appointmentList){
            LocalDate today = LocalDate.now();
            LocalDate appointmentDateLocal = appointment.getBooked_for_date().toLocalDate();
            int differencePeriod = Period.between(today, appointmentDateLocal).getDays();
            if(differencePeriod > 0) {
                System.out.println("AppointmentId : " + appointment.getAppointment_id()
                        + "DoctorId : " + appointment.getDoctor_id()
                        + "AppointmentDate : " + appointment.getBooked_for_date()
                        + "BillingId : " + appointment.getBilling_id());
                appointmentMap.put(appointment.getAppointment_id(), appointment);
            }
        }
        return appointmentMap;
    }

    public List<String> doctorAvailability(int doctorId){
        DoctorAvailabilityDAO doctorAvailabilityDAO = new DoctorAvailabilityDAOImpl();
        List<String> daysAvailable = doctorAvailabilityDAO.getAvailabilityByDoctor(doctorId);

        return daysAvailable;
    }

    public List<String> datesGenerator(List<String> daysAvailable, int weekNumber){
        Map<String, Integer> weekDaysMap = new HashMap();
        weekDaysMap.put("Mon", 1);
        weekDaysMap.put("Tue", 2);
        weekDaysMap.put("Wed", 3);
        weekDaysMap.put("Thu", 4);
        weekDaysMap.put("Fri", 5);
        weekDaysMap.put("Sat", 6);
        weekDaysMap.put("Sun", 7);

        LocalDate today = LocalDate.now();
        int dayToday = today.getDayOfWeek().getValue();
        List<String> daysOptions = new ArrayList<>();
        for(String day : daysAvailable) {
            int dayOfWeek = weekDaysMap.get(day);
            int daysToAdd = (7 - (dayToday - dayOfWeek)) % 7 + 7*weekNumber;
            LocalDate date = today.plusDays(daysToAdd);
            System.out.println(day + CommonConstants.commonTextSeparator + date);
            daysOptions.add(date.toString());
        }
        return daysOptions;
    }
}
