/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;

import java.io.Serializable;

/**
 *
 * @author Dresen_HP
 */
public class StartScene extends Scene implements Serializable{
    private boolean activityDone;
    
    public StartScene() {
    
}

    public boolean isActivityDone() {
        return activityDone;
    }

    public void setActivityDone(boolean activityDone) {
        this.activityDone = activityDone;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.activityDone ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StartScene other = (StartScene) obj;
        if (this.activityDone != other.activityDone) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StartScene{" + "activityDone=" + activityDone + '}';
    }
    
    
}
