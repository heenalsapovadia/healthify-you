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
        List<String> selectionOptions = Arrays.asList(ScreenFields.REGISTER_PATIENT_FOR_BLOOD_DONATION, ScreenFields.VIEW_DONATION_HISTORY,
                ScreenFields.EXIT_FROM_BLOOD_DONATION);

        while (true) {
            consoleObj.printHeader(ScreenTitles.BLOOD_BANK_SERVICE_DASHBOARD);
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
        System.out.println(ScreenFields.BLOOD_DONATION_CRITERIA);

        BloodBankServiceDAOImpl bloodBankDatabase = new BloodBankServiceDAOImpl();
        List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(Patient.instance());
        BloodBankServiceUtilImpl bloodBankServiceUtil = new BloodBankServiceUtilImpl();
        System.out.println(ScreenFields.YOUR_BLOOD_GROUP);
        String actualBloodGroup = null;
        String bloodGroupInput = null;
        bloodGroupInput = sc1.next();

        // validate blood group here
        for(BloodBankService bloodBankService : donations){
            actualBloodGroup = bloodBankService.getBloodGrp();
        }
        while(true) {
            if (donations.size() == 0) {
                System.out.println(ScreenFields.CHECK_ELIGIBILITY);
                System.out.println(ScreenFields.PATIENT_IS_ELIGIBLE);
                System.out.println(ScreenFields.REGISTERING_PATIENT);
                System.out.println(ScreenFields.TOKEN_GENERATED + bloodBankServiceUtil.getTokenIdForDonation());
                System.out.println(ScreenFields.DONATION_DATE + java.time.LocalDate.now());
                System.out.println("We operate on Tuesdays and Sundays. Visit anytime.");
                return bloodBankServiceUtil.registerPatientForBloodDonation(bloodBankDatabase, Patient.instance(), bloodGroupInput);
            }
            if (actualBloodGroup != null && bloodGroupInput.equals(actualBloodGroup)) {
                System.out.println("Blood Group Validated");
                validateDonationDataAndReport(actualBloodGroup);
                break;
            } else {
                System.out.println(ScreenFields.DONATION_RECORD);
                bloodGroupInput = sc1.next();
            }
        }
        return "";
    }



    public static String validateDonationDataAndReport(String bloodGroupInput) {
        BloodBankServiceDAOImpl bloodBankDatabase = new BloodBankServiceDAOImpl();
        List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(Patient.instance());
        BloodBankServiceUtilImpl bloodBankServiceUtil = new BloodBankServiceUtilImpl();


        // if reports are not normal the user wont be able to register..
        // also if there are no reports available for specific patient they would be simply able to register
        if (!bloodBankServiceUtil.checkIfReportsAreNormalForDonation()) {
            System.out.println(ScreenFields.REPORTS_ARE_NORMAL_FOR_BLOOD_DONATIONS);
            return null;
        }
        // condition to check if previous donation is 6 months
        for ( BloodBankService bbservice : donations ) {
            if (!bloodBankServiceUtil.validateIfPreviousDonationMoreThanSixMonth(bbservice)) {
                System.out.println(ScreenFields.PATIENT_DONATED_SIX_MONTH_BEFORE);
                System.out.println(ScreenFields.REGISTERING_PATIENT);
                System.out.println(ScreenFields.TOKEN_GENERATED + bloodBankServiceUtil.getTokenIdForDonation());
                System.out.println(ScreenFields.DONATION_DATE + java.time.LocalDate.now());
                System.out.println(ScreenFields.HOURS_OF_OPERATION);
                System.out.println(CommonConstants.NEW_LINE);
                return bloodBankServiceUtil.registerPatientForBloodDonation(bloodBankDatabase, Patient.instance(), bloodGroupInput);
            } else {
                System.out.println(ScreenFields.PATIENT_ALREADY_DONATED);
                System.out.println("\n");
                return null;
            }
        }
        return null;
    }

    // user will be able to view their previous donations if any exists
    private static void viewPreviousDonations() {
        BloodBankServiceDAOImpl bloodBankDatabase = new BloodBankServiceDAOImpl();
        List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(Patient.instance());
        if(donations.size()>0) {
            for ( BloodBankService service : donations ) {
                System.out.println("Patient-Id" + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + "Donation-Id" + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + "Date" + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + "Blood group" + CommonConstants.SINGLE_TAB);
                System.out.println(service.getPatientId() + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + service.getDonationId() + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + service.getDate() + CommonConstants.SINGLE_TAB + CommonConstants.SINGLE_TAB + CommonConstants.VERTICAL_BAR + service.getBloodGrp());
                System.out.println("\n");
            }
        }
        else{
            System.out.println(ScreenFields.NO_DONTION_RECORDS_FOUND);
        }
    }
}


