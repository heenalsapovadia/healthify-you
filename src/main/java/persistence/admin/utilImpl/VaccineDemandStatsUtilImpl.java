package persistence.admin.utilImpl;

import persistence.admin.util.VaccineDemandStatsUtil;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heenal Sapovadia
 * This class is responsible for handling the statistics calculations for Vaccination Demand
 */
public class VaccineDemandStatsUtilImpl implements VaccineDemandStatsUtil {
    
    private final static String COVAXIN = "covaxin";
    private final static String COVISHIELD = "covishield";
    private final static String PFIZER = "pfizer";
    private final static String MODERNA = "moderna";

    public VaccineDemandStatsUtilImpl(){ }

    @Override
    public String mostVaccinatedBy(String factor, List<Map<String, Object>> dataRecords){
        Map<String, Integer> factorFrequency = new HashMap<>();
        String mostVaccinatedValue = (String) dataRecords.get(0).get(factor);

        for(Map<String, Object> dataRecord : dataRecords){
            String factorValue = (String) dataRecord.get(factor);
            factorFrequency.put(factorValue, factorFrequency.getOrDefault(factorValue, 0)+1);
            if(factorFrequency.get(factorValue) > factorFrequency.get(mostVaccinatedValue)) {
                mostVaccinatedValue = factorValue;
            }
        }

        return mostVaccinatedValue;
    }

    @Override
    public int dosesAdministered(int timePeriodInMonths, List<Map<String, Object>> dataRecords){
        int dosesInGivenPeriod = 0;
        LocalDate today = LocalDate.now();
        for(Map<String, Object> dataRecord : dataRecords){
            Date dateOfDose = (Date) dataRecord.get("date");
            LocalDate dateOfDoseLocal = dateOfDose.toLocalDate();
            if (Period.between(dateOfDoseLocal, today).getMonths() <= timePeriodInMonths)
                dosesInGivenPeriod++;
        }
        return dosesInGivenPeriod;
    }
    
    @Override
    public Map<String, Integer> covidVaccineDistribution(List<Map<String, Object>> dataRecords){
        Map<String, Integer> covidAnalysis = new HashMap<>();
        for(Map<String, Object> dataRecord : dataRecords) {
            String vaccine = ((String) dataRecord.get("vaccineName")).toLowerCase();
            boolean isCovaxin = vaccine.equals(COVAXIN);
            boolean isCovishield = vaccine.equals(COVISHIELD);
            boolean isPfizer = vaccine.equals(PFIZER);
            boolean isModerna = vaccine.equals(MODERNA);
            if(isCovaxin || isCovishield || isPfizer || isModerna){
                if (isCovaxin) {
                    covidAnalysis.put(COVAXIN, covidAnalysis.getOrDefault(COVAXIN, 0) + 1);
                }
                else if (isCovishield) {
                    covidAnalysis.put(COVISHIELD, covidAnalysis.getOrDefault(COVISHIELD, 0) + 1);
                }
                else if (isPfizer) {
                    covidAnalysis.put(PFIZER, covidAnalysis.getOrDefault(PFIZER, 0) + 1);
                }
                else {
                    covidAnalysis.put(MODERNA, covidAnalysis.getOrDefault(MODERNA, 0) + 1);
                }
            }
        }
        return covidAnalysis;
    }
}
