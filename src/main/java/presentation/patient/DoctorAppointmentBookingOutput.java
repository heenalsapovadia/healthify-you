package presentation.patient;

import persistence.doctor.dao.AppointmentDAO;
import persistence.doctor.dao.DoctorAvailabilityDAO;
import persistence.doctor.dao.DoctorDAO;
import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.daoImpl.DoctorAvailabilityDAOImpl;
import persistence.doctor.daoImpl.DoctorDAOImpl;
import persistence.doctor.model.Appointment;
import presentation.common.CommonConstants;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * <pre>
 * Loads Doctor Appointment dashboard in the application.
 * </pre>
 *
 * @author Heenal Sapovadia
 *
 */
public class DoctorAppointmentBookingOutput {

    private PrintToConsole consoleObj;

    public DoctorAppointmentBookingOutput() {
        consoleObj = PrintToConsole.getInstance();
    }

    public void dashboard() throws SQLException {
        consoleObj.printHeader(ScreenTitles.DOCTOR_APPOINTMENT);

        List<String> options = Arrays.asList(ScreenTitles.BOOK_APPOINTMENT, ScreenTitles.RESCHEDULE_APPOINTMENT);
        int option = consoleObj.printSelection(options);

        switch (option){
            case 1:
                DoctorAppointmentBookingDashboard doctorAppointmentBookingDashboard = new DoctorAppointmentBookingDashboard();
                doctorAppointmentBookingDashboard.display();
                break;
            case 2:
                rescheduleAppointment();
                break;
        }
    }

    public void rescheduleAppointment(){
        consoleObj.printHeader(ScreenTitles.RESCHEDULE_APPOINTMENT);

        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
        List<Appointment> appointmentList = appointmentDAO.fetchAppointmentsForPatient();
        Map<Integer, String> doctorMap = doctorIdNameMap(appointmentList);
        Map<Integer, Appointment> appointmentMap = displayUpcomingAppointments(appointmentList, doctorMap);
        consoleObj.printSingleNewLine();

        int appointmentId = validateAppointmentIdToReschedule(appointmentMap);
        consoleObj.printSingleNewLine();

        System.out.println("------------------------ " + ScreenFields.APPOINTMENT_TO_RESCHEDULE + " ------------------------ ");
        Appointment appointmentToReschedule = appointmentMap.get(appointmentId);
        System.out.println(ScreenFields.APPOINTMENT_ID + " : "+appointmentId);
        System.out.println(ScreenFields.DOCTOR_NAME + " : "+ doctorMap.get(appointmentToReschedule.getDoctorId()));
        Date currentAppointmentDate = appointmentToReschedule.getRescheduledDate()==null ? appointmentToReschedule.getBookedForDate() : appointmentToReschedule.getRescheduledDate();
        System.out.println(ScreenFields.APPOINTMENT_DATE + " : "+currentAppointmentDate);

        consoleObj.printSingleNewLine();

        Scanner scanner = new Scanner(System.in);
        List<String> daysAvailable = doctorAvailability(appointmentToReschedule.getDoctorId());
        List<String> datesOptions = datesGenerator(daysAvailable, 0);
        System.out.println(ScreenFields.DOCTOR_AVAILABILITY);
        for(int i=0; i<datesOptions.size(); i++){
            String availableDate = datesOptions.get(i);
            if(availableDate.equals(currentAppointmentDate.toString()))
                datesOptions.remove(availableDate);
            else
                System.out.println((i+1) + ". " + availableDate);
        }
        consoleObj.printSingleNewLine();
        System.out.println(ScreenFields.DATES_NEXT_WEEK);
        System.out.println("1. Yes\n2. No");
        if(scanner.hasNextInt()) {
            int option = scanner.nextInt();
            if (option == 1) {
                List<String> datesForNextWeek = datesGenerator(daysAvailable, 1);
                datesOptions.addAll(datesForNextWeek);
            }
        }
        int dateOption = consoleObj.printSelection(datesOptions);
        Date rescheduleDate = Date.valueOf(datesOptions.get(dateOption-1));
        System.out.println("Rescheduling to Date"+CommonConstants.COMMON_TEXT_SEPARATOR+rescheduleDate);
        consoleObj.printSingleNewLine();

        appointmentToReschedule.setRescheduledDate(rescheduleDate);

        appointmentDAO.updateAppointment(appointmentToReschedule);
    }

    public int validateAppointmentIdToReschedule(Map<Integer, Appointment> appointmentMap){
        Scanner scanner = new Scanner(System.in);
        int appointmentId;
        while(true) {
            System.out.print(ScreenFields.APPOINTMENT_ID_TO_RESCHEDULE);
            if (scanner.hasNextInt()) {
                appointmentId = scanner.nextInt();
                if(appointmentMap.containsKey(appointmentId))
                    break;
                else
                    System.out.println(CommonErrors.INVALID_APPOINTMENT_ID);
            }
            else
                System.out.println(CommonErrors.INVALID_APPOINTMENT_ID);
        }
        return appointmentId;
    }

    public Map<Integer, Appointment> displayUpcomingAppointments(List<Appointment> appointmentList, Map<Integer, String> doctorMap){
        Map<Integer, Appointment> appointmentMap = new HashMap<>();
        System.out.println(ScreenFields.UPCOMING_APPOINTMENTS);
        consoleObj.printSingleNewLine();
        for(Appointment appointment : appointmentList){
            Date currentAppointmentDate = appointment.getRescheduledDate()==null ? appointment.getBookedForDate() : appointment.getRescheduledDate();
            LocalDate today = LocalDate.now();
            LocalDate appointmentDateLocal = appointment.getBookedForDate().toLocalDate();
            int differencePeriod = Period.between(today, appointmentDateLocal).getDays();
            if(differencePeriod > 0) {
                System.out.println(ScreenFields.APPOINTMENT_ID + " : " + appointment.getAppointmentId()
                        + ", " + ScreenFields.DOCTOR_NAME + " : " + doctorMap.get(appointment.getDoctorId())
                        + ", "+ ScreenFields.APPOINTMENT_DATE +" : " + currentAppointmentDate
                        + ", " + ScreenFields.BILL_ID + " : " + appointment.getBillingId());
                appointmentMap.put(appointment.getAppointmentId(), appointment);
            }
        }
        return appointmentMap;
    }

    public Map<Integer, String> doctorIdNameMap(List<Appointment> appointmentList){
        DoctorDAO doctorDAO = new DoctorDAOImpl();
        Map<Integer, String> doctorIdNameMap = new HashMap<>();
        for(Appointment appointment : appointmentList) {
            String doctorName = doctorDAO.getDoctorNameById(appointment.getDoctorId());
            doctorIdNameMap.put(appointment.getDoctorId(), doctorName);
        }
        return doctorIdNameMap;
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
            daysOptions.add(date.toString());
        }
        return daysOptions;
    }

}
