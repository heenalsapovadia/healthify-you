package presentation.patient;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.patient.model.*;
import persistence.patient.util.*;
import persistence.patient.utilImpl.*;
import presentation.common.*;
import java.sql.Date;
import java.util.*;

public class LabTestBookingOutput {

    PrintToConsole consoleObj = PrintToConsole.getInstance();

    public void dashboard(){
        /*
        Dashboard title
         */
        consoleObj.printHeader(ScreenTitles.bookATest);

        /*
        Dashboard main options list
         */
        List<String> selectionOptions = Arrays.asList(ScreenTitles.availablePlans, ScreenTitles.labTestRecommendation,
                ScreenTitles.makeBooking, ScreenTitles.previousBookings, ScreenFields.exit);

        int option = consoleObj.printSelection(selectionOptions);

        switch (option){
            case 1:
                viewPlans();
                break;
            case 2:
                getRecommendations();
                break;
            case 3:
                makeBooking();
                break;
            case 4:
                viewBookings();
                break;
            case 5:
                break;
        }

    }

    public void viewPlans(){
        Scanner sc = new Scanner(System.in);
        /*
        View Plans title
         */
        consoleObj.printHeader(ScreenTitles.availablePlans);

        LabCheckUtil labCheckUtil = new LabCheckUtilImpl();
        List<LabCheck> labCheckList = labCheckUtil.fetchLabCheckPlans();
        for(LabCheck labCheck : labCheckList){
            System.out.println(""+labCheck.getCheckup_id()+CommonConstants.commonTextSeparator+labCheck.getCheckup_name());
        }
        List<String> selectionOptions = Arrays.asList(ScreenFields.viewDetails, ScreenFields.backToBooking);
        int option = consoleObj.printSelection(selectionOptions);
        switch (option){
            case 1:
                labCheckUtil.fetchDetails();
                break;
            case 2:
                return;
        }
    }

    public void makeBooking(){
        consoleObj.printHeader(ScreenTitles.makeBooking);

        int healthCheckId;
        Date bookingdate;

        LabCheckUtil labCheckUtil = new LabCheckUtilImpl();
        Map<Integer, LabCheck> labCheckMap = labCheckUtil.fetchLabCheckMap();

        Scanner sc = new Scanner(System.in);

        healthCheckId = inputHealthCheckId(sc);
        bookingdate = inputBookingDate(sc);
        //double healthCheckCharges = labCheckMap.get("charges").getCharges();
        double healthCheckCharges = 100;


        List<String> options = Arrays.asList("Continue For Payment", "Exit");
        int option = consoleObj.printSelection(options);
        int billingId = 0;
        switch (option) {
            case 1:
                // Call Payment Interface screen code
                PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();
                billingId = paymentInterfaceOutput.processPayment(Patient.getPatient(), PaymentBillingCategory.L, healthCheckCharges);
                break;
            case 2:
                return;
        }

        LabCheckBookingUtil labCheckBookingUtil = new LabCheckBookingUtilImpl();
        labCheckBookingUtil.makeBooking(healthCheckId, bookingdate, billingId);
    }

    public void viewBookings(){
        consoleObj.printHeader(ScreenTitles.previousBookings);

        LabCheckBookingUtil labCheckBookingUtil = new LabCheckBookingUtilImpl();
        List<LabCheckBooking> labCheckBookingList = labCheckBookingUtil.fetchBookings();
        System.out.println("Appointment ID | HealthCheck ID | Date");
        for(LabCheckBooking labCheckBooking : labCheckBookingList){
            System.out.println(labCheckBooking.getAppointment_id()+CommonConstants.verticleBar+labCheckBooking.getHealthcheck_id()+CommonConstants.verticleBar+labCheckBooking.getBooked_for_date());
        }
    }

    public void getRecommendations(){
        consoleObj.printHeader(ScreenTitles.labTestRecommendation);
        LabCheckRecommendationUtil labCheckRecommendationUtil = new LabCheckRecommendationUtilImpl();

        List<LabCheck> ageBasedRecommendationList = labCheckRecommendationUtil.ageBasedRecommendation();
        List<LabCheck> genderBasedRecommendationList = labCheckRecommendationUtil.genderBasedRecommendation();
        List<LabCheck> historyBasedRecommendationList = labCheckRecommendationUtil.historyBasedRecommendation();

        Set<LabCheck> labCheckSet = new HashSet<>();
        for(LabCheck labCheck : ageBasedRecommendationList)
            labCheckSet.add(labCheck);
        for(LabCheck labCheck : genderBasedRecommendationList)
            labCheckSet.add(labCheck);
        for(LabCheck labCheck : historyBasedRecommendationList)
            labCheckSet.add(labCheck);
        System.out.println(ScreenFields.labCheckRecommendation+CommonConstants.commonTextSeparator);

        for(LabCheck labCheck : labCheckSet)
            System.out.println(labCheck.getCheckup_id()+CommonConstants.commonTextSeparator+labCheck.getCheckup_name());
    }

    private int inputHealthCheckId(Scanner sc){
        int healthCheckId;
        System.out.print(ScreenFields.checkId+ CommonConstants.commonTextSeparator);

        while(true) {
            if (sc.hasNextInt()) {
                healthCheckId = sc.nextInt();
                if (healthCheckId < 1 || healthCheckId > 10)
                    consoleObj.printError(CommonErrors.invalidCheckUpId);
                else
                    return healthCheckId;
            } else
                consoleObj.printError(CommonErrors.invalidSelection);
        }
    }

    private Date inputBookingDate(Scanner sc){
        Date bookingdate;
        System.out.print(ScreenFields.dateInput+CommonConstants.commonTextSeparator);

        while(true) {
            try {
                bookingdate = Date.valueOf(sc.next());
                if(bookingdate.compareTo(new Date(System.currentTimeMillis())) < 0) {
                    consoleObj.printError(CommonErrors.smallerDate);
                }
                else
                    return bookingdate;
            }
            catch(IllegalArgumentException e) {
                consoleObj.printError(CommonErrors.invalidDateFormat);
            }
        }
    }
}
