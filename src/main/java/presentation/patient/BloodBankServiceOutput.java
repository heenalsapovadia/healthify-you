package presentation.patient;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import persistence.patient.daoImpl.BloodBankServiceDAOimpl;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.Patient;
import presentation.CommonConstants;
import presentation.ScreenTitles;

public class BloodBankServiceOutput  {

    public static String bloodBankService(Patient patient, String bloodGroup) {
        Scanner sc = new Scanner(System.in);
        int userSelection;
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace + CommonConstants.titleSpace + ScreenTitles.prescription + CommonConstants.titleSpace);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();

        // If user enter 1 then eligibility is checked
        // If user has no records than automatically registered...
        // check whether data is minimum 6 month
        // check if report is normal - will input json format inorder to extract normal reports..

        while (true) {
            System.out.println("1. Registration for Blood Donation");
            System.out.println("2. My Donations");
            System.out.println("3. Exit");
            System.out.println("Please enter your selection below:");
            userSelection = sc.nextInt();
            switch (userSelection) {
                case 1: {
                    BloodBankServiceDAOimpl bloodBankDatabase = new BloodBankServiceDAOimpl();
                    List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(patient);
                    if (donations.size() == 0) {
                        System.out.println("No Previous Donations for Patient!");
                        System.out.println("Registering Patient! Below Token Provided for the New Registration");
                        // if user is new register them
                        //return registerPatientForBloodDonation(bloodBankDatabase, patient, bloodGroup);
                    } else {
                        // Check eligibility
                        Boolean donatedInLastSixMonths = false;
                        for (BloodBankService service : donations) {

                        }
                        if (!donatedInLastSixMonths) {
                            //return registerPatientForBloodDonation(bloodBankDatabase, patient, bloodGroup);
                        } else {
                            System.out.println("Patient has already Donated in last 6 months and is not eligible.");
                            return null;
                        }
                    }
                }
                case 2: {
                    // calling database class and checking if donation records exits then display previous records
                    BloodBankServiceDAOimpl bloodBankDatabase = new BloodBankServiceDAOimpl();
                    List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(patient);
                    for (BloodBankService service : donations) {
                        System.out.println("Patient Id - " + service.getPatientId() + " - Donation Id - " + service.getDonationId() + " - Data - " + service.getDate() + " - Blood group - " + service.getBloodGrp());
                    }
                    return null;
                }
                case 3: {
                    System.out.println("EXIT!");
                    return null;
                }
            }
        }
    }

//    public static void registerPatientForBloodDonation(BloodBankServiceDAOimpl bloodBankDatabase, Patient patient, String bloodGroup) {
//
//    }
}