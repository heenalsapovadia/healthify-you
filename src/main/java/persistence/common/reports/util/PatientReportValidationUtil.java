package persistence.common.reports.util;

public interface PatientReportValidationUtil {
    boolean validateBloodReports();

    boolean validateKidneyReports();

    boolean validateLiverReports();

    boolean validateEyeReports();
}
