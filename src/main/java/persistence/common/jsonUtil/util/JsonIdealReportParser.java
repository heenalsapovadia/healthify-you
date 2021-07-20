package persistence.common.jsonUtil.util;

import persistence.common.reports.model.CBC;
import persistence.common.reports.model.Kidney;
import persistence.common.reports.model.Liver;

import java.util.Map;

public interface JsonIdealReportParser {

    Map parseIdealReports();

    CBC getCBCMin(Map CBCPanel);

    CBC getCBCMax(Map CBCPanel);

    Kidney getKidneyMin(Map kidneyPanel);

    Kidney getKidneyMax(Map kidneyPanel);

    Liver getLiverMin(Map liverPanel);

    Liver getLiverMax(Map liverPanel);
}
