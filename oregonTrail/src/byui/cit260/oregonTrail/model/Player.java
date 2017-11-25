/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Dresen_HP
 */
public class Player implements Serializable{ 
    // class instance variables
    private String name;
    private String savedGameFile;
    private int pace;
    private double totalHealth;
    private int row;
    private int column;

   

    
    private int currentScore;
    // relationships with other classes 
    private List<Game> games = new ArrayList<>(); //0-* // TODO: Probably don't need because game higher.
    private Actor actor; // 1-1 TODO: Add player type and change this.
    private Occupation occupation; //


    
    // default constructor

    public Player() { //called from createPlayer() in GameControl
    }
    
    // methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSavedGameFile() {
        return savedGameFile;
    }

    public void setSavedGameFile(String savedGameFile) {
        this.savedGameFile = savedGameFile;
    }

    public int getPace() {
        return pace;
    }

    public int setPace(int pace) {
        this.pace = pace;
        return pace;
    }

    public double getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(double totalHealth) {
        this.totalHealth = totalHealth;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.savedGameFile);
        hash = 23 * hash + this.pace;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.totalHealth) ^ (Double.doubleToLongBits(this.totalHealth) >>> 32));
        hash = 23 * hash + this.row;
        hash = 23 * hash + this.column;
        hash = 23 * hash + this.currentScore;
        hash = 23 * hash + Objects.hashCode(this.games);
        hash = 23 * hash + Objects.hashCode(this.actor);
        hash = 23 * hash + Objects.hashCode(this.occupation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.pace != other.pace) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalHealth) != Double.doubleToLongBits(other.totalHealth)) {
            return false;
        }
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        if (this.currentScore != other.currentScore) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.savedGameFile, other.savedGameFile)) {
            return false;
        }
        if (!Objects.equals(this.games, other.games)) {
            return false;
        }
        if (this.actor != other.actor) {
            return false;
        }
        if (this.occupation != other.occupation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", savedGameFile=" + savedGameFile + ", pace=" + pace + ", totalHealth=" + totalHealth + ", row=" + row + ", column=" + column + ", currentScore=" + currentScore + ", games=" + games + ", actor=" + actor + ", occupation=" + occupation + '}';
    }
    

    public int setPace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    

}
