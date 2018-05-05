package com.mycompany.library;

import com.mycompany.api.IMoveAbility;

public class MoveAbility implements IMoveAbility {

    private final float acceleration;
    private final float deceleration;
    private final float maxSpeed;
    private final float rotationSpeed;

    private float dx;
    private float dy;
    private boolean turnLeft;
    private boolean turnRight;
    private boolean moveForward;

    public MoveAbility(float acceleration,
                       float deceleration,
                       float maxSpeed,
                       float rotationSpeed) {
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.maxSpeed = maxSpeed;
        this.rotationSpeed = rotationSpeed;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getDeceleration() {
        return deceleration;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public boolean isTurnLeft() {
        return turnLeft;
    }

    public void setTurnLeft(boolean turnLeft) {
        this.turnLeft = turnLeft;
    }

    public boolean isTurnRight() {
        return turnRight;
    }

    public void setTurnRight(boolean turnRight) {
        this.turnRight = turnRight;
    }

    public boolean isMoveForward() {
        return moveForward;
    }

    public void setMoveForward(boolean moveForward) {
        this.moveForward = moveForward;
    }

    @Override
    public void translateDx(float dx) {
        this.dx += dx;
    }

    @Override
    public void translateDy(float dy) {
        this.dy += dy;
    }

}
