/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.mycompany.collision;

import com.mycompany.api.ICollideAble;
import com.mycompany.api.IProcessor;
import com.mycompany.api.IWorld;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.pow;

/**
 * @author emil
 */
@Service
public class CollisionProcessor implements IProcessor {

    private final IWorld world;

    public CollisionProcessor(IWorld world) {
        this.world = world;
    }

    @Override
    public void process(float deltaTime) {
        List<ICollideAble> entities = world.getEntities(ICollideAble.class);
        for (ICollideAble collideAble0 : entities) {
            collideAble0.getCollisionAbility().clearCollisions();
            for (ICollideAble collideAble1 : entities)
                if (collideAble0 != collideAble1
                        && pow(collideAble0.getCollisionAbility().getHitRadius()
                        + collideAble1.getCollisionAbility().getHitRadius(), 2)
                        > pow(collideAble0.getPositionAbility().getX()
                        - collideAble1.getPositionAbility().getX(), 2)
                        + pow(collideAble0.getPositionAbility().getY()
                        - collideAble1.getPositionAbility().getY(), 2))
                    collideAble0.getCollisionAbility().addCollision(collideAble1);

        }
    }

}
