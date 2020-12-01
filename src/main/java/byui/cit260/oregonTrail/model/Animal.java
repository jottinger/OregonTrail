/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;


public enum Animal {
/**
 *
 * @author hannahwilliams
 */

    Bison("Bisons are herbivores. They have very poor eyesight but acute hearing and excellent smell.", 1400, "Medium"),
    Wolf("Wolves are carnivoes. They are territorial with an excellent sense of smell and tracking skills.", 100, "Hard"),
    Bear("Bears are omnivores. They can be lazy but vicious if feeling threatened.", 600, "Hard"),
    Rabbit("Rabbits are herbivores. They are fast with a great sense of smell.", 2, "Easy");
    
     // class instance variables
    
    private String description;
    private int baseWeight;
    private String difficulty; 

    private Animal(String description, int baseWeight, String difficulty) {
        this.description = description;
        this.baseWeight = baseWeight;
        this.difficulty = difficulty;
    }

    public int getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(int baseWeight) {
        this.baseWeight = baseWeight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Animal{" + "description=" + description + ", baseWeight=" + baseWeight + ", difficulty=" + difficulty + '}';
    }

    
    
}
    
