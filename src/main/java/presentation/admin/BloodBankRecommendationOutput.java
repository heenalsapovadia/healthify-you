package presentation.admin;

import persistence.admin.utilImpl.BloodBankRecommendationUtilImpl;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
import presentation.startup.DatabaseConnection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class BloodBankRecommendationOutput {

  public void getBloodBankRecommendations() {
    PrintToConsole consoleObj = PrintToConsole.getInstance();
    //consoleObj.printHeader(ScreenTitles.pharmaInvoice);
    consoleObj.printHeader("Recommendations for Blood Bank Management");
    consoleObj.printDoubleNewlines();
    Scanner sc = new Scanner(System.in);
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();

    String bloodGroup = "";
    consoleObj.printScreenFields("Please enter the primary blood group to be ordered from the list - A+, AB+, B+, O+, A-, AB-, B-, O- (case-insensitive)");
    bloodGroup = sc.nextLine().toUpperCase(Locale.ROOT);
    while (!bloodBankRecommendationUtil.validateBloodGroup(bloodGroup)) {
      System.err.println("Enter blood group from the given list!");
      consoleObj.printScreenFields("Please enter the primary blood group to be ordered from the list - A+, AB+, B+, O+, A-, AB-, B-, O- (case-insensitive)");
      bloodGroup = sc.nextLine().toUpperCase(Locale.ROOT);
    }

    Scanner sc1 = new Scanner(System.in);
    int numRec = 0;
    consoleObj.printScreenFields("Please enter the number of recommendations");
    numRec = sc1.nextInt();

    while (!bloodBankRecommendationUtil.validateNumRec(numRec)) {
      System.err.println("Enter blood group from the given list!");
      consoleObj.printScreenFields("Please enter the number of recommendations");
      numRec = sc1.nextInt();
    }

    HashSet<ArrayList<String>> result = bloodBankRecommendationUtil.getBloodGroupList(bloodGroup, numRec);
    System.out.println("The blood groups most frequently ordered along with " + bloodGroup + " are:\t");

    for(ArrayList<String> answer : result) {
        System.out.println(answer);
    }

    System.out.println("The blood groups displayed are the in the order of most frequently ordered to least frequently ordered!");

  }

  public static void main(String[] args) {
    DatabaseConnection.loadDatabaseConnection();
    BloodBankRecommendationOutput bloodBankRecommendationOutput = new BloodBankRecommendationOutput();
    bloodBankRecommendationOutput.getBloodBankRecommendations();
  }
}
