/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;
import java.awt.Point;
/**
 *
 * @author Dresen_HP
 */
public enum Actor{
   Settler("William", "Settler headed west with his family."),
   Pioneer("Maggie", "Young pioneer woman who is waiting for her husband to return from the war before moving further west."),
   Trapper("Jeb", "Grizzled trapper headed east to sell his furs."),
   Soldier("Captain Dodge", "Soldier stationed at fort."),
   Clerk("Louise", "Clerk at supply store."),
   Shoshone("Kimama", "Young Shoshone indian brave."),
   Cayusa("Chief Tiloukaikt","Indian chief of the Cayusa tribe."),
   Guide("Jim", "Mountain man available to hire as a guide. He will make travel easier."),
   Wife("Wife", "She will travel the trail with you."),
   Son("Son", "He will travel the trail with you."),
   Daughter("Daughter", "She will travel the trail with you.");
   
   // class instance variables
    private final String name;
    private final String description;

    // relationships with other classes
    private Player players; // 1-1
    private Game games; // 1-1
    private Location[] locations = new Location[25]; // 0-*
    private final CharacterDialog[] dialog = new CharacterDialog[18]; //Dependency
    
    
    
    // constructor
    Actor(String name, String description) {
        this.name = name;
        this.description = description;
    }   
    // getter and setter methods    

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public Player getPlayers() {
        return players;
    }

    public void setPlayers(Player players) {
        this.players = players;
    }

    public Game getGames() {
        return games;
    }

    public void setGames(Game games) {
        this.games = games;
    }

    public Location[] getLocations() {
        return locations;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }


    
    
    // toString 

    @Override
    public String toString() {
        return "Actor{" + "name=" + name + ", description=" + description + '}';
    }
    
}