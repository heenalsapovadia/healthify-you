package persistence.admin.utilImpl;

import persistence.admin.dao.VaccineDemandDAO;
import persistence.admin.daoImpl.VaccineDemandDAOImpl;
import persistence.admin.util.VaccineDemandStatsUtil;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaccineDemandStatsUtilImpl implements VaccineDemandStatsUtil {

    private List<Map<String, Object>> data;
    
    private final static String COVAXIN = "covaxin";
    private final static String COVISHIELD = "covishield";
    private final static String PFIZER = "pfizer";
    private final static String MODERNA = "moderna";

    public VaccineDemandStatsUtilImpl(){
        VaccineDemandDAO vaccineDemandDAO = new VaccineDemandDAOImpl();
        data = vaccineDemandDAO.getVaccinationData();
    }

    @Override
    public String mostVaccinatedBy(String factor){
        Map<String, Integer> factorFrequency = new HashMap<>();
        String mostVaccinatedValue = (String) data.get(0).get(factor);

        for(Map<String, Object> dataRecord : data){
            String factorValue = (String) dataRecord.get(factor);
            factorFrequency.put(factorValue, factorFrequency.getOrDefault(factorValue, 0)+1);
            if(factorFrequency.get(factorValue) > factorFrequency.get(mostVaccinatedValue)) {
                mostVaccinatedValue = factorValue;
            }
        }

        return mostVaccinatedValue;
    }

    @Override
    public int dosesAdministered(int timePeriodInMonths){
        int dosesInGivenPeriod = 0;
        LocalDate today = LocalDate.now();
        for(Map<String, Object> dataRecord : data){
            Date dateOfDose = (Date) dataRecord.get("date");
            LocalDate dateOfDoseLocal = dateOfDose.toLocalDate();
            if (Period.between(dateOfDoseLocal, today).getMonths() <= timePeriodInMonths)
                dosesInGivenPeriod++;
        }
        return dosesInGivenPeriod;
    }
    
    @Override
    public Map<String, Integer> covidVaccineDistribution(){
        Map<String, Integer> covidAnalysis = new HashMap<>();
        int totalCovidShots = 0;
        for(Map<String, Object> dataRecord : data) {
            String vaccine = ((String) dataRecord.get("vaccineName")).toLowerCase();
            boolean isCovaxin = vaccine.equals(COVAXIN);
            boolean isCovishield = vaccine.equals(COVISHIELD);
            boolean isPfizer = vaccine.equals(PFIZER);
            boolean isModerna = vaccine.equals(MODERNA);
            if(isCovaxin || isCovishield || isPfizer || isModerna){
                totalCovidShots++;
                if (isCovaxin)
                    covidAnalysis.put(COVAXIN, covidAnalysis.getOrDefault(COVAXIN, 0)+1);
                else if (isCovishield)
                    covidAnalysis.put(COVISHIELD, covidAnalysis.getOrDefault(COVISHIELD, 0)+1);
                else if (isPfizer)
                    covidAnalysis.put(PFIZER, covidAnalysis.getOrDefault(PFIZER, 0)+1);
                else
                    covidAnalysis.put(MODERNA, covidAnalysis.getOrDefault(MODERNA, 0)+1);
            }
        }
        return covidAnalysis;
    }
}
