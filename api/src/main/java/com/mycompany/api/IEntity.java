package com.mycompany.api;

/**
 * @author Emil
 */
public interface IEntity {

    /**
     * @return Asset name
     */
    String getAsset();

    /**
     * @return x coordinate in game units
     */
    float getX();

    /**
     * @param x x coordinate in game units
     */
    void setX(float x);

    /**
     * @return y coordinate in game units
     */
    float getY();

    /**
     * @param y y coordinate in game units
     */
    void setY(float y);

    /**
     * @return entity rotation in radians
     */
    float getRotation();

    /**
     * @param rotation entity rotation in radians
     */
    void setRotation(float rotation);

    void addRotation(float rotation);

    void subtractRotation(float rotation);

    void translateX(float x);

    void translateY(float y);
}
