/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;

import byui.cit260.oregonTrail.exceptions.HuntControlException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Dresen_HP
 */
public class HuntControlTest {
    
    public HuntControlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of calcFoodWeight method, of class HuntControl.
     */
    @Test
    public void testCalcFoodWeight() throws HuntControlException {
        System.out.println("calcFoodWeight");
        
        /********************
         * Test case #1
         ********************/
        System.out.println("\tTest case #1");
        int baseWeight1 = 15;

        int guide = 0;
        HuntControl instance = new HuntControl();
        double expResult = 100;
        double result = instance.calcFoodWeight(baseWeight1, guide);
        assertEquals(expResult, result, 0.0001); 
        
        /********************
         * Test case #2
         ********************/
        System.out.println("\tTest case #2");
        baseWeight1 = 200;
        
        guide = 1;
        expResult = 200;
        result = instance.calcFoodWeight(baseWeight1, guide);
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #3
         ********************/
        System.out.println("\tTest case #3");
        baseWeight1 = 15;
        
        guide = 0;
        expResult = 45;
        result = instance.calcFoodWeight(baseWeight1,  guide);
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #4
         ********************/
        System.out.println("\tTest case #4");
        baseWeight1 = 500;
        
        guide = 3;
        expResult = -1;
        result = instance.calcFoodWeight(baseWeight1, guide);
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #5
         ********************/
        System.out.println("\tTest case #5");
        baseWeight1 = -1;
        
        guide = 1;
        expResult = -1;
        result = instance.calcFoodWeight(baseWeight1,  guide);
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #6
         ********************/
        System.out.println("\tTest case #6");
        baseWeight1 = 200;
        
        guide = 1;
        expResult = -1;
        result = instance.calcFoodWeight(baseWeight1, guide);
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #7
         ********************/
        System.out.println("\tTest case #7");
        baseWeight1 = 0;
        guide = 0;
        expResult = 0;
        result = instance.calcFoodWeight(baseWeight1 , guide);
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #8
         ********************/
        System.out.println("\tTest case #2");
        baseWeight1 = 0;
        guide = 1;
        expResult = 0;
        result = instance.calcFoodWeight(baseWeight1 , guide);
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #9
         ********************/
        System.out.println("\tTest case #9");
        baseWeight1 = 0;
        guide = 1;
        expResult = 50;
        result = instance.calcFoodWeight(baseWeight1 , guide);
        assertEquals(expResult, result, 0.0001);
 
    
}

    /**
     * Test of calcHuntingSuccessProbability method, of class HuntControl.
     */
    @Test
    public void testCalcHuntingSuccessProbability() {
        System.out.println("calcHuntingSuccessProbability");
       
        /********************
         * Test case #1
         ********************/
        System.out.println("\tTest case #1");
        String difficulty = "Hard";
        int startDate = 5;
        int travelDays = 1;
        HuntControl instance = new HuntControl();
        double expResult = 0.2;
        double result = instance.calcHuntingSuccessProbability(difficulty, startDate, travelDays);
        assertEquals(expResult, result, 0.0001);

        
        
        /********************
         * Test case #2
         ********************/
        System.out.println("\tTest case #2");
        difficulty = "Medium";
        startDate = 10;
        travelDays = 45;
        expResult = 0.25;
        result = instance.calcHuntingSuccessProbability(difficulty, startDate, travelDays);
        assertEquals(expResult, result, 0.0001);
        
        
        /********************
         * Test case #3
         ********************/
        System.out.println("\tTest case #2");
        difficulty = "Easy";
        startDate = 1;
        travelDays = 90;
        expResult = 0.4;
        result = instance.calcHuntingSuccessProbability(difficulty, startDate, travelDays);
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #4
         ********************/
        System.out.println("\tTest case #2");
        difficulty = "OK";
        startDate = 1;
        travelDays = -5;
        expResult = -1;
        result = instance.calcHuntingSuccessProbability(difficulty, startDate, travelDays);
        assertEquals(expResult, result, 0.0001);
        
        /********************
         * Test case #5
         ********************/
        System.out.println("\tTest case #2");
        difficulty = "Easier";
        startDate = 5;
        travelDays = -4;
        expResult = -1;
        result = instance.calcHuntingSuccessProbability(difficulty, startDate, travelDays);
        assertEquals(expResult, result, 0.0001);
        
        
        /********************
         * Test case #6
         ********************/
        System.out.println("\tTest case #2");
        difficulty = "Fifty";
        startDate = 12;
        travelDays = -2;
        expResult = -1;
        result = instance.calcHuntingSuccessProbability(difficulty, startDate, travelDays);
        assertEquals(expResult, result, 0.0001); 
    }}
    
        
    
        
        
     
    
    
