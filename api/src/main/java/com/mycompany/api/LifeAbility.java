package com.mycompany.api;

public class LifeAbility {

    private int healthPoints;

    public LifeAbility(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void reduceHealthPoints(int healthPoints) {
        this.healthPoints -= healthPoints;
    }

}
