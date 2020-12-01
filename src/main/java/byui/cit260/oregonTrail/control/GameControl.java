/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;

import byui.cit260.oregonTrail.exceptions.GameControlException;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import static byui.cit260.oregonTrail.model.Occupation.Merchant;
import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;
import static byui.cit260.oregonTrail.model.InventoryType.Money;
import byui.cit260.oregonTrail.model.Location;
import byui.cit260.oregonTrail.model.Occupation;
import byui.cit260.oregonTrail.model.Player;
import oregonTrail.OregonTrail;
import byui.cit260.oregonTrail.model.Map;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 *
 * @author Dresen_HP
 */
public class GameControl {

// createPlayer called from StartProgramView  
    public static Player createPlayer(String name) throws GameControlException { // called from doAction() in StartProgramViewClass. Player's name passed in.
        if (name == null) {  // if no player name passed in, return null back to doAction()
            throw new GameControlException("Cannot create player. Player name is empty.");
        }

        Player player = new Player(); // Create new player object using constructor function in Player class
        player.setName(name); // set the name in the new player object.

        OregonTrail.setPlayer(player); // calls setPlayer in OregonTrail.java and passes in the player object.

        return player; // returns player object back to doAction in StartProgramView
    }

    /*public void CreateNewPlayer(String name) { // called from doAction() in StartProgramView class
        
        Player player = new Player(); // creates new Player instance named player
        String playerName = player.getName(); //calls getter function for player object to get player's name.
        if (name != null) { // If the player doesn't have a name set, setter function called to set name with value passed in.
            if (playerName == null) {
                player.setName(name);
            }               
        } 
        }*/
// createNewGame called from MainMenuView
    public static int createNewGame(Player player) throws GameControlException, MapControlException {
        if (player == null) // if no player passed in, return null back to startNewGame() in MainMenuView
        {
            throw new GameControlException("Cannot create game. Player name is empty.");  // return error code
        }            // create new game object.
        Game game = new Game();
        // save a reference to the Player object in the game.
        game.setPlayer(player);
        // save a reference to the game in the main class. 
        OregonTrail.setCurrentGame(game);
        // create actors - not needed because enum class
        // assign actor to the player - will do that in susequent occupationView
        // Initialize companions. Will set names in subsequent companionView
        game.setCompanion1("");
        game.setCompanion2("");
        game.setCompanion3("");
        // create inventory. TODO: finish createItems() in inventoryControl
        InventoryItem[] items = createItems();
        // save list of items in the game object.
        game.setInventory(items);
        game.setMilesTraveled(0);
        game.setPercentComplete(0);
        game.setTravelDays(1);
        game.getPlayer().setPace(1);
        game.setStartDate(1);
        // save location of player at start
        game.getPlayer().setColumn(0);
        game.getPlayer().setRow(0);
        // create map and set it in game
        int noOfColumns = 5;
        int noOfRows = 5;
        Map map = MapControl.createMap(noOfRows, noOfColumns);
        // make sure map was created.
        if (map == null) {
            return -1;
        } else {
            game.setMap(map);
            return 1; // indicates success
        }

        /*
        actors = createActors()
        Save the list of actors in the Game object
        Assign an actor to the player
         */
    }

// called from MainMenuView
    public static void saveGame(Game game, String filePath) throws GameControlException {
        try (FileOutputStream fops = new FileOutputStream(filePath)) {
            ObjectOutputStream output = new ObjectOutputStream(fops);

            output.writeObject(game); // write game object out to file
            
        } catch (Exception e) {
            throw new GameControlException(e.getMessage());
        }
    }

// called from MainMenuView
    public static void getSavedGame(String filePath) throws GameControlException {
        Game game = null;

        try (FileInputStream fips = new FileInputStream(filePath)) {
            ObjectInputStream input = new ObjectInputStream(fips);

            game = (Game) input.readObject(); // read the game object from file
        } catch (Exception e) {
            throw new GameControlException(e.getMessage());
        }
        // close the output file
        OregonTrail.setCurrentGame(game); // save in OregonTrail 
    }

    public static void addDaysToCalendar(int daysToNext) throws MapControlException {
        Game game = OregonTrail.getCurrentGame();
        int travelDays = game.getTravelDays();
        travelDays += daysToNext;
        game.setTravelDays(travelDays);
    }

