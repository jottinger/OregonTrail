/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.InventoryControl;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.Location;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class GameMenuView extends View{
    
    // constructor function
    public GameMenuView() {
        super("\n"
                +"\n----------------------------------------------------"
                    +"\n| Game Menu                                        |"
                    +"\n----------------------------------------------------"
                    +"\nB - Barter"
                    +"\nC - Change Pace"
                    +"\nF - Ford River"
                    +"\nG - Hire Guide"
                    +"\nV - View Inventory"
                    +"\nH - Hunt"
                    +"\nP - Purchase Goods"
                    +"\nM - Travel to Next Location"
                    +"\nQ - Quit"
                    +"\n----------------------------------------------------"
                    +"\n"
                    +"\n Please enter your choice:");
   
    }
   @Override
   public boolean doAction(String value) {
       value = value.toUpperCase();
       
       switch (value) {
           case "B":
               BarterView barterView = new BarterView();
               barterView.display();
               break;
           case "C":
               ChangePaceView changePaceView = new ChangePaceView();
               changePaceView.display();
               break;
           case "F":
               RiverMenuView riverMenuView = new RiverMenuView();
               riverMenuView.display();
               break;
           case "G":
               GuideMenuView guideMenuView = new GuideMenuView();
               guideMenuView.display();
               break;
           case "V":
               InventoryView inventoryView = new InventoryView();
               inventoryView.display();
               break;
           case "H":
               HuntView huntView = new HuntView();
               huntView.display();
               break;
           case "P":
               String inventory = "";

       {
           try {
               inventory = InventoryControl.displayInventoryQuantityPrice();
           } catch (InventoryControlException ex) {
               System.out.println(ex.getMessage());
           }
       }

               PurchaseGoodsView purchaseGoodsView;
       try {
           purchaseGoodsView = new PurchaseGoodsView(inventory);
           purchaseGoodsView.display();
       } catch (InventoryControlException ex) {
           System.out.println(ex.getMessage());
       } 
               break;
           case "M":
               this.displayMap();
               break;
           default:
               System.out.println("*** Error: invalid choice entered. Try again. ***");
               
               
           
       } return false;
   }

    private void displayMap() {
        //game = get the currentGame from the main class
        Game game = OregonTrail.getCurrentGame();
        //locations = get the 2-D locations array from the map
        Location[][] locations = game.getMap().getLocations();
        //Print the title
        int i = 1;
        System.out.println(  "\n*******************************************"
                            +"\n*            The Oregon Trail             *"
                            +"\n*-----------------------------------------*"
                            +"\n      1       2       3       4       5    ");
        for (Location[] row : locations) {
            System.out.print("\n*-----------------------------------------*"
                              + "\n" + i + " " );
            i++;
        
            for (Location location : row){
                System.out.print("|   " + location.getSymbol() + "   ");
                
            }
        }       
        System.out.println("\n*******************************************");
        MapView mapview = new MapView();
        mapview.display();
        
        //DisplayMapView displayMapView = new DisplayMapView();
        //displayMapView.display();
    }
}

