package presentation.patient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import persistence.patient.daoImpl.BloodBankServiceDAOImpl;
import persistence.patient.daoImpl.RegistrationDAOImpl;
import persistence.patient.model.BloodBankService;
import persistence.patient.model.BloodTestReport;
import persistence.patient.model.Patient;
import persistence.patient.utilImpl.BloodBankServiceUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.ScreenTitles;

public class BloodBankServiceOutput  {

    public static String bloodBankService(Patient patient) {
        Scanner sc = new Scanner(System.in);
        int userSelection;
        String bloodGroupInput;
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();
        System.out.println(CommonConstants.titleSpace + CommonConstants.titleSpace + ScreenTitles.bloodBankService + CommonConstants.titleSpace);
        for ( int i = 0; i < 100; i++ )
            System.out.print(CommonConstants.headingChar);
        System.out.println();

        // If user enter 1 then eligibility is checked
        // If user has no records than automatically registered...
        // check whether data is minimum 6 month
        // check if report is normal - will input json format inorder to extract normal reports..

        while(true)  {
            System.out.println("1. Register for Blood Donation and enter your Blood group");
            System.out.println("2. My Donations");
            System.out.println("3. Exit");
            System.out.println("Please enter your selection below:");
            userSelection = sc.nextInt();
            switch (userSelection) {
                case 1: {
                    // checking for one instance of blood test report...
//                    BloodBankService bloodreport = new BloodBankService();
//                    List<BloodTestReport> bloodTests = bloodreport.getBloodTests();
//                    for (BloodTestReport test : bloodTests) {
//                        if (test.getHemoglobinValue() < 5) {
//                            System.out.println("Patient has low hemoglobin, so not eligible");@
//                            return null;
//                        }
//                    }
                    BloodBankServiceDAOImpl bloodBankDatabase = new BloodBankServiceDAOImpl();
                    BloodBankServiceUtilImpl bloodBankServiceUtil = new BloodBankServiceUtilImpl();
                    List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(patient);
                    BloodBankServiceUtilImpl service1 = new BloodBankServiceUtilImpl();
                    Scanner sc1 = new Scanner(System.in);
                    bloodGroupInput = sc1.nextLine();
                    if (donations.size() == 0) {
                        System.out.println("Checking Eligibility....");
                        System.out.println("Eligible...No previous donations found for the Patient!");
//                        System.out.println("Please enter you blood group:-" + bloodGroupInput );
                        System.out.println("Registering Patient!");
                        System.out.println("Your Token is: " + bloodBankServiceUtil.getTokenIdForDonation() );
                        System.out.println("Donation Date: " + java.time.LocalDate.now());
                        System.out.println("We operate on Tuesdays and Sundays. Visit anytime.");
                        return registerPatientForBloodDonation(bloodBankDatabase, patient, bloodGroupInput);

                    } else {
                        // Check eligibility
                        Boolean donatedInLastSixMonths = false;
                        for ( BloodBankService service : donations ) {
                            if(bloodGroupInput != service.getBloodGrp()){
                                System.out.println("Wrong blood group entered. Please enter again to view donation history.");
                            }
                            bloodGroupInput = sc1.nextLine();
                            int m1 = service.getDate().getYear() * 12 + service.getDate().getMonth();
                            Date currentDate = new Date();
                            int m2 = currentDate.getYear() * 12 + currentDate.getMonth();
                            // if greater than 6 months register for blood donation
                            if (m2 - m1 + 1 <= 6) {
                                donatedInLastSixMonths = true;
                            }
                            if (!donatedInLastSixMonths) {
                                return registerPatientForBloodDonation(bloodBankDatabase, patient,bloodGroupInput);
                            } else {
                                System.out.println("Patient has already Donated in last 6 months and is not eligible.");
                                break;
                            }
                        }
                    }
                }
                case 2: {
                    // calling database class and checking if donation records exits then display previous records
                    BloodBankServiceDAOImpl bloodBankDatabase = new BloodBankServiceDAOImpl();
                    List<BloodBankService> donations = bloodBankDatabase.getAllBloodDonationsForPatient(patient);
                    for (BloodBankService service : donations) {
                     System.out.println("Patient-Id" + CommonConstants.singleTab + CommonConstants.verticleBar + "Donation-Id" + CommonConstants.singleTab + CommonConstants.verticleBar + "Date" + CommonConstants.singleTab + CommonConstants.singleTab + CommonConstants.verticleBar + "Blood group" + CommonConstants.singleTab);
                     System.out.println(service.getPatientId() + CommonConstants.singleTab + CommonConstants.verticleBar + service.getDonationId() + CommonConstants.singleTab + CommonConstants.singleTab + CommonConstants.verticleBar + service.getDate() + CommonConstants.singleTab + CommonConstants.singleTab + CommonConstants.verticleBar + service.getBloodGrp());
                    }
                    break;
                }
                case 3: {
                    System.out.println("EXIT!");
                    return null;
                }
            }
        }
    }

    public static String registerPatientForBloodDonation(BloodBankServiceDAOImpl bloodBankDatabase, Patient patient, String bloodGroupInput) {
        BloodBankService bbservice = new BloodBankService();
        BloodBankServiceUtilImpl serviceUtil = new BloodBankServiceUtilImpl();
        BloodTestReport bloodTestReport = new BloodTestReport();
        String donationId = serviceUtil.getRandomStringForDonationId();
        bbservice.setBloodGrp(bloodGroupInput);
        // since no patient id yet in Patient model validating through patient email
        bbservice.setPatientId(patient.getPatientId());
        Date d1 = new Date();
        bbservice.setDate(d1);
        bbservice.setDonationId(donationId);
//        List<BloodTestReport> tests = new ArrayList<BloodTestReport>();
//        BloodTestReport bloodTest = new BloodTestReport();
//        bloodTest.setHemoglobinValue(4);
        bloodBankDatabase.insertBloodBankServiceDetails(bbservice);
        return donationId;
    }
}
