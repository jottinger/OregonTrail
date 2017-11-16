/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.InventoryControl;
import byui.cit260.oregonTrail.control.RiverControl;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;
import java.util.Scanner;
import oregonTrail.OregonTrail;

/**
 *
 * @author jordan
 */
public class RiverMenuView extends View {
    private String menu;
    private String promptMessage;
    
    public RiverMenuView() {
        super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| River Menu                                       |"
                    +"\n----------------------------------------------------"
                    +"\nF - Ford the river"
                    +"\nH - Hire a Guide for $10"
                    +"\nS - Save game"
                    +"\nQ - Quit"
                    +"\n----------------------------------------------------");
    }
    
    @Override
    public boolean doAction(String choice) {
        choice = choice.toUpperCase(); //convert choice to upper case
        
        switch (choice) {
            case "F": //ford the river
                this.fordRiver();
                break;
            case "H": //hire a guide
                this.hireGuide();
                break;
            case "S": //save the current game
                this.saveGame();
                break;
            case "Q": //quit the menu
                this.quitGame();
                break;
            default:
                System.out.println("\n*** Invalid selection *** Try again");
        }
        
        return false;
    }

    private void fordRiver() {
        //ford the river
        int riverHeight = getRiverHeight();
        InventoryItem[] inventory = OregonTrail.getCurrentGame().getInventory();
        double guide = inventory[InventoryType.Guide.ordinal()].getQuantityInStock();
        long currentRiverWeather = getRiverWeather();
        int success = success = RiverControl.calcRiverSuccessProbability(riverHeight, guide, currentRiverWeather);
        if (success == 0) {
            System.out.println("\n*************************************************"
                          + "\n| Your attempt to cross the river failed."
                          + "\n| You have lost 20% of your inventory."
                          + "\n************************************************");
            
            String afterInventory = InventoryControl.riverFailureRemove(inventory);
            System.out.print(afterInventory);
            this.display();
        } else if (success == 1) {
            System.out.println("\n*************************************************"
                          + "\n| Congratulations! "
                          + "\n| Your attempt to cross the river succeeded."
                          + "\n************************************************");
            GameMenuView gameMenuView = new GameMenuView();
            gameMenuView.display();
        }
        
    }
    
    private void saveGame() {
        System.out.println("*** saveGame() function called ***");
    }

    private void quitGame() {
        System.out.println("*** quitGame() function called ***");
    }

    private void hireGuide() {
        GuideMenuView guideView = new GuideMenuView();
        guideView.display();
    }

    private int getRiverHeight() {
        System.out.println("*** getRiverHeight() function called ***");
        int height = 0;
        return height;
    }

    private long getRiverWeather() {
        System.out.println("*** getRiverWeather() function called ***");
        long weather = 0;
        return weather;
    }

}