    // creates inventory list for game when new game is started.
    public static InventoryItem[] createItems() throws GameControlException {

        InventoryItem[] inventory = new InventoryItem[8];
        inventory[InventoryType.Bullets.ordinal()] = new InventoryItem(InventoryType.Bullets, 0);
        inventory[InventoryType.Clothing.ordinal()] = new InventoryItem(InventoryType.Clothing, 0);
        inventory[InventoryType.Food.ordinal()] = new InventoryItem(InventoryType.Food, 0);
        inventory[InventoryType.Guide.ordinal()] = new InventoryItem(InventoryType.Guide, 0);
        inventory[InventoryType.Medicine.ordinal()] = new InventoryItem(InventoryType.Medicine, 0);
        inventory[InventoryType.Money.ordinal()] = new InventoryItem(InventoryType.Money, 500);
        inventory[InventoryType.Oxen.ordinal()] = new InventoryItem(InventoryType.Oxen, 0);
        inventory[InventoryType.WagonWheel.ordinal()] = new InventoryItem(InventoryType.WagonWheel, 0);
        return inventory;

    }

    public static void setCompanionName(String companion) throws GameControlException {
        if (companion == null) {
            throw new GameControlException("Cannot set companion. Companion name cannot be blank.");
        }
        Game game = OregonTrail.getCurrentGame(); // gets current game from main
        String companion1 = game.getCompanion1(); // gets companion 1 name from game
        String companion2 = game.getCompanion2(); // gets companion 1 name from game
        String companion3 = game.getCompanion3(); // gets companion 1 name from game
        if (companion1 == "") {
            OregonTrail.getCurrentGame().setCompanion1(companion); // sets companions
        } else if (companion2 == "") {
            OregonTrail.getCurrentGame().setCompanion2(companion);
        } else if (companion3 == "") {
            OregonTrail.getCurrentGame().setCompanion3(companion);
        } else {
            return;
        }

    } // goes back to doAction()

// called from CreateNewGame in this class
    public static void setOccupation(Occupation choice) throws GameControlException {
        if (choice == null) {
            throw new GameControlException("Cannot set occupation. OccupationChoice cannot be null");
        }
        Game game = OregonTrail.getCurrentGame();
        /*Occupation occupation = game.getPlayer().getOccupation();
        if (occupation == null) {*/
        game.getPlayer().setOccupation(choice);

        //}
    }
// called from CreateNewGame in this class

    public static void setStartDate(int startDate) throws GameControlException, MapControlException {
        Game game = OregonTrail.getCurrentGame();
        int gameStartDate = game.getStartDate();
        if (gameStartDate == 1) {
            game.setStartDate(startDate);
        }
        game.setTravelDays(0);
        Location location = MapControl.getCurrentLocation();
        location.getScene().setActivityDone(true);

    }

    public static String thisDay() throws GameControlException {
        Game game = OregonTrail.getCurrentGame();
        int startDate = game.getStartDate();
        int travelDays = game.getTravelDays();
        if (startDate == 0) {
            throw new GameControlException("Cannot find day. StartDate cannot be null.");
        }
        if (travelDays == 0) {
            throw new GameControlException("Cannot find day. TravelDays cannot be null.");
        }
        int monthNumber = findMonth(startDate, travelDays);
        int day = findDay(startDate, travelDays);
        String month = null;
        String calendarDate = null;
        // get month

        String[] monthList = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
        month = monthList[monthNumber];

        //construct calendarDate string of month and days
        calendarDate = month + day;
        // return calendarDate

        return calendarDate;
    }

    public static int findMonth(int startDate, int travelDays) throws GameControlException {
        if (startDate < 0) {
            throw new GameControlException("Month cannot be calculated. Start date cannot be less than 0");
        }
        if (travelDays < 0) {
            throw new GameControlException("Month cannot be calculated. Travel days cannot be less than 0");
        }
        double quotient;
        int monthNumber = 1;
        double calDate = startDate + travelDays;
        quotient = calDate / 30;
        monthNumber = (int) Math.ceil(quotient);
        if (monthNumber > 12) {
            monthNumber = monthNumber % 12;
        }
        if (monthNumber == 0) {
            monthNumber = 12;
        }
        monthNumber -= 1;

        return monthNumber;
    }

    public static int findDay(int startDate, int travelDays) throws GameControlException {
        if (startDate < 0) {
            return -1;
        }
        if (travelDays < 0) {
            return -1;
        }
        int calDate = startDate + travelDays;
        int days = calDate % 30;
        if (days == 0) {
            days = 30;
        }
        return days;
    }

    public static int getRandom(int high) {
        Random rand = new Random();
        int low = 0;

        int randomNumber = rand.nextInt(high - low) + low;
        return randomNumber;
    }
}

//public void saveGame(Player player, Game game, Database INSTANCE) {
//TODO: Learn how to save the game.
//}
//public void loadGame(Player player, Game game, Database INSTANCE) {
//TODO: Add statements to load game.
//}
//public void quitGame() {
//TODO: Add statements to function.
//}
