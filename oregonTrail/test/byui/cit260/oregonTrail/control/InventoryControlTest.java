/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;

import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dresen_HP
 */
public class InventoryControlTest {
    
    public InventoryControlTest() {
    }


    /**
     * Test of calcBarterPrice method, of class InventoryControl.
     */
    @Test
    public void testCalcBarterPrice() {
        
        System.out.println("calcBarterPrice");
        /********************
         * Test case #1
         *******************/
        System.out.println("\tTestCase #1");
        
        // input values for test case 1
        int basePriceGet = 20;
        int basePriceGive = 5;
        double percentComplete = 0.5;
        int barterCoefficient = 1;
        // expected results
        double expResult = 6.0;
        // create new instance of Inventory Control Class
        InventoryControl instance = new InventoryControl();
        // run test case and get result
        double result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #2
         *******************/
        System.out.println("\tTestCase #2");
        // input values for test case 2
        basePriceGet = 5;
        basePriceGive = 20;
        percentComplete = 0.3;
        barterCoefficient = 2;
        // expected results
        expResult = 1.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #3
         *******************/
        System.out.println("\tTestCase #3");
        // input values for test case 3
        basePriceGet = -5;
        basePriceGive = 20;
        percentComplete = 0.4;
        barterCoefficient = 1;
        // expected results
        expResult = -1.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #4
         *******************/
        System.out.println("\tTestCase #4");
        // input values for test case 4
        basePriceGet = 20;
        basePriceGive = -5;
        percentComplete = 0.4;
        barterCoefficient = 2;
        // expected results
        expResult = -1.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #5
         *******************/
        System.out.println("\tTestCase #5");
        // input values for test case 5
        basePriceGet = 20;
        basePriceGive = 5;
        percentComplete = 2;
        barterCoefficient = 1;
        // expected results
        expResult = -1.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #6
         *******************/
        System.out.println("\tTestCase #6");
        // input values for test case 6
        basePriceGet = 20;
        basePriceGive = 5;
        percentComplete = 0.8;
        barterCoefficient = 3;
        // expected results
        expResult = -1.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #7
         *******************/
        System.out.println("\tTestCase #7");
        // input values for test case 7
        basePriceGet = 1;
        basePriceGive = 20;
        percentComplete = 0.1;
        barterCoefficient = 1;
        // expected results
        expResult = 1.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #8
         *******************/
        System.out.println("\tTestCase #8");
        // input values for test case 8
        basePriceGet = 20;
        basePriceGive = 1;
        percentComplete = 0.2;
        barterCoefficient = 2;
        // expected results
        expResult = 44.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #9
         *******************/
        System.out.println("\tTestCase #9");
        // input values for test case 9
        basePriceGet = 20;
        basePriceGive = 5;
        percentComplete = 0.0;
        barterCoefficient = 1;
        // expected results
        expResult = 4.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #10
         *******************/
        System.out.println("\tTestCase #10");
        // input values for test case 10
        basePriceGet = 5;
        basePriceGive = 20;
        percentComplete = 1.0;
        barterCoefficient = 2;
        // expected results
        expResult = 1.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #11
         *******************/
        System.out.println("\tTestCase #11");
        // input values for test case 11
        basePriceGet = 20;
        basePriceGive = 5;
        percentComplete = 0.3;
        barterCoefficient = 1;
        // expected results
        expResult = 6.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #12
         *******************/
        System.out.println("\tTestCase #12");
        // input values for test case 12
        basePriceGet = 20;
        basePriceGive = 20;
        percentComplete = 0.9;
        barterCoefficient = 2;
        // expected results
        expResult = 3.0;
        // run test case and get result
        result = instance.calcBarterPrice(basePriceGet, basePriceGive, percentComplete, barterCoefficient);
        // compare results to expected results
        assertEquals(expResult, result, 0.0001);
    }


    /**
     * Test of riverFailureRemove method, of class InventoryControl.
     */
    /*@Test
    public void testRiverFailureRemove() {
        System.out.println("riverFailureRemove");
        InventoryItem[] inventory = null;
        InventoryItem
        InventoryItem[] expResult = ;
        String result = InventoryControl.riverFailureRemove(inventory);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */




}
