package presentation.patient;

import persistence.patient.model.LabCheck;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.util.LabCheckBookingUtil;
import persistence.patient.util.LabCheckRecommendationUtil;
import persistence.patient.util.LabCheckUtil;
import persistence.patient.utilImpl.LabCheckBookingUtilImpl;
import persistence.patient.utilImpl.LabCheckRecommendationUtilImpl;
import persistence.patient.utilImpl.LabCheckUtilImpl;
import presentation.common.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LabTestBookingOutput {

    PrintToConsole consoleObj = PrintToConsole.getInstance();

    public void dashboard(){
        /*
        Dashboard title
         */
        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace+CommonConstants.titleSpace+ ScreenTitles.bookATest+CommonConstants.titleSpace);
        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);

        /*
        Dashboard main options list
         */
        System.out.println();
        System.out.println("1. "+ScreenTitles.availablePlans);
        System.out.println("2. "+ScreenTitles.labTestRecommendation);
        System.out.println("3. "+ScreenTitles.makeBooking);
        System.out.println("4. "+ScreenTitles.previousBookings);
        System.out.println("5. "+ ScreenFields.exit);

        /*
        User input
         */
        System.out.print(ScreenFields.enterOption+CommonConstants.commonTextSeparator);
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

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
        while(true) {
            System.out.println("1. "+ScreenFields.viewDetails);
            System.out.println("2. "+ScreenFields.backToBooking);
            int option = sc.nextInt();
            switch (option){
                case 1:
                    labCheckUtil.fetchDetails();
                    break;
                case 2:
                    return;
            }
        }
    }

    public void makeBooking(){
        consoleObj.printHeader(ScreenTitles.makeBooking);

        int healthCheckId;
        Date bookingdate;
        Scanner sc = new Scanner(System.in);

        healthCheckId = inputHealthCheckId(sc);
        bookingdate = inputBookingDate(sc);

        LabCheckBookingUtil labCheckBookingUtil = new LabCheckBookingUtilImpl();
        labCheckBookingUtil.makeBooking(healthCheckId, bookingdate);
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
        Set<LabCheck> labCheckSet = new HashSet<>();
        for(LabCheck labCheck : ageBasedRecommendationList)
            labCheckSet.add(labCheck);
        for(LabCheck labCheck : genderBasedRecommendationList)
            labCheckSet.add(labCheck);
        System.out.println(ScreenFields.labCheckRecommendation+CommonConstants.commonTextSeparator);

        for(LabCheck labCheck : labCheckSet){
            System.out.println(labCheck.getCheckup_id()+CommonConstants.commonTextSeparator+labCheck.getCheckup_name());
        }
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
