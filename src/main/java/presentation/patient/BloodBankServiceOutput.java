package presentation.patient;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import presentation.common.*;
import persistence.patient.daoImpl.BloodBankServiceDAOImpl;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
import persistence.patient.utilImpl.BloodBankServiceUtilImpl;

public class BloodBankServiceOutput  {

    public static String bloodBankService(Patient patient) {
        Scanner sc = new Scanner(System.in);
        String bloodGroupInput;
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace + CommonConstants.titleSpace + ScreenTitles.bloodBankService + CommonConstants.titleSpace);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        int userSelection = 0;
        System.out.println(ScreenFields.registerPatientForBloodDonation);
        System.out.println(ScreenFields.viewDonationHistory);
        System.out.println(ScreenFields.exit);
        System.out.println(ScreenFields.enterYourSelection);
        userSelection = sc.nextInt();
        
        if (userSelection == 1) {
            BloodBankServiceDAOImpl bloodBankDatabase = new BloodBankServiceDAOImpl();
            BloodBankServiceUtilImpl bloodBankServiceUtil = new BloodBankServiceUtilImpl();
            List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(patient);
            BloodBankServiceUtilImpl service1 = new BloodBankServiceUtilImpl();
            Scanner sc1 = new Scanner(System.in);
            System.out.println(ScreenFields.yourBloodGroup);
            bloodGroupInput = sc1.nextLine();
            if (donations.size() == 0) {
                System.out.println(ScreenFields.checkingEligibility);
                System.out.println(ScreenFields.patientIsEligible);
                System.out.println(ScreenFields.registeringPatient);
                System.out.println(ScreenFields.tokenGenerated + bloodBankServiceUtil.getTokenIdForDonation());
                System.out.println(ScreenFields.donationDate + java.time.LocalDate.now());
                System.out.println("We operate on Tuesdays and Sundays. Visit anytime.");
                return bloodBankServiceUtil.registerPatientForBloodDonation(bloodBankDatabase, patient, bloodGroupInput);

            } else {
                // Check eligibility
                Boolean donatedInLastSixMonths = false;
                for ( BloodBankService service : donations ) {
                    if (bloodGroupInput != service.getBloodGrp()) {
                        System.out.println(ScreenFields.donationRecord);
                        bloodGroupInput = sc1.nextLine();
                    }
                    int m1 = service.getDate().getYear() * 12 + service.getDate().getMonth();
                    Date currentDate = new Date();
                    int m2 = currentDate.getYear() * 12 + currentDate.getMonth();
                    // if greater than 6 months register for blood donation
                    if (m2 - m1 + 1 <= 6) {
                        donatedInLastSixMonths = true;
                    }
                    if (!donatedInLastSixMonths) {
                        return bloodBankServiceUtil.registerPatientForBloodDonation(bloodBankDatabase, patient, bloodGroupInput);
                    } else {
                        System.out.println(ScreenFields.patientAlreadyDonated);
                        break;
                    }
                }
            }
        }
        if (userSelection == 2) {
            // calling database class and checking if donation records exits then display previous records
            BloodBankServiceDAOImpl bloodBankDatabase = new BloodBankServiceDAOImpl();
            List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(patient);
            for ( BloodBankService service : donations ) {
                System.out.println("Patient-Id" + CommonConstants.singleTab + CommonConstants.verticleBar + "Donation-Id" + CommonConstants.singleTab + CommonConstants.verticleBar + "Date" + CommonConstants.singleTab + CommonConstants.singleTab + CommonConstants.verticleBar + "Blood group" + CommonConstants.singleTab);
                System.out.println(service.getPatientId() + CommonConstants.singleTab + CommonConstants.verticleBar + service.getDonationId() + CommonConstants.singleTab + CommonConstants.singleTab + CommonConstants.verticleBar + service.getDate() + CommonConstants.singleTab + CommonConstants.singleTab + CommonConstants.verticleBar + service.getBloodGrp());
            }
        }
        if (userSelection == 3) {
            System.out.println("EXIT!");
        }
        return null;
    }
}
