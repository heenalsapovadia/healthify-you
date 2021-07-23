package persistence.common.jsonUtil.util;

import persistence.common.reports.model.Blood;
import persistence.common.reports.model.Covid;
import persistence.common.reports.model.Kidney;
import persistence.common.reports.model.Liver;
import persistence.common.reports.model.Vision;

import java.util.List;
import java.util.Map;

public interface JsonPatientReportParser {
    Map getPatientReport(int patientId);

    List<Blood> parseBloodReports(Map tests);

    List<Kidney> parseKidneyReports(Map tests);

    List<Liver> parseLiverReports(Map tests);

    List<Vision> parseEyeReports(Map tests);
    
    List<Covid> parseCovidReports(Map tests);
}
