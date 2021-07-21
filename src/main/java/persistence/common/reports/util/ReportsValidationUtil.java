package persistence.common.reports.util;

import persistence.common.reports.model.*;

public interface ReportsValidationUtil {

    boolean validateBloodReport(Blood bloodReport, CBC cbcMin, CBC cbcMax);

    boolean validateKidneyReport(Kidney kidneyReport, Kidney kidneyPanelMin, Kidney kidneyPanelMax);

    boolean validateLiverReport(Liver liverReport, Liver liverPanelMin, Liver liverPanelMax);

    boolean validateEyeReport(Vision eyeReport);
}
