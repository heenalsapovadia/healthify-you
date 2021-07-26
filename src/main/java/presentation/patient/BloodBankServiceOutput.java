package presentation.patient;
import java.util.*;
import presentation.common.*;
import persistence.patient.daoImpl.BloodBankServiceDAOImpl;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
import persistence.patient.utilImpl.BloodBankServiceUtilImpl;

public class BloodBankServiceOutput {

    public static void bloodBankService() {

        PrintToConsole consoleObj = PrintToConsole.getInstance();
        /*
          Main dashboard for bloodBankSerivce  options list
        */
        // options to show user what type of services are offered like register for blood donation, my donation etc.
        List<String> selectionOptions = Arrays.asList(ScreenFields.registerPatientForBloodDonation, ScreenFields.viewDonationHistory,
                ScreenFields.exitFromBloodDonation);

        while (true) {
            consoleObj.printHeader(ScreenTitles.bloodBankService);
            int option = consoleObj.printSelection(selectionOptions);

            switch (option) {
                case 1:
                    registerForBloodDonation();
                    break;
                case 2:
                    viewPreviousDonations();
                    break;
                case 3:
                    return;
            }
            consoleObj.printSingleNewLine();
        }
    }

    private static String registerForBloodDonation() {
        Scanner sc1 = new Scanner(System.in);
        System.out.println(ScreenFields.bloodDonationCriteria);

        BloodBankServiceDAOImpl bloodBankDatabase = new BloodBankServiceDAOImpl();
        BloodBankServiceUtilImpl bloodBankServiceUtil = new BloodBankServiceUtilImpl();
        List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(Patient.getPatient());
        BloodBankService service1 = new BloodBankService();
        System.out.println(ScreenFields.yourBloodGroup);
        String bloodGroupInput = null;
        bloodGroupInput = sc1.next();

        // validate blood group here
        while(true) {
            //bloodGroupInput = sc1.nextLine();
                if (bloodGroupInput.equals(service1.getBloodGrp())) {
                    //continue;
                    System.out.println("Blood Group Validated");
                } else {
                    System.out.println(ScreenFields.donationRecord);
                    bloodGroupInput = sc1.next();
                }
        }
//        if (!bloodGroupInput.equals(service1.getBloodGrp())) {
//            do {
//                System.out.println(ScreenFields.donationRecord);
//                   bloodGroupInput = sc1.next();
//
//            } while (bloodGroupInput.equals(service1.getBloodGrp()));
//        }


//            // Using logic to check if previous donation is 0 then user will be allowed to register for blood donation
//            if (donations.size() == 0) {
//                System.out.println(ScreenFields.checkingEligibility);
//                System.out.println(ScreenFields.patientIsEligible);
//                System.out.println(ScreenFields.registeringPatient);
//                System.out.println(ScreenFields.tokenGenerated + bloodBankServiceUtil.getTokenIdForDonation());
//                System.out.println(ScreenFields.donationDate + java.time.LocalDate.now());
//                System.out.println("We operate on Tuesdays and Sundays. Visit anytime.");
//                return bloodBankServiceUtil.registerPatientForBloodDonation(bloodBankDatabase, Patient.getPatient(), bloodGroupInput);
//            } else {
//                // If donation history is available and more than 6 months before will check eligibility if blood report is normal
//                Boolean donatedInLastSixMonths = false;
//                Boolean reportsAreNormalForBloodDonations = false;
//
//                for ( BloodBankService service : donations ) {
//                    // if blood group is wrong it will keep asking until blood group Ais correct
//                    bloodBankServiceUtil.validateSixMonthCheck(donations);
//
//                    if (!donatedInLastSixMonths == true) {
//                        // if previous donation is more than 6 months check report of the person and then proceed
//                        bloodBankServiceUtil.checkingEligibilityThroughReport();
//                        if (reportsAreNormalForBloodDonations == true) {
//                            System.out.println(ScreenFields.reportNormal);
//                            System.out.println(ScreenFields.registeringPatient);
//                            System.out.println(ScreenFields.tokenGenerated + bloodBankServiceUtil.getTokenIdForDonation());
//                            System.out.println(ScreenFields.donationDate + java.time.LocalDate.now());
//                            System.out.println(ScreenFields.hoursOfOperation);
//                            System.out.println(CommonConstants.NEW_LINE);
//                            return bloodBankServiceUtil.registerPatientForBloodDonation(bloodBankDatabase, Patient.getPatient(), bloodGroupInput);
//                        }
////                    else {
////                        System.out.println(ScreenFields.reportsNotNormal);
////                        System.out.println("\n");
////                        break;
////                    }
//                    }
//                }System.out.println(ScreenFields.reportsNotNormal);
//                System.out.println("\n");
//                //return "";
//            }


        //bloodGroupInput = sc1.nextLine();
        //return "";
    }


    private static void viewPreviousDonations() {
        BloodBankServiceDAOImpl bloodBankDatabase = new BloodBankServiceDAOImpl();
        List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(Patient.getPatient());
        if(donations.size()>0) {
            for ( BloodBankService service : donations ) {
                System.out.println("Patient-Id" + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + "Donation-Id" + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + "Date" + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + "Blood group" + CommonConstants.SINGLE_TAB);
                System.out.println(service.getPatientId() + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + service.getDonationId() + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + service.getDate() + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + service.getBloodGrp());
                System.out.println("\n");
            }
        }
        else{
            System.out.println(ScreenFields.noDontionRecordsFound);
        }
        //return null;
    }

}


