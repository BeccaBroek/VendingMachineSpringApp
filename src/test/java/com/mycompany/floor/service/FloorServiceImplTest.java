/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.service;

import com.mycompany.floor.dao.FloorOrderDao;
import com.mycompany.floor.dao.FloorOrderDaoStubImpl;
import com.mycompany.floor.dao.FloorProductDao;
import com.mycompany.floor.dao.FloorProductDaoImpl;
import com.mycompany.floor.dao.FloorStateDao;
import com.mycompany.floor.dao.FloorStateDaoImpl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Becca
 */
public class FloorServiceImplTest {

    FloorService service;
    FloorOrderDao orderDao;

    public FloorServiceImplTest() {
        orderDao = new FloorOrderDaoStubImpl();
        FloorStateDao stateDao = new FloorStateDaoImpl();
        FloorProductDao productDao = new FloorProductDaoImpl();
        service = new FloorServiceImpl(orderDao, stateDao, productDao);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of load method, of class FloorServiceImpl.
     */
    @Test
    public void testLoad() throws Exception {
    }

    /**
     * Test of getByDate method, of class FloorServiceImpl.
     */
    @Test
    public void testGetByDate() throws Exception {
        assertTrue(service.getByDate("06092017").size() == 2);
    }

    /**
     * Test of addOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
        LocalDate ld = LocalDate.now();
        String orderDate = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        int sizeOriginal = 0;
        service.addOrder("Paul", "PA", "Wood", "15");
        int sizeAfter = service.getByDate(orderDate).size();
        assertTrue(sizeOriginal == sizeAfter - 1);
    }

    /**
     * Test of check method, of class FloorServiceImpl.
     */
    @Test(expected = InvalidInputException.class)
    public void testCheckNameInvalid() throws Exception {
        service.checkName("Rebecca1");
    }

    @Test
    public void testCheckNameValid() throws Exception {
        boolean throwsException = false;
        try {
            service.checkName("Rebecca");
        } catch (InvalidInputException e) {
            throwsException = true;
        }
        assertFalse(throwsException);
        throwsException = false;
        try {
            service.checkName("");
        } catch (InvalidInputException e) {
            throwsException = true;
        }
        assertFalse(throwsException);
    }

    /**
     * Test of checkState method, of class FlooringService.
     */
    @Test
    public void testCheckStateValid() throws Exception {
        boolean throwsException = false;
        try {
            service.checkState("PA");
            service.checkState("OH");
            service.checkState("MI");
            service.checkState("IN");
            service.checkState("");
        } catch (InvalidInputException e) {
            throwsException = true;
        }
        assertFalse(throwsException);
    }

    @Test(expected = InvalidInputException.class)
    public void testCheckStateInValid() throws Exception {
        service.checkState("4");
        service.checkState("LL");
    }

    /**
     * Test of checkProductType method, of class FlooringService.
     */
    @Test
    public void testCheckProductTypeValid() throws Exception {
        boolean throwsException = false;
        try {
            service.checkProductType("Wood");
            service.checkProductType("Laminate");
            service.checkProductType("Carpet");
            service.checkProductType("Tile");
            service.checkProductType("");

        } catch (InvalidInputException e) {
            throwsException = true;
        }
        assertFalse(throwsException);
    }

    @Test(expected = InvalidInputException.class)
    public void testCheckProductTypeInvalid() throws Exception {
        service.checkState("4");
        service.checkState("LL");
        service.checkState("grass");
    }

    /**
     * Test of checkArea method, of class FlooringService.
     */
    @Test
    public void testCheckAreaValid() throws Exception {
        boolean throwsException = false;
        try {
            service.checkArea("1");
            service.checkArea("100.7");

        } catch (InvalidInputException e) {
            throwsException = true;
            System.out.println("area isnt valid, expected to be valid");
        }
        assertFalse(throwsException);
    }

    @Test(expected = InvalidInputException.class)
    public void testCheckAreaInvalid() throws Exception {
        service.checkArea("0");
        service.checkArea("LL");
        service.checkArea("-1");
    }

    /**
     * Test of save method, of class FloorServiceImpl.
     */
    @Test
    public void testSave() throws Exception {
    }

    /**
     * Test of checkForOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testCheckForOrder() throws Exception {
        service.checkForOrder("06092017", "1");
    }

    @Test
    public void testCheckForOrderInvalid() throws Exception {
        try {
            service.checkForOrder("06092014", "1");
            fail("Expected exception, test threw no exception");
        } catch (FileNotFoundException | NullPointerException e) {
        }
    }

    /**
     * Test of getOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testGetOrder() {
        service.getOrder("1", "06092017");
    }

    /**
     * Test of editOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        service.getOrder("1", "06092017").setCustomerName("Fred");
        service.getOrder("1", "06092017").setCustomerState("PA");
        service.getOrder("1", "06092017").setProductType("Carpet");
        service.getOrder("1", "06092017").setArea("13");

        service.editOrder("1", "06092017", "Bob", "MI", "Wood", "17");
        assertEquals(service.getOrder("1", "06092017").getCustomerName(), "Bob");
        assertEquals(service.getOrder("1", "06092017").getCustomerState(), "MI");
        assertEquals(service.getOrder("1", "06092017").getProductType(), "Wood");
        assertEquals(service.getOrder("1", "06092017").getArea(), "17");
    }

    /**
     * Test of removeOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        service.removeOrder("06092017", "1");
    }

    @Test
    public void testRemoveOrderInvalid() throws Exception {
        try {
            service.removeOrder("06092009", "1");
            fail("expected exception to be thrown, testRemoveOrderInvalid");
        } catch (IOException e) {
        }

    }
}

