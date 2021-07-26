package presentation.patient;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.patient.model.*;
import persistence.patient.util.*;
import persistence.patient.utilImpl.*;
import presentation.common.CommonConstants;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import presentation.common.PaymentInterfaceOutput;
import java.sql.Date;
import java.util.*;

public class LabTestBookingOutput {

    PrintToConsole consoleObj = PrintToConsole.getInstance();

    public void dashboard(){
        List<String> selectionOptions = Arrays.asList(ScreenTitles.AVAILABLE_PLANS, ScreenTitles.LAB_TEST_RECOMMENDATION,
                ScreenTitles.MAKE_BOOKING, ScreenTitles.PREVIOUS_BOOKINGS, ScreenFields.EXIT);
        while(true) {
            consoleObj.printHeader(ScreenTitles.BOOK_A_TEST);
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
        consoleObj.printHeader(ScreenTitles.AVAILABLE_PLANS);

        LabCheckUtil labCheckUtil = new LabCheckUtilImpl();
        List<LabCheck> labCheckList = labCheckUtil.fetchLabCheckPlans();
        for(LabCheck labCheck : labCheckList){
            System.out.println(""+labCheck.getCheckupId()+CommonConstants.COMMON_TEXT_SEPARATOR+labCheck.getCheckupName());
        }
        List<String> selectionOptions = Arrays.asList(ScreenFields.VIEW_DETAILS, ScreenFields.BACK_TO_BOOKING);
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
        consoleObj.printHeader(ScreenTitles.MAKE_BOOKING);

        int healthCheckId;
        Date bookingdate;

        LabCheckUtil labCheckUtil = new LabCheckUtilImpl();
        Map<Integer, LabCheck> labCheckMap = labCheckUtil.fetchLabCheckMap();

        Scanner scanner = new Scanner(System.in);

        healthCheckId = inputHealthCheckId(scanner);
        bookingdate = inputBookingDate(scanner);
        double healthCheckCharges = labCheckMap.get(healthCheckId).getCharges();

        List<String> options = Arrays.asList(ScreenFields.CONTINUE_TO_PAYMENT, ScreenFields.EXIT);
        int option = consoleObj.printSelection(options);


        int billingId = 0;
        switch (option) {
            case 1:
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
        consoleObj.printHeader(ScreenTitles.PREVIOUS_BOOKINGS);

        LabCheckBookingUtil labCheckBookingUtil = new LabCheckBookingUtilImpl();
        List<LabCheckBooking> labCheckBookingList = labCheckBookingUtil.fetchBookings();
        System.out.println(ScreenFields.APPOINTMENT_ID + CommonConstants.VERTICAL_BAR
                + ScreenFields.HEALTH_CHECK_ID + CommonConstants.VERTICAL_BAR
                + ScreenFields.DATE);
        for(LabCheckBooking labCheckBooking : labCheckBookingList){
            System.out.println(labCheckBooking.getAppointmentId() + CommonConstants.VERTICAL_BAR
                    + labCheckBooking.getHealthcheckId() + CommonConstants.VERTICAL_BAR
                    + labCheckBooking.getBookedForDate());
        }
    }

    public void getRecommendations(){
        consoleObj.printHeader(ScreenTitles.LAB_TEST_RECOMMENDATION);
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
        System.out.println("------------ "+ScreenFields.LAB_CHECK_RECOMMENDATION +" ------------");
        for(LabCheck labCheck : labCheckSet)
            System.out.println(labCheck.getCheckupId()+CommonConstants.COMMON_TEXT_SEPARATOR+labCheck.getCheckupName());
    }

    private int inputHealthCheckId(Scanner scanner){
        int healthCheckId;
        System.out.print(ScreenFields.HEALTH_CHECK_NUMBER + CommonConstants.COMMON_TEXT_SEPARATOR);

        while(true) {
            if (scanner.hasNextInt()) {
                healthCheckId = scanner.nextInt();
                if (healthCheckId < 1 || healthCheckId > 10)
                    consoleObj.printError(CommonErrors.INVALID_CHECK_UP_ID);
                else
                    return healthCheckId;
            } else
                consoleObj.printError(CommonErrors.INVALID_SELECTION);
        }
    }

    private Date inputBookingDate(Scanner scanner){
        Date bookingdate;
        System.out.print(ScreenFields.DATEINPUT+CommonConstants.COMMON_TEXT_SEPARATOR);

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
                consoleObj.printError(CommonErrors.INVALID_DATE_FORMAT);
            }
        }
    }
}
