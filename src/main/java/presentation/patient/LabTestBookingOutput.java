package presentation.patient;

import persistence.patient.model.LabCheck;
import persistence.patient.util.LabCheckUtil;
import persistence.patient.utilImpl.LabCheckUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

import java.util.List;
import java.util.Scanner;

public class LabTestBookingOutput {

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

        LabTestBookingOutput labTestBookingOutput = new LabTestBookingOutput();

        switch (option){
            case 1:
                labTestBookingOutput.viewPlans();
                break;
            case 2:
                break;
        }

    }

    public void viewPlans(){
        Scanner sc = new Scanner(System.in);
        /*
        View Plans title
         */
        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace+CommonConstants.titleSpace+ ScreenTitles.availablePlans+CommonConstants.titleSpace);
        for(int i=0; i<100; i++)
            System.out.print(CommonConstants.headingChar);
        System.out.println();

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
}
