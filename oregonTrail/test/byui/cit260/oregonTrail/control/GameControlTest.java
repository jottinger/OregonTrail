/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;

import byui.cit260.oregonTrail.exceptions.GameControlException;
import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.Occupation;
import byui.cit260.oregonTrail.model.Player;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dresen_HP
 */
public class GameControlTest {
    
    public GameControlTest() {
    }



    /**
     * Test of getMonth method, of class GameControl.
     */
    @Test
    public void testGetMonth() throws GameControlException {
        System.out.println("getMonth");
        /********************
         * Test case #1
         *******************/
        System.out.println("\tTestCase #1");
        int startDate = 31;
        int travelDays = 500;
        GameControl instance = new GameControl();
        int expResult = 6;
        int result = instance.findMonth(startDate, travelDays);
        assertEquals(expResult, result, 0.0001);
    
            /********************
         * Test case #2
         *******************/
        System.out.println("\tTestCase #2");
        startDate = 61;
        travelDays = 30;
        expResult = 4;
        result = instance.findMonth(startDate, travelDays);
        assertEquals(expResult, result);
           /********************
         * Test case #3
         *******************/
        System.out.println("\tTestCase #3");
        startDate = 0;
        travelDays = 132;
        expResult = 5;
        result = instance.findMonth(startDate, travelDays);
        assertEquals(expResult, result);
           /********************
         * Test case #4
         *******************/
        System.out.println("\tTestCase #4");
        startDate = -4;
        travelDays = 200;
        expResult = -1;
        result = instance.findMonth(startDate, travelDays);
        assertEquals(expResult, result);
           /********************
         * Test case #5
         *******************/
        System.out.println("\tTestCase #5");
        startDate = 141;
        travelDays = -1;
        expResult = -1;
        result = instance.findMonth(startDate, travelDays);
        assertEquals(expResult, result);
           /********************
         * Test case #6
         *******************/
        System.out.println("\tTestCase #6");
        startDate = 241;
        travelDays = 500;
        expResult = 1;
        result = instance.findMonth(startDate, travelDays);
        assertEquals(expResult, result);
           /********************
         * Test case #7
         *******************/
        System.out.println("\tTestCase #7");
        startDate = 1;
        travelDays = 0;
        expResult = 1;
        result = instance.findMonth(startDate, travelDays);
        assertEquals(expResult, result);
    }
}

    
