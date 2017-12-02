/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author Dresen_HP
 */
public class Location implements Serializable{
    
    
    /*A("Missouri", 0, 102, "S", 0, 0),
    B("Kansas River Crossing", 102, 83, "R", 0, 1),
    C("Big Blue River Crossing", 185, 119, "R", 0, 3), 
    D("Fort Kearney", 304, 200, "F", 2, 4),
    E("Courthouse Rock", 504, 50, "F", 3, 4),
    F("Chimney Rock", 554, 39, "O", 4, 4), 
    G("Platte River Crossing", 589, 51, "R", 6, 4),
    H("Fort Laramie", 640, 13, "F", 7, 4),
    I("Guernsey", 653, 177, "O", 8, 6),
    J("Independence Rock", 830, 102, "O", 9, 7),
    K("Fort Bridger", 989, 68, "F", 10, 8),
    L("Green River Crossing", 1057, 144, "R", 13, 8),
    M("Soda Springs", 1201, 57, "O", 14, 9),
    N("Fort Hall", 1258, 182, "F", 14, 10), 
    O("Snake River Crossing", 1440, 5, "R", 15, 11),
    P("Shoshone Falls", 1445, 9, "O", 15, 12),
    Q("Fort Boise", 1554, 135, "F", 16, 13),
    R("Grand Ronde in the Blue Mountains", 1689, 21, "M", 18, 14),
    S("Waiilatpu Village", 1710, 98, "M", 20, 15),
    T("The Dalles", 1808, 50, "M", 20, 16),
    U("Fort Bridger", 1858, 127, "F", 21, 18),
    V("Fort Walla Walla", 1935, 15, "F", 23, 19),
    W("Willamette Valley of the Oregon Territory", 1950, 12, "O", 24, 18),
    X("Columbia River Crossing", 1962, 73, "R", 25, 17),
    Y("Fort Vancouver", 2035, 0, "E", 25, 15);*/
    // variables
    private  String name;
    private  int milesFromStart;
    private  int milesToNext;
    private  String type;
    private  int column;
    private  int row;
    private boolean visited;
    private String symbol;
    private boolean blocked;
    private Scene scene;
    private Places place;
    
    
    // constructor function
    public Location() {        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMilesFromStart() {
        return milesFromStart;
    }

    public void setMilesFromStart(int milesFromStart) {
        this.milesFromStart = milesFromStart;
    }

    public int getMilesToNext() {
        return milesToNext;
    }

    public void setMilesToNext(int milesToNext) {
        this.milesToNext = milesToNext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + this.milesFromStart;
        hash = 53 * hash + this.milesToNext;
        hash = 53 * hash + Objects.hashCode(this.type);
        hash = 53 * hash + this.column;
        hash = 53 * hash + this.row;
        hash = 53 * hash + (this.visited ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.symbol);
        hash = 53 * hash + (this.blocked ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.scene);
        hash = 53 * hash + Objects.hashCode(this.place);
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
        final Location other = (Location) obj;
        if (this.milesFromStart != other.milesFromStart) {
            return false;
        }
        if (this.milesToNext != other.milesToNext) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        if (this.row != other.row) {
            return false;
        }
        if (this.visited != other.visited) {
            return false;
        }
        if (this.blocked != other.blocked) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.scene, other.scene)) {
            return false;
        }
        if (this.place != other.place) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Location{" + "name=" + name + ", milesFromStart=" + milesFromStart + ", milesToNext=" + milesToNext + ", type=" + type + ", column=" + column + ", row=" + row + ", visited=" + visited + ", symbol=" + symbol + ", blocked=" + blocked + ", scene=" + scene + ", place=" + place + '}';
    }

 
}

