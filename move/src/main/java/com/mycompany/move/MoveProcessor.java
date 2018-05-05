package com.mycompany.move;

import com.mycompany.api.IMoveAbility;
import com.mycompany.api.IMoveAble;
import com.mycompany.api.IProcessor;
import com.mycompany.api.IWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.*;

/**
 * @author Emil
 */
@Service
public class MoveProcessor implements IProcessor {

    private IWorld world;

    @Override
    public void process(float deltaTime) {
        for (IMoveAble moveAble : world.getEntities(IMoveAble.class)) {

            IMoveAbility moveAbility = moveAble.getMoveAbility();

            // TL;DR: stuff moves
            if (moveAbility.isTurnLeft())
                moveAble.addRotation(moveAbility.getRotationSpeed() * deltaTime);

            if (moveAbility.isTurnRight())
                moveAble.subtractRotation(moveAbility.getRotationSpeed() * deltaTime);

            if (moveAbility.isMoveForward()) {
                moveAbility.translateDx((float) (cos(moveAble.getRotation()) * moveAbility.getAcceleration() * deltaTime));
                moveAbility.translateDy((float) (sin(moveAble.getRotation()) * moveAbility.getAcceleration() * deltaTime));
            }
            float velocity = (float) sqrt(moveAbility.getDx() * moveAbility.getDx() + moveAbility.getDy() * moveAbility.getDy());
            if (velocity > 0) {
                moveAbility.translateDx(-(moveAbility.getDx() / velocity) * moveAbility.getDeceleration() * deltaTime);
                moveAbility.translateDy(-(moveAbility.getDy() / velocity) * moveAbility.getDeceleration() * deltaTime);
            }
            if (velocity > moveAbility.getMaxSpeed()) {
                moveAbility.setDx((moveAbility.getDx() / velocity) * moveAbility.getMaxSpeed());
                moveAbility.setDy((moveAbility.getDy() / velocity) * moveAbility.getMaxSpeed());
            }

            moveAble.translateX(moveAbility.getDx() * deltaTime);
            if (moveAble.getX() > IWorld.WIDTH) {
                moveAble.setX(0);
            } else if (moveAble.getX() < 0) {
                moveAble.setX(IWorld.WIDTH);
            }

            moveAble.translateY(moveAbility.getDy() * deltaTime);
            if (moveAble.getY() > IWorld.HEIGHT) {
                moveAble.setY(0);
            } else if (moveAble.getY() < 0) {
                moveAble.setY(IWorld.HEIGHT);
            }
        }
    }

    @Autowired
    public void setWorld(IWorld world) {
        this.world = world;
    }

}
