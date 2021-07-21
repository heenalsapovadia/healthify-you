package presentation.admin;

import persistence.admin.utilImpl.VaccineDemandStatsUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

public class ImmunizationStatsOutput {
    PrintToConsole consoleObj = PrintToConsole.getInstance();

    public void dashboard(){
        // Title
        consoleObj.printHeader(ScreenTitles.vaccineStats);

        VaccineDemandStatsUtilImpl vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();

        System.out.println(ScreenFields.mostDemandedVaccine
                + CommonConstants.commonTextSeparator
                + vaccineDemandStatsUtil.mostVaccinatedBy("vaccineName"));
        System.out.println(ScreenFields.mostVaccinatedAge
                + CommonConstants.commonTextSeparator
                + vaccineDemandStatsUtil.mostVaccinatedBy("ageGroup"));
        System.out.println(ScreenFields.mostVaccinatedGender
                + CommonConstants.commonTextSeparator
                + vaccineDemandStatsUtil.mostVaccinatedBy("gender"));
        System.out.println(ScreenFields.mostVaccinatedArea
                + CommonConstants.commonTextSeparator
                + vaccineDemandStatsUtil.mostVaccinatedBy("area"));
    }
}
