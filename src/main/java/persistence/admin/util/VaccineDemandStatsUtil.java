package persistence.admin.util;

import java.util.List;
import java.util.Map;

public interface VaccineDemandStatsUtil {
	String mostVaccinatedBy(String factor, List<Map<String, Object>> dataRecords);

	int dosesAdministered(int timePeriodInMonths, List<Map<String, Object>> dataRecords);

	Map<String, Integer> covidVaccineDistribution(List<Map<String, Object>> dataRecords);
}
