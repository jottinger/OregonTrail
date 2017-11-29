/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import java.util.Map;

/**
 *
 * @author Dresen_HP
 */
public class InventoryView extends View {
    // class instance variables
    private Map inventory; // inventory list
    private String promptMessage; // enter choice prompt
    
    // constructor function called from ??? TODO: Would this be called from game menu or from startGame?
    public InventoryView() {
        this.console.println("*** InventoryView() function called ***");
        //this.promptMessage = "Please enter your choice";
        //this.inventory = player.getItems();
    }

    @Override
    public boolean doAction(String value) {
        this.console.println("***doAction stub function called***");
        return false;
    }
}
