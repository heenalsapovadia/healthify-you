package presentation.admin;

import persistence.admin.dao.VaccineDemandDAO;
import persistence.admin.daoImpl.VaccineDemandDAOImpl;
import persistence.admin.utilImpl.VaccineDemandStatsUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * <pre>
 * Loads Immunization Statistics dashboard in the application.
 * </pre>
 *
 * @author Heenal Sapovadia
 *
 */
public class ImmunizationStatsOutput {

    private PrintToConsole consoleObj;
    private VaccineDemandStatsUtilImpl vaccineDemandStatsUtil;
    private List<Map<String, Object>> dataRecords;

    private static final String VACCINE_NAME = "vaccineName";
    private static final String AGE_GROUP = "ageGroup";
    private static final String GENDER = "gender";
    private static final String AREA = "area";
    private static final String COVISHIELD = "covishield";

    public ImmunizationStatsOutput() {
        consoleObj = PrintToConsole.getInstance();
        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        VaccineDemandDAO vaccineDemandDAO = new VaccineDemandDAOImpl();
        dataRecords = vaccineDemandDAO.getVaccinationData();
    }

    public void dashboard(){
        consoleObj.printHeader(ScreenTitles.VACCINE_STATS);

        System.out.println(ScreenFields.MOST_DEMANDED_VACCINE
                + CommonConstants.COMMON_TEXT_SEPARATOR
                + vaccineDemandStatsUtil.mostVaccinatedBy(VACCINE_NAME, dataRecords));
        System.out.println(ScreenFields.MOST_VACCINATED_AGEGROUP
                + CommonConstants.SINGLE_TAB
                + ":"
                + CommonConstants.MEDIUM_SPACE
                + vaccineDemandStatsUtil.mostVaccinatedBy(AGE_GROUP, dataRecords));
        System.out.println(ScreenFields.MOST_VACCINATED_GENDER
                + CommonConstants.COMMON_TEXT_SEPARATOR
                + vaccineDemandStatsUtil.mostVaccinatedBy(GENDER, dataRecords));
        System.out.println(ScreenFields.MOST_VACCINATED_AREA
                + CommonConstants.COMMON_TEXT_SEPARATOR
                + vaccineDemandStatsUtil.mostVaccinatedBy(AREA, dataRecords));

        consoleObj.printDoubleNewlines();

        covidAnalysis();

        consoleObj.printDoubleNewlines();

        dosesAdministered();
    }

    public void covidAnalysis(){
        Map<String, Integer> covidAnalysis = vaccineDemandStatsUtil.covidVaccineDistribution(dataRecords);

        System.out.println(ScreenTitles.COVID_ANALYSIS);
        if(covidAnalysis.isEmpty())
            System.out.println(CommonErrors.NO_DATA_FOR_COVID);
        else {
            for (Map.Entry<String, Integer> entry : covidAnalysis.entrySet()) {
                if(entry.getKey().equals(COVISHIELD)) System.out.println(entry.getKey()
                        + CommonConstants.SINGLE_TAB
                        + ":"
                        + CommonConstants.MEDIUM_SPACE
                        + entry.getValue());
                else
                    System.out.println(entry.getKey()
                            + CommonConstants.COMMON_TEXT_SEPARATOR
                            + entry.getValue());
            }
        }
    }

    public void dosesAdministered(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(ScreenFields.CHECK_DOSE_STATISTICS);
        List<String> options = Arrays.asList("Yes", "No");
        int option = consoleObj.printSelection(options);

        switch (option) {
            case 1:
                System.out.print(ScreenFields.ENTER_MONTHS + CommonConstants.COMMON_TEXT_SEPARATOR);
                int months;
                if(scanner.hasNextInt()) {
                    months = scanner.nextInt();
                    int doses = vaccineDemandStatsUtil.dosesAdministered(months, dataRecords);
                    System.out.println("Doses administered in the last " + months + " months"
                            + CommonConstants.COMMON_TEXT_SEPARATOR
                            + doses);
                }
                break;
            case 2:
                return;
        }
    }
}
