package presentation.admin;

import persistence.admin.utilImpl.BloodBankRecommendationUtilImpl;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

import static presentation.common.ScreenFields.BLOOD_BANK_MESSAGE;

/**
* <pre>
* Output class for blood bank recommendation
* </pre>
*
* @author Samiksha Salgaonkar
*
**/


public class BloodBankRecommendationOutput {

  public void getBloodBankRecommendations() {
    PrintToConsole consoleObj = PrintToConsole.getInstance();
    consoleObj.printHeader(ScreenTitles.BLOOD_BANK_RECOMMENDATION);
    consoleObj.printDoubleNewlines();
    Scanner sc = new Scanner(System.in);
    BloodBankRecommendationUtilImpl bloodBankRecommendationUtil = new BloodBankRecommendationUtilImpl();

    String bloodGroup = "";
    consoleObj.printScreenFields(ScreenFields.BLOOD_GROUP_INPUT);
    bloodGroup = sc.nextLine().toUpperCase(Locale.ROOT);
    while (!bloodBankRecommendationUtil.validateBloodGroup(bloodGroup)) {
      System.err.println("Enter blood group from the given list!");
      consoleObj.printScreenFields(ScreenFields.BLOOD_GROUP_INPUT);
      bloodGroup = sc.nextLine().toUpperCase(Locale.ROOT);
    }

    Scanner sc1 = new Scanner(System.in);
    int numRec = 0;
    consoleObj.printScreenFields(ScreenFields.NUM_REC_INPUT);
    numRec = sc1.nextInt();

    while (!bloodBankRecommendationUtil.validateNumRec(numRec)) {
      System.err.println("Enter blood group from the given list!");
      consoleObj.printScreenFields(ScreenFields.NUM_REC_INPUT);
      numRec = sc1.nextInt();
    }

    HashSet<ArrayList<String>> result = bloodBankRecommendationUtil.getBloodGroupList(bloodGroup, numRec);
    System.out.println("The blood groups most frequently ordered along with " + bloodGroup + " are:\t");

    for(ArrayList<String> answer : result) {
        System.out.println(answer);
    }

    System.out.println(BLOOD_BANK_MESSAGE);

  }

}
