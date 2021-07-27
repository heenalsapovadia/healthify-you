package persistence.admin.utilImpl;

import org.junit.Test;
import persistence.admin.util.VaccineDemandStatsUtil;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class VaccineDemandStatsUtilImplTest {
    VaccineDemandStatsUtil vaccineDemandStatsUtil;
    List<Map<String, Object>> dataRecords;

    @Test
    public void mostVaccinatedByVaccine() {
        dataRecords = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("vaccineName", "Influenza");
        Map<String, Object> map1 = new HashMap<>();
        map1.put("vaccineName", "covaxin");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("vaccineName", "Influenza");
        dataRecords.add(map);
        dataRecords.add(map1);
        dataRecords.add(map2);


        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals("Influenza", vaccineDemandStatsUtil.mostVaccinatedBy("vaccineName", dataRecords));
    }

    @Test
    public void mostVaccinatedByAge() {
        dataRecords = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("ageGroup", "27 to 60");
        Map<String, Object> map1 = new HashMap<>();
        map1.put("ageGroup", "27 to 60");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("ageGroup", "10 to 26");
        dataRecords.add(map);
        dataRecords.add(map1);
        dataRecords.add(map2);

        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals("27 to 60", vaccineDemandStatsUtil.mostVaccinatedBy("ageGroup", dataRecords));
    }

    @Test
    public void mostVaccinatedByGender() {
        dataRecords = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("gender", "F");
        Map<String, Object> map1 = new HashMap<>();
        map1.put("gender", "M");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("gender", "M");
        dataRecords.add(map);
        dataRecords.add(map1);
        dataRecords.add(map2);

        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals("M", vaccineDemandStatsUtil.mostVaccinatedBy("gender", dataRecords));
    }

    @Test
    public void mostVaccinatedByArea() {
        dataRecords = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("area", "Argentina");
        Map<String, Object> map1 = new HashMap<>();
        map1.put("area", "Halifax");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("area", "Argentina");
        dataRecords.add(map);
        dataRecords.add(map1);
        dataRecords.add(map2);

        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals("Argentina", vaccineDemandStatsUtil.mostVaccinatedBy("area", dataRecords));
    }

    @Test
    public void dosesAdministered() {
        dataRecords = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("date", Date.valueOf("2021-01-23"));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("date", Date.valueOf("2021-07-27"));
        Map<String, Object> map2 = new HashMap<>();
        map2.put("date", Date.valueOf("2021-07-25"));
        dataRecords.add(map);
        dataRecords.add(map1);
        dataRecords.add(map2);

        vaccineDemandStatsUtil = new VaccineDemandStatsUtilImpl();
        assertEquals(2, vaccineDemandStatsUtil.dosesAdministered(1, dataRecords));
    }

}