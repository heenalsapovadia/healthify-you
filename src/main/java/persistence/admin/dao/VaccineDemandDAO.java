package persistence.admin.dao;

import java.util.List;
import java.util.Map;

public interface VaccineDemandDAO {
	List<Map<String, Object>> getVaccinationData();
}
