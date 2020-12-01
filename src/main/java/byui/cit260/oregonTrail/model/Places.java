/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;

/**
 *
 * @author Dresen_HP
 */
public enum Places {
    A("Missouri", 0, 102, "S", 0, 0),
    B("Kansas River Crossing", 102, 83, "R", 0, 1),
    C("Big Blue River Crossing", 185, 119, "R", 0, 2),
    D("Fort Kearney", 304, 200, "F", 0, 3),
    E("Courthouse Rock", 504, 50, "F", 0, 4),
    F("Chimney Rock", 554, 39, "O", 1, 0),
    G("Platte River Crossing", 589, 51, "R", 1, 1),
    H("Fort Laramie", 640, 13, "F", 1, 2),
    I("Guernsey", 653, 177, "O", 1, 3),
    J("Independence Rock", 830, 102, "O", 1, 4),
    K("Fort Bridger", 989, 68, "F", 2, 0),
    L("Green River Crossing", 1057, 144, "R", 2, 1),
    M("Soda Springs", 1201, 57, "O", 2, 2),
    N("Fort Hall", 1258, 182, "F", 2, 3),
    O("Snake River Crossing", 1440, 5, "R", 2, 4),
    P("Shoshone Falls", 1445, 9, "O", 3, 0),
    Q("Fort Boise", 1554, 135, "F", 3, 1),
    R("Grand Ronde", 1689, 21, "M", 3, 2),
    S("Waiilatpu Village", 1710, 98, "M", 3, 3),
    T("The Dalles", 1808, 50, "M", 3, 4),
    U("Fort Bridger", 1858, 127, "F", 4, 0),
    V("Fort Walla Walla", 1935, 15, "F", 4, 1),
    W("Willamette Valley", 1950, 12, "O", 4, 2),
    X("Columbia River Crossing", 1962, 73, "R", 4, 3),
    Y("Fort Vancouver", 2035, 0, "E", 4, 4);

    // class instance variables
    private String description;
    private int milesFromStart;
    private int milesToNext;
    private String type;
    private int rows;
    private int columns;

    Places(String description, int milesFromStart, int milesToText, String type, int rows, int columns) {
        this.description = description;
        this.milesFromStart = milesFromStart;
        this.milesToNext = milesToText;
        this.type = type;
        this.rows = rows;
        this.columns = columns;
    }

    // getter methods

    public String getDescription() {
        return description;
    }

    public int getMilesFromStart() {
        return milesFromStart;
    }

    public int getMilesToNext() {
        return milesToNext;
    }

    public String getType() {
        return type;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    // toString

    @Override
    public String toString() {
        return "Places{" + "description=" + description + ", milesFromStart=" + milesFromStart + ", milesToNext=" + milesToNext + ", type=" + type + ", rows=" + rows + ", columns=" + columns + '}';
    }
    
}
