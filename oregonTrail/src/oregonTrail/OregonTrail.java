/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oregonTrail;

import byui.cit260.oregonTrail.model.Actor;
import byui.cit260.oregonTrail.model.CharacterDialog;
import byui.cit260.oregonTrail.model.Player;
import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.Animal;
import byui.cit260.oregonTrail.model.HuntingScene;
import byui.cit260.oregonTrail.model.RiverScene;
import byui.cit260.oregonTrail.model.BarterScene;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;
import byui.cit260.oregonTrail.model.Location;
import byui.cit260.oregonTrail.model.Map;
import byui.cit260.oregonTrail.view.StartProgramView;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dresen_HP
 */
public class OregonTrail {
    
    // class instance variables
    private static Game currentGame = null; 
    private static Player player = null;
    
    /**
     * @param args the command line arguments
     */
    
    // main function starts the program
    public static void main(String[] args) {
    
        // calls contstructor function in StartProgramView to create new instance of StartProgramView object named startProgramView. 
        //This constructor function creates and displays the welcome banner that explains game.
        StartProgramView startProgramView = new StartProgramView();
        startProgramView.display(); //call displayStartProgramView() in StartProgramView to prompt user for name.
        
    }
        
    public static Game getCurrentGame() {
        return currentGame;
    }
    
    public static void setCurrentGame(Game currentGame) {
        OregonTrail.currentGame = currentGame;
    }
    
    public static Player getPlayer() { 
        return player;
    }
    
    public static void setPlayer(Player player) { // called from createPlayer() in GameControl
        OregonTrail.player = player;
    }
  
}    