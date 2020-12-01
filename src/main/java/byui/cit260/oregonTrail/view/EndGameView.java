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
class EndGameView extends View {
    
    public EndGameView() {
        super("Are you sure you want to quit the game? Y/N");
    }
    
    @Override
    public boolean doAction(String value) {
      value = value.toUpperCase();

        switch (value) {
            case "Y":
                System.exit(0);
                break;
            case "N":
                MainMenuView mainMenu = new MainMenuView();
                break;
            default:
                ErrorView.display(this.getClass().getName(), "Error reading input: Invalid entry try again.");
                
        
    } return false;
}}


    

