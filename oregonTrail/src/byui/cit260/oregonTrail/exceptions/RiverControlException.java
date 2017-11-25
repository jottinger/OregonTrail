/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.exceptions;

/**
 *
 * @author jordan
 */
public class RiverControlException extends Exception {

    public RiverControlException() {
    }

    public RiverControlException(String message) {
        super(message);
    }

    public RiverControlException(String message, Throwable cause) {
        super(message, cause);
    }

    public RiverControlException(Throwable cause) {
        super(cause);
    }

    public RiverControlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
