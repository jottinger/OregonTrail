/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;


import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.Animal;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Dresen_HP
 */
public class HuntControl {
    public int calcFoodWeight(int baseWeight, int guide) {
        // validate inputs
        if (baseWeight < 0) {
            return -1;
        }

        if (guide != 0 && guide != 1) {
            return -1;
        }
        
        // calculations
        int yield = baseWeight;
        int weight;
        if (guide == 0) {
            weight = yield - 100;
            if (weight > 0) {
                yield = 100;
            }
        }
        if (guide == 1) {
            weight = yield - 200;
            if (weight > 0) {
                yield = 200;
            }
        }
        return yield;
    }

    public double calcHuntingSuccessProbability(String name, int startDate, int travelDays ) {
            //validate inputs
            //if ( difficulty != "Hard" && difficulty != "Medium" && difficulty != "Easy") {
               // return -1;
            //}
            
            if (name != "Bison" && name != "Wolf" && name != "Bear" && name != "Rabbit") {
                return -1;
            }
          
   int randomNum = 0;
   double huntSuccess = 0;
   long month = 0;
   int sd = startDate;
   int dt = travelDays;
   double currentHuntWeatherModifier = 0;
   double difficultyModifier = 0;
   Random random = new Random();
   int low = 1;
   int high = 100;
   int randomNumber = random.nextInt(high-low)+low;
   
            
            
    //calculate month

    if ((sd + dt)/30 > 12) {
        month = (sd % dt)/12;
    }
    else {
        month = (sd + dt)/30;
    }
     
    //calculate currentHuntWeather using month
    
    if (month >= 0 && month <= 2) {
        currentHuntWeatherModifier = 20;
    }
    if (month >= 3 && month <= 4) {
        currentHuntWeatherModifier = 15;
    }
    if (month >= 5 && month <= 8) {
        currentHuntWeatherModifier = -10;
    }
    if (month >= 9 && month <= 11) {
        currentHuntWeatherModifier = 15;
    }
    if (month >= 11 && month <= 12) {
        currentHuntWeatherModifier = 20;
    }
    
    //calculate difficultyModifier by turning string into number
    
    if (name == "Bear" || name == "Wolf") {
        difficultyModifier = 70;
    }
    if (name == "Bison") {
        difficultyModifier = 50;
    }
    if (name == "Rabbit") {
        difficultyModifier = 30;
    }
       
   
    
    double chanceOfSuccess = (difficultyModifier + currentHuntWeatherModifier);
    
    if (randomNumber >= chanceOfSuccess) {
        huntSuccess = 0;
    }
    
    if (randomNumber <= chanceOfSuccess) {
        huntSuccess = 1;
    }
    
    return huntSuccess;
    
    
    }   
    
    
  
    
}
