package persistence.patient.daoImpl;

import org.junit.Test;
import static org.junit.Assert.*;

import persistence.patient.utilImpl.DoctorRecommendationUtilImpl;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorRecommendationDAOImplTest {

    @Test
    public void fetchDoctorList() throws SQLException {

        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.getConnection();
        DoctorRecommendationDAOImpl doctorRecommendationDAOImpl = new DoctorRecommendationDAOImpl();

        ArrayList<Integer> doctorIDList = new ArrayList<>();
        doctorIDList.add(2);
        doctorIDList.add(2);
        doctorIDList.add(2);
        doctorIDList.add(2);
        doctorIDList.add(2);
        doctorIDList.add(2);
        doctorIDList.add(3);
        doctorIDList.add(3);
        doctorIDList.add(3);
        doctorIDList.add(3);
        doctorIDList.add(4);
        doctorIDList.add(4);
        doctorIDList.add(4);
        doctorIDList.add(4);
        doctorIDList.add(6);
        doctorIDList.add(6);
        doctorIDList.add(6);

        assertEquals(doctorIDList, doctorRecommendationDAOImpl.fetchDoctorList("Cough"));

    }

    @Test
    public void fetchDoctorList2() throws SQLException {

        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.getConnection();
        DoctorRecommendationDAOImpl doctorRecommendationDAOImpl = new DoctorRecommendationDAOImpl();

        ArrayList<Integer> doctorIDList = new ArrayList<>();
        doctorIDList.add(3);
        doctorIDList.add(3);
        doctorIDList.add(3);

        assertEquals(doctorIDList, doctorRecommendationDAOImpl.fetchDoctorList("Cold"));

    }

    @Test
    public void fetchIncorrectDoctorList() throws SQLException {

        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.getConnection();
        DoctorRecommendationDAOImpl doctorRecommendationDAOImpl = new DoctorRecommendationDAOImpl();

        ArrayList<Integer> doctorIDList = new ArrayList<>();
        doctorIDList.add(3);
        doctorIDList.add(3);
        doctorIDList.add(3);
        doctorIDList.add(3);

        assertNotEquals(doctorIDList, doctorRecommendationDAOImpl.fetchDoctorList("Cold"));

    }

    @Test
    public void fetchIncorrectDoctorList2() throws SQLException {

        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.getConnection();
        DoctorRecommendationDAOImpl doctorRecommendationDAOImpl = new DoctorRecommendationDAOImpl();

        ArrayList<Integer> doctorIDList = new ArrayList<>();
        doctorIDList.add(3);
        doctorIDList.add(3);
        doctorIDList.add(3);
        doctorIDList.add(3);

        assertNotEquals(doctorIDList, doctorRecommendationDAOImpl.fetchDoctorList("Cough"));

    }

    @Test
    public void fetchIncorrectDoctorList3() throws SQLException {

        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.getConnection();
        DoctorRecommendationDAOImpl doctorRecommendationDAOImpl = new DoctorRecommendationDAOImpl();

        ArrayList<Integer> doctorIDList = new ArrayList<>();

        assertNotEquals(doctorIDList, doctorRecommendationDAOImpl.fetchDoctorList("Cough"));

    }

    @Test
    public void fetchIncorrectDoctorList4() throws SQLException {

        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.getConnection();
        DoctorRecommendationDAOImpl doctorRecommendationDAOImpl = new DoctorRecommendationDAOImpl();

        ArrayList<Integer> doctorIDList = new ArrayList<>();

        assertNotEquals(null, doctorRecommendationDAOImpl.fetchDoctorList("Diabetes"));

    }


    /* Input validation test cases for getDoctorName() method start here */

    @Test
    public void getDoctorName_IV1() {

        /* Input validations for passed parameter */
        /* Incorrect inputs */

        DatabaseConnection.loadDatabaseConnection();
        Connection conn = DatabaseConnection.getConnection();
        DoctorRecommendationDAOImpl doctorRecommendationDAOImpl = new DoctorRecommendationDAOImpl();
        ArrayList<Integer> doctorIDList = new ArrayList<>();

        /* recf is null */
        assertEquals(null, doctorRecommendationDAOImpl.getDoctorName(null));

        /* recf is empty */
        assertEquals(null, doctorRecommendationDAOImpl.getDoctorName(doctorIDList));

    }

}
