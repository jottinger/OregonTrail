/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;

import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.exceptions.RiverControlException;
import byui.cit260.oregonTrail.model.Location;
import byui.cit260.oregonTrail.model.RiverScene;
import java.util.Random;
import oregonTrail.OregonTrail;

/**
 *
 * @author Group 3
 * 
 */
public class RiverControl {
    
    public static int calcRiverSuccessProbability(int riverHeight, double guide)
                        throws RiverControlException, MapControlException {
    
    //validate inputs
    if (riverHeight < 0) {
            throw new RiverControlException("River hieght of " + riverHeight +" too high.");
        }
    if (guide != 0 && guide != 1) {
            throw new RiverControlException("Guide selection must be made. Go back to "
                                          + "previous menu and make a guide choice"
                                          + " selection.");
        }
    /*
    if (currentRiverWeather < -2) {
            throw new RiverControlException("Current weather not found. "
                                          + "Unable to continue.");
        }
    */
    
    if (riverHeight > 20) {
        throw new RiverControlException("River height cannot be above 20. Your "
                                      + "selection of " + riverHeight + "is too "
                                      + "high. Make another selection");
    }
        
   //declare all the variables
   int max = 100;
   int min = 1;
   int randomNum = 0;
   int chanceOfSuccess = 0;
   long month = 0;
   int currentWeatherModifier = 0;
   int adjustedRiverHeight = 0;
   int guideModifier = 0;
   int success = 1;

  
   //create random number
    Random rand = new Random();
    int low = 1;
    int high = 100;
    int randomNumber = rand.nextInt(high - low) + low;
   
   //get RiverHeight
   //riverHeight = 10; //TODO: generate a random number
   
   //calculate month

   int sd = OregonTrail.getCurrentGame().getStartDate();
   int dt = OregonTrail.getCurrentGame().getTravelDays();
   
   if (dt < 1){
       dt = 1;
   }

   
   //int sd = 60;
   //int dt = 94;


    if ((sd + dt)/30 > 12) {
        month = (sd % dt)/12;
    }
    else {
        month = (sd + dt)/30;
    }
        
    //calculate currentWeatherModifier using the current month (colder weather lowers the riverHieght)       
    if (month >= 0 && month <= 2) {
        currentWeatherModifier = -2;
    }
    if (month >= 3 && month <= 4) {
        currentWeatherModifier = -1;
    }
    if (month >= 5 && month <= 8) {
        currentWeatherModifier = 0;
    }
    if (month >= 9 && month <= 11) {
        currentWeatherModifier = 1;
    }
    if (month >=11 && month <= 12) {
        currentWeatherModifier = -2;
    }
    
   
    //set river height
    adjustedRiverHeight = riverHeight + currentWeatherModifier;
    
    //do you get a bonus for hiring a guide?
    if (guide == 0) {
        guideModifier = 0;
    }
    
    if (guide == 1) {
        guideModifier = 50;
    }
    
    chanceOfSuccess = guideModifier + adjustedRiverHeight;
    
    if (randomNumber <= chanceOfSuccess) {
        success = 1;
    }
    
    if (randomNumber >= chanceOfSuccess) {
        success = 0;
    }
    
   //return chanceOfSuccess
   return success;
}



}
