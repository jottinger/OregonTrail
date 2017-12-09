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
public enum CharacterDialog{
    WinterMountain("There are snow storms blocking the mountains, it might be best to wait till it a few weeks before moving on.","mountain"),
    WinterRiver("The water level will decrease to make crossing much easier.","river"),
    WinterPlains("There is much less game available, I would stock up on food before leaving.","plain"),
    WinterLateMountain("The storms have stopped but the snow hasn’t melted yet, you may want to wait a few more weeks.","mountain"), 
    WinterLateRiver("The river has started to melt, but it is darn cold! I would take the ferry or you’re guaranteed to catch pneumonia.","river"), 
    WinterLatePlain("Large game is still sparse, take extra bullets for those faster smaller critters and extra food.","plain"), 
    SpringMountain("The passes are open but it sure is muddy and cold in the mountains, take extra clothes and be careful of getting stuck.","mountain"), 
    SpringRiver("The snow melting from the mountains has raised the water level of the river, floating might be better than fording at this point.","river"), 
    SpringPlain("Hibernation must be over, all the animals are out and the hunting is prime.","plain"), 
    SummerMountain("Be careful of thieves in the mountains. They wait for those that get stuck during summer storms.","mountain"), 
    SummerRiver("The ferry up the river is the best when the water level is high.","river"), 
    SummerPlain("Lots of Bison were seen yesterday. It might be a good time to go hunting.","plain"), 
    FallMountain("Winter is coming, make sure to bring extra food and clothes to get through the mountains.","mountain"), 
    FallRiver("Water levels continue to drop and fording the river is possible.","river"), 
    FallPlain("You better stock up on food or be prepared to hunt. The game will be gone soon!","plain"), 
    WinterEarlyMountain("Looks like snow is coming. I wouldn’t want to get caught in the mountains in the storm.","mountain"), 
    WinterEarlyRiver("With the water so low, the river froze over. You can walk right across right now.","river"), 
    WinterEarlyPlains("There are no animals to hunt, but a resourceful hunter could knock down a snowman for some clothes.","plain");
    
    // class instance variables
           
    private String dialog;
    private String type;
    
    // constructor
    
    CharacterDialog(String dialog, String type) {
        this.dialog = dialog;
        this.type = type;
    }
    
    //getter and setter methods

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    // toString

    @Override
    public String toString() {
        return "CharacterDialog{" + "dialog=" + dialog + ", type=" + type + '}';
    }
    
    
}