/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;
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
import byui.cit260.oregonTrail.model.RegularScene;
import byui.cit260.oregonTrail.model.RiverScene;
import byui.cit260.oregonTrail.model.Scene;
import byui.cit260.oregonTrail.model.SceneType;
import static byui.cit260.oregonTrail.model.SceneType.RegularScene;
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
    
    public static Map createMap(int noOfRows, int noOfColumns) {
        
        //if noOfRows < 0 OR numOfColumns < 0
        if (noOfRows < 0 || noOfColumns < 0) {
            return null;
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

    private static Location[][] createLocations(int rows, int columns) {
        if (rows < 1 || columns < 1)
            return null;
        char alphabet = 'A';
        Location[][] locations = new Location[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Location location = new Location();
                location.setRow(i);
                location.setColumn(j);
                location.setVisited(false);
                location.setBlocked(true);
                
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
    private static Scene[] createScenes(InventoryItem[] inventory) {
        //scenes = Create an array Scene objects
        Scene[] scenes = new Scene[SceneType.values().length];
        {
            RegularScene scene = new RegularScene();
            scene.setDescription("Stopping point along the way.");
            scenes[SceneType.RegularScene.ordinal()] = scene;
        }
        {
            BarterScene scene = new BarterScene();
            scene.setDescription("Try to trade for what you need.");
            scene.setItemDesired(inventory[InventoryType.Bullets.ordinal()]);
            scene.setQuantityToTrade(0);
            scene.setItemsToTrade(inventory[InventoryType.Money.ordinal()]);
            scene.setInventory(inventory);
            scenes[SceneType.BarterScene.ordinal()] = scene;
        }
        {
            RiverScene scene = new RiverScene();
            scene.setDescription("River Crossing");
            scene.setRiverHeight(4);
            scene.setSafetyWithGuide(0);
            scene.setTravelChoice("Cross");
            scene.setInventory(inventory);
            scenes[SceneType.RiverScene.ordinal()] = scene;
        }
        {
            HuntingScene scene = new HuntingScene();
            scene.setDescription("Hunt for food.");
            scene.setAnimal1(null);
            scene.setAnimal2(null);
            scene.setBonusWithGuide(0);
            scene.setInventory(inventory);
            scenes[SceneType.HuntingScene.ordinal()] = scene;
        }
        {
            FortScene scene = new FortScene();
            scene.setDescription("Resupply at the fort.");
            scene.setInventory(inventory);
            scene.setItemToBuy(inventory[InventoryType.Bullets.ordinal()]);
            scene.setQuantityToBuy(0);
            scenes[SceneType.FortScene.ordinal()] = scene;
        }
        {
            EndScene scene = new EndScene();
            scene.setDescription("End of the trail!");
            scene.setCongratulations("You Win!");
            scenes[SceneType.EndScene.ordinal()] = scene;
        }   
        return scenes;
    }
    private static void assignActorsToScenes(Actor[] actors) {
        
    }
    private static void assignDialogToScenes( Scene[] scenes) {
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
    }
    private static void assignScenesToLoctions(Map map, Scene[] scenes) {
       Location[][] locations = map.getLocations();
        locations[0][0].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[0][1].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[0][2].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[0][3].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[0][4].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[1][0].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[1][1].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[1][2].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[1][3].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[1][4].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[2][0].setScene(scenes[SceneType.HuntingScene.ordinal()]);
        locations[2][1].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[2][2].setScene(scenes[SceneType.BarterScene.ordinal()]);
        locations[2][3].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[2][4].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[3][0].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[3][1].setScene(scenes[SceneType.BarterScene.ordinal()]);
        locations[3][2].setScene(scenes[SceneType.HuntingScene.ordinal()]);
        locations[3][3].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[3][4].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[4][0].setScene(scenes[SceneType.FortScene.ordinal()]);
        locations[4][1].setScene(scenes[SceneType.RegularScene.ordinal()]);
        locations[4][2].setScene(scenes[SceneType.HuntingScene.ordinal()]);
        locations[4][3].setScene(scenes[SceneType.RiverScene.ordinal()]);
        locations[4][4].setScene(scenes[SceneType.EndScene.ordinal()]);
        
    }

    private static void setToFalse() {
    
        }


    
    
    
    public int calcDistanceTraveled( int pace, int totalHealth){
       // validate inputs
	   // pace must be an integar >= 0 
        if (pace < 0 ) {
            return -1;
        }
		// lower boundary, health cannot be less than 0 
        if (totalHealth < 0) {
            return -1;
        }
		// cannot travel if dead aka totalHealth is 0
        if (totalHealth == 0) {
            return 1;
    }			
		// if pace is 0, the party is resting	
        if (pace == 0) {
            return 2;
    }
    
        // declare all variables
        int p = 15;
	int tH = 2;
	int travelDays = 10;
        
        pace = p * tH;
		
	int distanceTraveled = pace * travelDays;				
		
    return distanceTraveled;        
    }    
}   
/*
move(actor, locations)
visitHuntingLocation(huntingScene, actor)
visitFortLocation(fortScene, actor)
visitRiverScene(riverScene, actor)
visitBarterScene(barterScene, actor)
*/


