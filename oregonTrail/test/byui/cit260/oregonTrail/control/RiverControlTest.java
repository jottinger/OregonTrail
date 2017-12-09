/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;

import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.exceptions.RiverControlException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jordan
 */
public class RiverControlTest {
    
    public RiverControlTest() {
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
     * Test of calcRiverSuccessProbability method, of class RiverControl.
     */
    @Test
    public void testCalcRiverSuccessProbability() throws RiverControlException, MapControlException {
        System.out.println("calcRiverSuccessProbability");
        
        /********************
         * Test case #1
         ********************/
        System.out.println("\tTest case #1");
        int riverHeight = 30;
        int guide = 1;
        RiverControl instance = new RiverControl();
        int expResult = -1;        int result = instance.calcRiverSuccessProbability(riverHeight, guide);
        assertEquals(expResult, result);
        
        /********************
         * Test case #2
         ********************/
        System.out.println("\tTest case #2");
        riverHeight = 21;
        guide = 0;
        expResult = -1;
        result = instance.calcRiverSuccessProbability(riverHeight, guide);
        assertEquals(expResult, result);
        
        /********************
         * Test case #3
         ********************/
        System.out.println("\tTest case #3");
        riverHeight = 10;
        guide = -1;
        expResult = -1;
        result = instance.calcRiverSuccessProbability(riverHeight, guide);
        assertEquals(expResult, result);
        
        /********************
         * Test case #4
         ********************/
        System.out.println("\tTest case #4");
        riverHeight = 5;
        guide = 1;
        expResult = 0;
        result = instance.calcRiverSuccessProbability(riverHeight, guide);
        assertEquals(expResult, result);
        
        /********************
         * Test case #5
         ********************/
        System.out.println("\tTest case #5");
        riverHeight = 2;
        guide = 0;
        expResult = 0;
        result = instance.calcRiverSuccessProbability(riverHeight, guide);
        assertEquals(expResult, result);
        
        /********************
         * Test case #6
         ********************/
        System.out.println("\tTest case #6");
        riverHeight = 9;
        guide = 1;
        expResult = 1;
        result = instance.calcRiverSuccessProbability(riverHeight, guide);
        assertEquals(expResult, result);
        
        /********************
         * Test case #7
         ********************/
        System.out.println("\tTest case #7");
        riverHeight = 20;
        guide = 0;
        expResult = 0;
        result = instance.calcRiverSuccessProbability(riverHeight, guide);
        assertEquals(expResult, result);
    }
    
}
