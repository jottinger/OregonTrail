/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import java.io.PrintWriter;
import oregonTrail.OregonTrail;

/**
 *
 * @author jordan
 */
public class ErrorView {
    
    private static final PrintWriter errorFile = OregonTrail.getOutFile();
    private static final PrintWriter logFile = OregonTrail.getLogFile();
    
    public static void display(String className, String errorMessage) {
        
        errorFile.println(
                "--------------------------------------------------------"
              + "\n- ERROR - " + errorMessage
              + "\n--------------------------------------------------------");
        
        //log error
        logFile.println(className + " - " + errorMessage);
    }
    public void display2(String className, String errorMessage) {
        
    }
}
