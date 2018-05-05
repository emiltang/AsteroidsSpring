package com.mycompany.api;

public interface IMoveAbility {

    float getRotationSpeed();

    float getMaxSpeed();

    float getDeceleration();

    float getAcceleration();

    float getDx();

    void setDx(float dx);

    float getDy();

    void setDy(float dy);

    boolean isTurnLeft();

    void setTurnLeft(boolean turnLeft);

    boolean isTurnRight();

    void setTurnRight(boolean turnRight);

    boolean isMoveForward();

    void setMoveForward(boolean moveForward);

    void translateDx(float dx);

    void translateDy(float dy);

}
