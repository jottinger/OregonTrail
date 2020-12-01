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

/**
 *
 * @author Dresen_HP
 */
public class Map implements Serializable {
    //class instace variables
    private String description;
    private boolean visited;
    private Game game;
    private int rows;
    private int columns;

    
    // relationships with other classes
    private Location[][] locations = new Location[5][5]; //0-*
    private Places[] places;
    private Scene[] scenes;
    
    //constructor

    public Map() {
    }
    
    //methods

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Location[][] getLocations() {
        return locations;
    }

    public void setLocations(Location[][] locations) {
        this.locations = locations;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Places[] getPlaces() {
        return places;
    }

    public void setPlaces(Places[] places) {
        this.places = places;
    }

    public Scene[] getScenes() {
        return scenes;
    }

    public void setScenes(Scene[] scenes) {
        this.scenes = scenes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + (this.visited ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.game);
        hash = 59 * hash + this.rows;
        hash = 59 * hash + this.columns;
        hash = 59 * hash + Arrays.deepHashCode(this.locations);
        hash = 59 * hash + Arrays.deepHashCode(this.places);
        hash = 59 * hash + Arrays.deepHashCode(this.scenes);
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
        final Map other = (Map) obj;
        if (this.visited != other.visited) {
            return false;
        }
        if (this.rows != other.rows) {
            return false;
        }
        if (this.columns != other.columns) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        if (!Arrays.deepEquals(this.locations, other.locations)) {
            return false;
        }
        if (!Arrays.deepEquals(this.places, other.places)) {
            return false;
        }
        if (!Arrays.deepEquals(this.scenes, other.scenes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Map{" + "description=" + description + ", visited=" + visited + ", game=" + game + ", rows=" + rows + ", columns=" + columns + ", locations=" + locations + ", places=" + places + ", scenes=" + scenes + '}';
    }
    
    
    

    
     
}