package com.mycompany.api;

/**
 * Representation of an enmity's position in the game
 *
 * @author emil
 */
public class PositionAbility {

    private float x;
    private float y;
    private float rotation;

    /**
     * @param x        horizontal position to start in world
     * @param y        vertical position to start in world
     * @param rotation direction to point
     */
    public PositionAbility(float x, float y, float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    /**
     * @return rotation
     */
    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void addRotation(float rotation) {
        this.rotation += rotation;
    }

    public void subtractRotation(float rotation) {
        this.rotation -= rotation;
    }

    public void translateX(float x) {
        this.x += x;
    }

    public void translateY(float y) {
        this.y += y;
    }

}
