/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.Location;
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
               System.out.println("*** HireGuideView() function called ***");
               //HireGuideView hireGuideView = new HireGuideView();
               //hireGuideView.display();
               break;
           case "V":
               System.out.println("*** InventoryView() function called ***");
               //InventoryView inventoryView = new InventoryView();
               //inventoryView.display();
               break;
           case "H":
               System.out.println("*** HuntView() function called ***");
               HuntView huntView = new HuntView();
               huntView.display();
               break;
           case "P":
               System.out.println("*** PurchaseGoodsView() function called ***");
               //PurchaseGoodsView purchaseGoodsView = new PurchaseGoodsView();
               //purchaseGoodsView.display();
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
                
        //Print the column numbers for each column
        //for every row in map
        //Print a row divider
        //Print the row number on a new line
        //for every column in the row
        //Print a column divider
        //location = locations[row][column]
        //if location has been visited
        //Get the mapSymbol for the scene in this location
        //Print the mapSymbol
        //else
        //Print " ?? "
        //endif
        //endFor
        //Print the ending column divider
        //endFor
        //Print the ending row divider
        
        //DisplayMapView displayMapView = new DisplayMapView();
        //displayMapView.display();
    }
}

