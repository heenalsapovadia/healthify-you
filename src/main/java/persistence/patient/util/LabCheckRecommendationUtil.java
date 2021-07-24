package persistence.patient.util;

import persistence.patient.model.LabCheck;
import java.util.List;

public interface LabCheckRecommendationUtil {
    List<LabCheck> genderBasedRecommendation();

    List<LabCheck> ageBasedRecommendation();

    List<LabCheck> historyBasedRecommendation();
}
