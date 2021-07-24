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
        Dashboard main options list
         */
        List<String> selectionOptions = Arrays.asList(ScreenTitles.availablePlans, ScreenTitles.labTestRecommendation,
                ScreenTitles.makeBooking, ScreenTitles.previousBookings, ScreenFields.exit);
        while(true) {
            consoleObj.printHeader(ScreenTitles.bookATest);
            int option = consoleObj.printSelection(selectionOptions);

            switch (option) {
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
                    return;
            }
            consoleObj.printSingleNewLine();
        }

    }

    public void viewPlans(){
        /*
        View Plans title
         */
        consoleObj.printHeader(ScreenTitles.availablePlans);

        LabCheckUtil labCheckUtil = new LabCheckUtilImpl();
        List<LabCheck> labCheckList = labCheckUtil.fetchLabCheckPlans();
        for(LabCheck labCheck : labCheckList){
            System.out.println(""+labCheck.getCheckupId()+CommonConstants.commonTextSeparator+labCheck.getCheckupName());
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

        Scanner scanner = new Scanner(System.in);

        healthCheckId = inputHealthCheckId(scanner);
        bookingdate = inputBookingDate(scanner);
        double healthCheckCharges = labCheckMap.get(healthCheckId).getCharges();

        List<String> options = Arrays.asList("Continue For Payment", ScreenFields.exit);
        int option = consoleObj.printSelection(options);


        int billingId = 0;
        switch (option) {
            case 1:
                // Call Payment Interface screen code
                PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();
                billingId = paymentInterfaceOutput.processPayment(PaymentBillingCategory.L, healthCheckCharges,"");
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
            System.out.println(labCheckBooking.getAppointmentId()+CommonConstants.verticleBar+labCheckBooking.getHealthcheckId()+CommonConstants.verticleBar+labCheckBooking.getBookedForDate());
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
        System.out.println("------------ "+ScreenFields.labCheckRecommendation+" ------------");
        for(LabCheck labCheck : labCheckSet)
            System.out.println(labCheck.getCheckupId()+CommonConstants.commonTextSeparator+labCheck.getCheckupName());
    }

    private int inputHealthCheckId(Scanner scanner){
        int healthCheckId;
        System.out.print(ScreenFields.checkId+ CommonConstants.commonTextSeparator);

        while(true) {
            if (scanner.hasNextInt()) {
                healthCheckId = scanner.nextInt();
                if (healthCheckId < 1 || healthCheckId > 10)
                    consoleObj.printError(CommonErrors.INVALID_CHECK_UP_ID);
                else
                    return healthCheckId;
            } else
                consoleObj.printError(CommonErrors.invalidSelection);
        }
    }

    private Date inputBookingDate(Scanner scanner){
        Date bookingdate;
        System.out.print(ScreenFields.dateInput+CommonConstants.commonTextSeparator);

        while(true) {
            try {
                bookingdate = Date.valueOf(scanner.next());
                if(bookingdate.compareTo(new Date(System.currentTimeMillis())) < 0) {
                    consoleObj.printError(CommonErrors.SMALLER_DATE);
                }
                else
                    return bookingdate;
            }
            catch(IllegalArgumentException illegalArgumentException) {
                consoleObj.printError(CommonErrors.invalidDateFormat);
            }
        }
    }
}
