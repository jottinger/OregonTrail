/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

/**
 *
 * @author Dresen_HP
 */
class HireGuideView extends View{

    public void display() {
        this.console.println("\n *** HireGuideView called ***");    }

    @Override
    public boolean doAction(String value) {
        this.console.println("\n *** doAction called ***");
        return false;
    }
    
}  
