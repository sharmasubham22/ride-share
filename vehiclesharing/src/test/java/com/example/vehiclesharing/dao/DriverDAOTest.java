package com.example.vehiclesharing.dao;
import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.IDriver;
import com.example.vehiclesharing.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
public class DriverDAOTest {

    @Autowired
    DriverDAO driverDAO;

    @Autowired
    IDriver idriver;

    @Test
    void testSaveDriverCorrect() {
        User user = new User();
        user.setFirst_name("test2");
        user.setLast_name("case");
        user.setEmail("test@case.com");
        user.setPassword("1234563");
        user.setCredits(220);
        user.setUserType("DRIVER");
        //Driver d= idriver.convertObject();
        Driver d=new Driver(user.getFirst_name(), user.getLast_name(), user.getEmail(),user.getPassword());
        assertTrue(driverDAO.save(d));
    }

    @Test
    void testExtractDriverCorrectByEmail(){
        assertNotNull(driverDAO.getObjectByEmail("test@case.com"));
    }

    @Test
    void testExtractDriverByEmailIncorrect(){
        assertNull(driverDAO.getObjectByEmail("test2@case.com"));
    }

    @Test
    void testExtractDriverCorrectById(){
        assertNotNull(driverDAO.getObjectById(1));
    }

    @Test
    void testExtractDriverByIdIncorrect(){
        assertNull(driverDAO.getObjectById(100));
    }


    @Test
    void testUpdateDriverCorrect(){
        assertTrue(driverDAO.updateObject("test@case.com", "driver_credits", 100));

    }

    @Test
    void removeByIdCorrect(){
        assertTrue(driverDAO.removeObject(1));

    }

    @Test
    void getObjectListCorrect(){
        assertTrue(driverDAO.getObjectsList()!=null);
    }







}