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
public class DisplayMapView extends View{

    public void display() {
        this.console.println("\n *** DisplayMapView called ***");
    }
        @Override
    public boolean doAction(String value) {
        return false;
    }
}
