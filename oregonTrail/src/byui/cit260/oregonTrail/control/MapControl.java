/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.Actor;
import byui.cit260.oregonTrail.model.Animal;
import byui.cit260.oregonTrail.model.BarterScene;
import byui.cit260.oregonTrail.model.CharacterDialog;
import byui.cit260.oregonTrail.model.EndScene;
import byui.cit260.oregonTrail.model.FortScene;
import java.util.Date;
import java.util.Random;
import byui.cit260.oregonTrail.model.Player;
import byui.cit260.oregonTrail.model.Location;
import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.HuntingScene;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;
import byui.cit260.oregonTrail.model.Map;
import byui.cit260.oregonTrail.model.Places;
import byui.cit260.oregonTrail.model.RegularScene;
import byui.cit260.oregonTrail.model.RiverScene;
import byui.cit260.oregonTrail.model.Scene;
import byui.cit260.oregonTrail.model.SceneType;
import static byui.cit260.oregonTrail.model.SceneType.RegularScene;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;


/**
 *
 * @author Clausi,James
 */


public class MapControl {
    /* Assignment 10 TODO
    * Implement createMap() pg 21
    * Implement createCharacterDialog() pg 27
    *   Create new enum class called DialogType
    *   Use it to set index position of CharacterDialog pg 28
    * Implement assignDialogToScenes()
    */
    
    public static Map createMap(int noOfRows, int noOfColumns) 
        throws MapControlException
    {
        //if noOfRows < 0 OR numOfColumns < 0
        if (noOfRows < 0 || noOfColumns < 0) {
        //    return null;
        throw new MapControlException("Map must be at least one column wide and one row long");
        }
        Map gameMap = new Map();
        gameMap.setRows(noOfRows);
        gameMap.setColumns(noOfColumns);
        //locations = createLocations(noOfRows, noOfColumns)
        Location[][] locations = createLocations(noOfRows, noOfColumns);
        //Assign the locations array to the map
        gameMap.setLocations(locations);
        //scenes = createScenes()
        InventoryItem[] inventory = new InventoryItem[InventoryType.values().length];
        inventory = OregonTrail.getCurrentGame().getInventory();
        Scene[] scenes = createScenes(inventory);
        //assignQuestionsToScenes()
        //assignDialogToScenes(scenes);
        assignScenesToLoctions(gameMap, scenes);
        return gameMap;
    }

