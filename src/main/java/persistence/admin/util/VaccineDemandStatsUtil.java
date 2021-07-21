package persistence.admin.util;

import java.util.Map;

public interface VaccineDemandStatsUtil {
    String mostVaccinatedBy(String factor);

    int dosesAdministered(int timePeriodInMonths);

    Map<String, Integer> covidVaccineDistribution();
}
