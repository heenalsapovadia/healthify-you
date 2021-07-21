package presentation.admin;

import persistence.admin.utilImpl.VaccineDemandStatsUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ImmunizationStatsOutput {
    PrintToConsole consoleObj = PrintToConsole.getInstance();
    VaccineDemandStatsUtilImpl vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();

    public void dashboard(){
        // Title
        consoleObj.printHeader(ScreenTitles.vaccineStats);

        System.out.println(ScreenFields.mostDemandedVaccine
                + CommonConstants.commonTextSeparator
                + vaccineDemandStatsUtil.mostVaccinatedBy("vaccineName"));
        System.out.println(ScreenFields.mostVaccinatedAge
                + CommonConstants.singleTab
                + ":"
                + CommonConstants.mediumSpace
                + vaccineDemandStatsUtil.mostVaccinatedBy("ageGroup"));
        System.out.println(ScreenFields.mostVaccinatedGender
                + CommonConstants.commonTextSeparator
                + vaccineDemandStatsUtil.mostVaccinatedBy("gender"));
        System.out.println(ScreenFields.mostVaccinatedArea
                + CommonConstants.commonTextSeparator
                + vaccineDemandStatsUtil.mostVaccinatedBy("area"));

        consoleObj.printDoubleNewlines();

        covidAnalysis();

        consoleObj.printDoubleNewlines();

        dosesAdministered();
    }

    public void covidAnalysis(){
        Map<String, Integer> covidAnalysis = vaccineDemandStatsUtil.covidVaccineDistribution();

        System.out.println(ScreenTitles.covidAnalysis);
        if(covidAnalysis.isEmpty())
            System.out.println("No data available for Covid Shot analysis");
        else {
            for (Map.Entry<String, Integer> entry : covidAnalysis.entrySet()) {
                System.out.println(entry.getKey() + CommonConstants.commonTextSeparator + entry.getValue());
            }
        }
    }

    public void dosesAdministered(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to check the statistics of doses administered ? ");
        List<String> options = Arrays.asList("Yes", "No");
        int option = consoleObj.printSelection(options);

        switch (option) {
            case 1:
                System.out.print("Enter number of months"+CommonConstants.commonTextSeparator);
                int months;
                if(sc.hasNextInt()) {
                    months = sc.nextInt();
                    int doses = vaccineDemandStatsUtil.dosesAdministered(months);
                    System.out.println("Doses administered in the last " + months + " months"
                            + CommonConstants.commonTextSeparator
                            + doses);
                }
                break;
            case 2:
                return;
        }
    }
}
