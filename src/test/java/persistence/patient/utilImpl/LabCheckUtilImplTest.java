package persistence.patient.utilImpl;

import org.junit.Assert;
import org.junit.Test;
import persistence.patient.model.LabCheck;
import persistence.patient.util.LabCheckUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LabCheckUtilImplTest {

    @Test
    public void fetchLabCheckPlans() {
        LabCheck labCheck1 = new LabCheck();
        labCheck1.setCheckupId(1);
        labCheck1.setCheckupName("BloodTest");
        labCheck1.setCheckupType("L");
        labCheck1.setDescription("CBC,CMP,BMP");
        labCheck1.setCharges(500);

        LabCheck labCheck2 = new LabCheck();
        labCheck2.setCheckupId(2);
        labCheck2.setCheckupName("ChildHealthCheck");
        labCheck2.setCheckupType("H");
        labCheck2.setDescription("Forchildren less than 10 years");
        labCheck2.setCharges(1500);
        List<LabCheck> labCheckList = Arrays.asList(labCheck1, labCheck2);

        LabCheckUtil labCheckUtil = new LabCheckUtilImpl();
        Map<Integer, LabCheck> labCheckMap = labCheckUtil.fetchLabCheckMap(labCheckList);

        Assert.assertTrue(labCheckMap.containsKey(1));
        Assert.assertTrue(labCheckMap.containsKey(2));
    }
}