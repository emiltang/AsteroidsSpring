package com.mycompany.move;

import com.mycompany.api.*;
import org.springframework.stereotype.Service;

import static java.lang.Math.*;

/**
 * @author Emil
 */
@Service
public class MoveProcessor implements IProcessor {

    private final IWorld world;

    public MoveProcessor(IWorld world) {
        this.world = world;
    }

    @Override
    public void process(float deltaTime) {
        for (IMoveAble moveAble : world.getEntities(IMoveAble.class)) {

            MoveAbility move = moveAble.getMoveAbility();
            PositionAbility position = moveAble.getPositionAbility();

            // TL;DR: stuff moves
            if (move.isTurnLeft())
                position.addRotation(move.getRotationSpeed() * deltaTime);

            if (move.isTurnRight())
                position.subtractRotation(move.getRotationSpeed() * deltaTime);

            if (move.isMoveForward()) {
                move.translateDx((float) (cos(position.getRotation()) * move.getAcceleration() * deltaTime));
                move.translateDy((float) (sin(position.getRotation()) * move.getAcceleration() * deltaTime));
            }
            float velocity = (float) sqrt(move.getDx() * move.getDx() + move.getDy() * move.getDy());
            if (velocity > 0) {
                move.translateDx(-(move.getDx() / velocity) * move.getDeceleration() * deltaTime);
                move.translateDy(-(move.getDy() / velocity) * move.getDeceleration() * deltaTime);
            }
            if (velocity > move.getMaxSpeed()) {
                move.setDx((move.getDx() / velocity) * move.getMaxSpeed());
                move.setDy((move.getDy() / velocity) * move.getMaxSpeed());
            }

            position.translateX(move.getDx() * deltaTime);
            if (position.getX() > IWorld.WIDTH) {
                position.setX(0);
            } else if (position.getX() < 0) {
                position.setX(IWorld.WIDTH);
            }

            position.translateY(move.getDy() * deltaTime);
            if (position.getY() > IWorld.HEIGHT) {
                position.setY(0);
            } else if (position.getY() < 0) {
                position.setY(IWorld.HEIGHT);
            }
        }
    }
}