    private static Location[][] createLocations(int rows, int columns)
        throws MapControlException
        {
        /*Don't need this because createMap function looks for a negative colum and row
            if (rows < 1 || columns < 1)
            return null; */
        char p = 0;
        char alphabet = 'A';
        
        Location[][] locations = new Location[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Location location = new Location();
                location.setRow(i);
                location.setColumn(j);
                location.setVisited(false);
                location.setBlocked(true);
                String alpha = Character.toString(alphabet);
                location.setPlace(Places.valueOf(alpha));
                p++;
                location.setSymbol((Character.toString(alphabet)));
                alphabet++;
               locations[i][j] = location; 
            }
            locations[0][0].setBlocked(false);
            locations[0][1].setBlocked(false);
            locations[0][0].setSymbol("*");
        }
            return locations;

    }
    private static Scene[] createScenes(InventoryItem[] inventory) 
        throws MapControlException
        {
        //scenes = Create an array Scene objects
        Scene[] scenes = new Scene[SceneType.values().length];
        {
            RegularScene scene = new RegularScene();
            scene.setName("Regular");
            scene.setDescription("Stopping point along the way.");
            scenes[SceneType.RegularScene.ordinal()] = scene;
            scene.setOption1("Talk To Locals");
        }
        {
            StartScene scene = new StartScene();
            scene.setName("Start");
            scene.setDescription("Prepare to start your journey. \n"
                    + "You will choose your occupation, companions, \n"
                    + "set your start date, and purchase items for the road.");
            scene.setItemDesired(inventory[InventoryType.Bullets.ordinal()]);
            scene.setQuantityToTrade(0);
            scene.setItemsToTrade(inventory[InventoryType.Money.ordinal()]);
            scene.setInventory(inventory);
            scenes[SceneType.StartScene.ordinal()] = scene;
            scene.setOption1("Prepare For Travel");
        }
        {
            RiverScene scene = new RiverScene();
            scene.setName("River");
            scene.setDescription("River Crossing: "
                    + "\nYou need to cross the river to continue your journey."
                    + "\nHiring a guide will increase your chance of success.");
            scene.setRiverHeight(4);
            scene.setSafetyWithGuide(0);
            scene.setTravelChoice("Cross");
            scene.setInventory(inventory);
            scenes[SceneType.RiverScene.ordinal()] = scene;
            scene.setOption1("River Crossing");
            
        }
        {
            HuntingScene scene = new HuntingScene();
            scene.setName("Hunting");
            scene.setDescription("The hunting is good here."
                    + "\nSpend the day hunting to provide food for your family."
                    + "\nMake sure you have bullets before you hunt.");
            scene.setAnimal1(null);
            scene.setAnimal2(null);
            scene.setBonusWithGuide(0);
            scene.setInventory(inventory);
            scenes[SceneType.HuntingScene.ordinal()] = scene;
            scene.setOption1("Go Hunting");
        }
        {
            FortScene scene = new FortScene();
            scene.setName("Fort");
            scene.setDescription("Resupply at the fort. \n"
                    + "Purchase needed supplies for your journey.");
            scene.setInventory(inventory);
            scene.setItemToBuy(inventory[InventoryType.Bullets.ordinal()]);
            scene.setQuantityToBuy(0);
            scenes[SceneType.FortScene.ordinal()] = scene;
            scene.setOption1("Purchase Goods");
        }
        {
            EndScene scene = new EndScene();
            scene.setName("Finish");
            scene.setDescription("End of the trail!");
            scene.setCongratulations("You Win!");
            scenes[SceneType.EndScene.ordinal()] = scene;
            scene.setOption1("Finish Game");
        }   
        return scenes;
    }
    private static void assignActorsToScenes(Actor[] actors) {
        
    }
    /*private static void assignDialogToScenes( Scene[] scenes) {
        System.out.println("\n*** assignDialogToScenes() called ***");
        
        // Assign questions to the first question scene
        //questionScene1 = scenes(indexOfScene)
        //questionsInScene = Create a new Questions array
        //questionsInScene[0] = questions[indexOfQuestion]
        //questionsInScene[1] = questions[indexOfQuestion]

        // assign questionsInScene array to questionScene1
        
        // Assign questions to the second question scene
        //nextQuestionScene2 = scenes(indexOfScene)
        //questionsInScene = Create a new Questions array
        //questionsInScene[0] = questions[indexOfQuestion]
        //questionsInScene[1] = questions[indexOfQuestion]

        //assign questionsInScene array to questionScene2
        // REPEAT FOR ALL OTHER QUESTION SCENES 
    }*/
    private static void assignScenesToLoctions(Map map, Scene[] scenes) 
            throws MapControlException
        {
       Location[][] locations = map.getLocations();
        locations[0][0].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[0][1].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[0][2].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[0][3].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[0][4].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[1][0].setScene(scenes[SceneType.HuntingScene.ordinal()]);
        locations[1][1].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[1][2].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[1][3].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[1][4].setScene(scenes[SceneType.HuntingScene.ordinal()]);
        locations[2][0].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[2][1].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[2][2].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[2][3].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[2][4].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[3][0].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[3][1].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[3][2].setScene(scenes[SceneType.HuntingScene.ordinal()]);
        locations[3][3].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[3][4].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[4][0].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[4][1].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[4][2].setScene(scenes[SceneType.HuntingScene.ordinal()]);
        locations[4][3].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[4][4].setScene(scenes[SceneType.EndScene.ordinal()]);
    }
    public static int calcDaysNeeded(int miles, int pace) throws MapControlException {
        if (miles < 0 ) {
            throw new MapControlException("Can not calculate days needed because miles negative. "
                    + "\nPlease try again with positive numbers.");
        }
        if (pace < 1 || pace > 3) {
            throw new MapControlException("Can not calculate days needed because pace is invalid. "
                    + "\nPlease set pace to 1, 2, or 3.");
        }
        int milesPerDay = 0;
        int days = 0;
        switch (pace) {
            case 1:
                milesPerDay = 10;
                break;
            case 2:
                milesPerDay = 15;
                break;
            case 3:
                milesPerDay = 21;
                break;
        }
        days = miles / milesPerDay; 
        return days;
        // check inputs
        // change pace into miles per day.
        // divide miles/miles per day.
        // return days
    }
    public static int calcFoodNeeded(int days, int pace) throws MapControlException {
        if (pace < 1 || pace > 3) {
            throw new MapControlException("Can not calculate food needed because pace is invalid. "
                    + "\nPlease set pace to 1, 2, or 3.");
        }
        if (days < 0 ) {
            throw new MapControlException("Can not calculate food needed because miles negative. "
                    + "\nPlease try again with positive numbers.");
        }
        int foodPerDay = 0;
        int food = 0;
        switch (pace) {
            case 1:
                foodPerDay = 1;
                break;
            case 2:
                foodPerDay = 2;
                break;
            case 3:
                foodPerDay = 3;
                break;
        }
        food = foodPerDay * days;
        
        return food;
        // change pace into miles per day.
        // figure food needed per day based on pace
        // multiply food times days
        // return food
    }
    //milesToNext, foodRequired, daysToNext, newLocation, milesToNext, location
    public static void moveLocation(Point coordinates, double foodRequired, int daysToNext) throws MapControlException, InventoryControlException {
            if (coordinates == null) {
                throw new MapControlException("Cannot move to new location. Coordinate is null.");
            }
            if (foodRequired < 0) {
                throw new MapControlException("Cannot move to new location. Food Required cannot be negative");
            }
            if (daysToNext < 0) {
                throw new MapControlException("Cannot move to new location. daysToNext cannot be negative");
            }
            // Get milesToNext and change symbol on old location
            Game game = OregonTrail.getCurrentGame();
            Location oldLocation = getCurrentLocation();
            changeCurrentSymbol();
            int miles = oldLocation.getPlace().getMilesToNext();
            
            // move to new location.
            int newRow = coordinates.x;
            int newCol = coordinates.y;
            game.getPlayer().setRow(newRow);
            game.getPlayer().setColumn(newCol);
            // Change visited, blocked, symbol of new location.
            Location location = getCurrentLocation();
            location.setVisited(true);
            location.setSymbol("*");
            // unblock next location
            unblockNext(game, newRow, newCol);
            // Remove food from inventory, add days and miles, change percent complete. 
            InventoryControl.subtractFromInventory(InventoryType.Food, foodRequired);
            GameControl.addDaysToCalendar(daysToNext);
            addMilesTraveled(miles);
            addPercentComplete(miles);
            
        
        }

    private static void unblockNext(Game game, int newRow, int newCol) throws MapControlException{
        int nextCol = newCol + 1;
        Location[][] locations = game.getMap().getLocations();
        Location location;

            if (nextCol <= 4) {
                location = locations[newRow][nextCol];
            }
            else {
                int nextRow = newRow + 1;
                nextCol = 0;
                location = locations[nextRow][nextCol];
            }
            location.setBlocked(false);
    }

    private static void changeCurrentSymbol() throws MapControlException{        
        Location location = getCurrentLocation();
        location.setSymbol(">");
        
    }

    private static void addPercentComplete(int miles) {
        Game game = OregonTrail.getCurrentGame();
        Location[][] locations = game.getMap().getLocations();
        int total = 0;
        
        for (Location[] location : locations) {
            for (Location stop : location) {
                int distance = stop.getPlace().getMilesToNext();
                total += distance; 
            }
        }
        double milesTraveled = (double)game.getMilesTraveled();
        double percentComplete = milesTraveled / total;
        game.setPercentComplete(percentComplete);
    }

    
    
    
    public int calcDistanceTraveled( int pace, int totalHealth)
        throws MapControlException {
       // validate inputs
	   // pace must be an integar >= 0 
        if (pace < 0 ) {
            throw new MapControlException("Cannot calculate distance traveled."
                                        + "Your pace of " + pace + " is less than zero.");
        }
		// lower boundary, health cannot be less than 0 
        if (totalHealth <= 0) {
            throw new MapControlException("Cannot calculate distance traveled."
                                        + "Your heath of " + totalHealth + " is less than zero.");
        }
    
        // declare all variables
        int p = 15;
	int tH = 2;
	int travelDays = 10;
        
        pace = p * tH;
		
	int distanceTraveled = pace * travelDays;				
		
    return distanceTraveled;        
    }  
    
    // check location to make sure it is not out of bounds, and is unblocked and not visited.
    public static Location checkLocation(Point coordinates) throws MapControlException {
        Map map = OregonTrail.getCurrentGame().getMap();
        int newRow = coordinates.x;
        int newColumn = coordinates.y;
        
        if (newRow < 0 || newRow >= map.getRows() || 
                newColumn < 0 || newColumn >= map.getColumns()) {
            throw new MapControlException("The row or column number is outside bounds of map");
        }
        Location[][] locations = OregonTrail.getCurrentGame().getMap().getLocations();
        Location location = locations[newRow][newColumn];
        int miles = 0;
        if (location.isBlocked()) {
            throw new MapControlException("This location is not the next location on the trail. "
                    + "\n Please choose the next location on the trail.");
        }
        else if (location.isVisited()) { 
            throw new MapControlException("You have already visited this location."
                    + "\n Please choose the next location on the trail.");
        }
        else {
            return location;
        } 
        
    }

    private static void addMilesTraveled(int miles) throws MapControlException{
        Game game = OregonTrail.getCurrentGame();
        int currentMiles = game.getMilesTraveled();
        currentMiles += miles;
        game.setMilesTraveled(currentMiles);
    }
    
    public static Location getCurrentLocation() throws MapControlException{
        Location[][] locations = OregonTrail.getCurrentGame().getMap().getLocations();
        if (locations == null) {
            throw new MapControlException("Cannot get location. Locations array is null");
        }
        int row = OregonTrail.getCurrentGame().getPlayer().getRow();
        int column = OregonTrail.getCurrentGame().getPlayer().getColumn();
        Location location = locations[row][column];
        return location;
    }
}   



/*
move(actor, locations)
visitHuntingLocation(huntingScene, actor)
visitFortLocation(fortScene, actor)
visitRiverScene(riverScene, actor)
visitBarterScene(barterScene, actor)
*/


