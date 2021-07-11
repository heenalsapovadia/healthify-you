package persistence.patient.daoImpl;

import org.junit.Test;
import persistence.patient.dao.LabCheckDao;
import persistence.patient.model.LabCheck;

import java.util.List;

import static org.junit.Assert.*;

public class LabCheckDaoImplTest {

    @Test
    public void getAvailablePlans() {
        LabCheckDao labCheckDao = new LabCheckDaoImpl();
        List<LabCheck> labCheckList = labCheckDao.getAvailablePlans();
        assertEquals(10, labCheckList.size());
    }
}