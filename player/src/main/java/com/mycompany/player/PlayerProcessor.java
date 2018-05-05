/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.mycompany.player;

import com.mycompany.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mycompany.api.IInputService.Key.*;

/**
 * @author Emil
 */
@Service
public class PlayerProcessor implements IProcessor {

    private IWorld world;

    private IInputService inputService;

    @Override
    public void process(final float dt) {
        for (Player player : world.getEntities(Player.class)) {

            player.getMoveAbility().setMoveForward(inputService.keyDown(UP));
            player.getMoveAbility().setTurnLeft(inputService.keyDown(LEFT));
            player.getMoveAbility().setTurnRight(inputService.keyDown(RIGHT));

            player.getCollisionAbility().getCollisions().stream()
                    .map(ICollideAble::getCollisionAbility)
                    .map(ICollisionAbility::getDamage)
                    .forEach(player::reduceHealthPoints);

            if (player.getHealthPoints() <= 0)
                world.removeEntity(player);
        }
    }

    @Autowired
    public void setInputService(IInputService inputService) {
        this.inputService = inputService;
    }

    @Autowired
    public void setWorld(IWorld world) {
        this.world = world;
    }
}
