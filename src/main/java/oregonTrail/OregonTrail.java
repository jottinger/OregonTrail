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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dresen_HP
 */
public class OregonTrail {

    // class instance variables
    private static Game currentGame = null;
    private static Player player = null;
    private static InventoryItems currentInventory;

    private static PrintWriter outFile = null;
    private static BufferedReader inFile = null;

    private static PrintWriter logFile = null;

    /**
     * @param args the command line arguments
     */

    // main function starts the program
    public static void main(String[] args) {

        try {
            //open character stream files for end user iput and output
            OregonTrail.inFile =
                    new BufferedReader(new InputStreamReader(System.in));

            OregonTrail.outFile = new PrintWriter(System.out, true);

            try {

                //open log file
                String filePath = "log.txt";
                OregonTrail.logFile = new PrintWriter(filePath);

            } catch (Exception e) {
                System.out.println("Exception: " + e.toString() +
                        "\nCause: " + e.getCause() +
                        "\nMessage: " + e.getMessage());
            }

            //create StartProgramView and start the program
            //(JORDAN) does it matter than we call StartProgramView later? I think we should remove what is down below...
            StartProgramView startProgramView = new StartProgramView();
            startProgramView.display();
            return;

        } catch (Throwable e) {

            System.out.println("Exception: " + e.toString() +
                    "\nCause: " + e.getCause() +
                    "\nMessage: " + e.getMessage());

            e.printStackTrace();
            ;
        } finally {
            try {
                if (OregonTrail.inFile != null)
                    OregonTrail.inFile.close();

                if (OregonTrail.outFile != null)
                    OregonTrail.outFile.close();

                if (OregonTrail.logFile != null)
                    OregonTrail.logFile.close();

            } catch (IOException ex) {
                System.out.println("Error closing log files");
                return;
            }

        }

        // calls contstructor function in StartProgramView to create new instance of StartProgramView object named startProgramView.
        //This constructor function creates and displays the welcome banner that explains game.
        StartProgramView startProgramView = new StartProgramView();
        try {
            startProgramView.display(); //call displayStartProgramView() in StartProgramView to prompt user for name.
        } catch (Throwable te) {
            System.out.println(te.getMessage());
            te.printStackTrace();
            startProgramView.display();
        }
    }


    public static Game getCurrentGame() {
        return currentGame;
    }

    public static InventoryItem[] setInventoryItems(String inventoryItem, int inventoryAmount) {
        InventoryItem[] currentInventory = new InventoryItem[8];
        return currentInventory;
    }

    public static InventoryItems getInventoryItems() {
        return currentInventory;
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

    public static PrintWriter getOutFile() {
        return outFile;
    }

    public static void setOutFile(PrintWriter outFile) {
        OregonTrail.outFile = outFile;
    }

    public static BufferedReader getInFile() {
        return inFile;
    }

    public static void setInFile(BufferedReader inFile) {
        OregonTrail.inFile = inFile;
    }

    public static PrintWriter getLogFile() {
        return logFile;
    }

    public static void setLogFile(PrintWriter logFile) {
        OregonTrail.logFile = logFile;
    }

    private static class InventoryItems {

        public InventoryItems() {
        }
    }

}
