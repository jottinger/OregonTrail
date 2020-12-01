/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.MapControl;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;
import byui.cit260.oregonTrail.model.Location;
import byui.cit260.oregonTrail.model.Scene;
import byui.cit260.oregonTrail.model.SceneType;
import java.awt.Point;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
class MoveLocationView extends View {

    public MoveLocationView() {
        super("\nEnter the coordinates of the next stop on the trail - row,col"
                + "\nExample: Stop B is row 1, column2, so enter 1,2");
    }

    @Override
    public boolean doAction(String value) {
        // take entry of coordinates and spit into individual numbers and save in variables.
        String parts[] = value.split(",");
        String number1 = parts[0];
        int row = 0;
        int col = 0;
        try { // change first number string to number.
            row = parseInt(number1);
        } catch (NumberFormatException nf) {
            ErrorView.display(this.getClass().getName(), "Error reading input: "
                    + "You must enter a valid set of coordinates. \nTry again or enter Q to quit.");
            display();
        }
        // get second number string. If there is space before 2nd number, it is discarded.
        String number2 = parts[1];
        if (number2 == " ") {
            number2 = parts[2];
        }
        try { // change second number string to int.
            col = parseInt(number2);
        } catch (NumberFormatException nf) {
            ErrorView.display(this.getClass().getName(), "Error reading input: "
                    + "You must enter a valid set of coordinates. \nTry again or enter Q to quit.");
            display();
        }
        // subtract 1 from each number to account for 0 start.
        row -= 1;
        col -= 1;
        // create coordinates with two number inputs.
        Point coordinates = new Point(row, col);
        // create location variable.
        Location nextLocation;
        try {// check location to see if it is available to go to, then returns it.
            nextLocation = MapControl.checkLocation(coordinates);
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
            return false;
        }
        // get current location to get milesToNext
        Location currentLocation;
        try {
            currentLocation = MapControl.getCurrentLocation();
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
            return false;
        }
        // initialize miles
        int miles = 0;
        miles = currentLocation.getPlace().getMilesToNext();
        // get current pace
        int pace = 0;
        pace = OregonTrail.getCurrentGame().getPlayer().getPace();
        // calculate days needed to go to next location.
        int days = 0;
        try {
            days = MapControl.calcDaysNeeded(miles, pace);
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
            return false;
        }
        // figure how much food it will take to move.
        int food = 0;
        try {
            food = MapControl.calcFoodNeeded(days, pace);
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
            return false;
        }
        //Check food in stock to make sure there is enough to travel to next location.
        InventoryItem[] inventory = OregonTrail.getCurrentGame().getInventory();
        double quantity = inventory[InventoryType.Food.ordinal()].getQuantityInStock();
        GameMenuView gameMenuView = new GameMenuView();
        if (quantity < food) {
            ErrorView.display(value, "\nYou don't have enough food to make it to the next location."
                    + "\nYou need " + food + " food. \nObtain more food by buying food or hunting.");
            gameMenuView.display();

        } else {
            ConfirmMoveView confirmMoveView = new ConfirmMoveView(food, days, coordinates);
            confirmMoveView.display();

        }

        // get confirmation to move.
        return false;
    }

}
