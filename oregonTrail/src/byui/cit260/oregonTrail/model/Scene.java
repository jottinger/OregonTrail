/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.Point;
/**
 *
 * @author jones-jordan
 */
public class Scene implements Serializable {
    private String description;
    private String name;
    private String option1;
    private boolean activityDone;
    
    

    // default constructor

    public Scene() {
    }
      

    // methods

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public boolean isActivityDone() {
        return activityDone;
    }

    public void setActivityDone(boolean activityDone) {
        this.activityDone = activityDone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.option1);
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
        final Scene other = (Scene) obj;
        if (this.activityDone != other.activityDone) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.option1, other.option1)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Scene{" + "description=" + description + ", name=" + name + ", option1=" + option1 + ", activityDone=" + activityDone + '}';
    }
    
   
   
}