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
   Settler("William", "Settler headed west with his family.", "I am headed west with my wife and kids to find land."),
   Pioneer("Maggie", "Young pioneer woman who is waiting for her husband to return from the war before moving further west.", "My husband is off to war. When he gets back, \nwe will head further west."),
   Trapper("Jeb", "Grizzled trapper headed east to sell his furs.", "I trap beaver in Oregon country. I be headed \neast to sell his furs."),
   Soldier("Captain Dodge", "Soldier stationed at fort.", "Welcome to the fort. You will be safe from hostile \nindians here."),
   Clerk("Louise", "Clerk at supply store.", "Welcome to the general store. We have anything \nyou need for your journey west."),
   Shoshone("Kimama", "Young Shoshone indian brave.", "I am a strong indian brave. New comers like you must be careful."),
   Cayusa("Chief Tiloukaikt","Indian chief of the Cayusa tribe.","I am chief of the Cayusa tribe."),
   Guide("Jim", "Mountain man available to hire as a guide. He will make travel easier.", "I have been to Oregon and back at least 20 \ntimes. I know all the dangers along the route."),
   Wife("Wife", "She will travel the trail with you.", "I am your wife. I am the greatest good you are ever going to get."),
   Son("Son", "He will travel the trail with you.", "Hi pa. Can I go down by the horses?"),
   Daughter("Daughter", "She will travel the trail with you.", "Hi pa! I found some flowers!");
     
   // class instance variables
    private final String name;
    private final String description;
    private final String explanation;

    // relationships with other classes
    private Player players; // 1-1
    private Game games; // 1-1
    private Location[] locations = new Location[25]; // 0-*
    private final CharacterDialog[] dialog = new CharacterDialog[18]; //Dependency
    
    
    
    // constructor
    Actor(String name, String description, String explanation) {
        this.name = name;
        this.description = description;
        this.explanation = explanation;
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

    public String getExplanation() {
        return explanation;
    }


    
    
    // toString 

    @Override
    public String toString() {
        return "Actor{" + "name=" + name + ", description=" + description + ", explanation=" + explanation + ", players=" + players + ", games=" + games + ", locations=" + locations + ", dialog=" + dialog + '}';
    }


}