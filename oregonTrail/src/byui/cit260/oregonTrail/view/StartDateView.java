/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import java.util.Scanner;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class StartDateView extends View{
    
    // constructor function called from displayNextView() in StartGameView
    public StartDateView() {
        super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Choose the day to start traveling                |" 
                    +"\n| Choice will affect weather and conditions        |"
                    +"\n----------------------------------------------------"
                    +"\nM - March 1"
                    +"\nJ - June 1"
                    +"\nS - September 1"
                    +"\nQ - Quit"
                    +"\n----------------------------------------------------"
                    +"\n"
                    +"\nPlease enter your choice.");
    }

    @Override
    public boolean doAction(String value) {
        value = value.toUpperCase();
        int startDate = 1;
        
        switch (value) {
            case "M":
                startDate = 61;
                this.saveStartDate(startDate);
                break;
            case "J":
                startDate = 151;
                this.saveStartDate(startDate);
                break;
            case "S":
                startDate = 241;
                this.saveStartDate(startDate);
                break;
            default:
                ErrorView.display(this.getClass().getName(),"Error reading input: Invalid selection: Try again.");
                break;   
        }
        return false;
    } 

    private void displayNextView() {
        GameMenuView gameMenuView = new GameMenuView();
        gameMenuView.display();
        // TODO: needs to change to inventory view.  
        // Still need to add money to inventory and let user purchase supplies.
        // Still need to set pace.
    }

    private void saveStartDate(int startDate) {
        GameControl.setStartDate(startDate);
        this.findThisDay(startDate);
        
    }
    private void findThisDay(int startDate) {
        int travelDays = OregonTrail.getCurrentGame().getTravelDays();
        int monthDate = GameControl.findMonth(startDate, travelDays); 
        int day = GameControl.findDay(startDate, travelDays);
        String calDate = GameControl.thisDay(monthDate, day);
        this.console.println("\n*************************************************"
                          + "\n| StartDate: " + calDate
                          + "\n************************************************");
        this.displayNextView();
        
    }
}