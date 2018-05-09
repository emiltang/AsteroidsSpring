/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.mycompany.player;

import com.mycompany.api.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

import static com.mycompany.api.IInputService.Key;
import static java.lang.Math.*;

/**
 * @author Emil
 */
@Service
public class PlayerPlugin implements IProcessor {

    private static final String ASSET_KEY = "player";
    private static final String ASSET_PATH = "spaceship.png";
    private static final float ACCELERATION = 64;
    private static final float DECELERATION = 8;
    private static final float MAX_SPEED = 512;
    private static final float ROTATION_SPEED = 2;
    private static final int HEALTH_POINTS = 2;
    private static final int DAMAGE = 0;
    private static final int HIT_RADIUS = 100;

    private final IAssetManager assetManager;
    private final IWorld world;
    private final IInputService inputService;
    private final IProjectileService projectileService;

    public PlayerPlugin(final IAssetManager assetManager,
                        final IWorld world,
                        final IInputService inputService,
                        final IProjectileService projectileService) {
        this.assetManager = assetManager;
        this.world = world;
        this.inputService = inputService;
        this.projectileService = projectileService;
    }

    @PostConstruct
    public void start() {
        PositionAbility positionAbility = new PositionAbility(IWorld.WIDTH / 2, IWorld.HEIGHT / 2, (float) (random() * 2 * PI));
        MoveAbility moveAbility = new MoveAbility(ACCELERATION, DECELERATION, MAX_SPEED, ROTATION_SPEED);
        CollisionAbility collisionAbility = new CollisionAbility(DAMAGE, HIT_RADIUS);
        LifeAbility lifeAbility = new LifeAbility(HEALTH_POINTS);

        Player player = new Player(ASSET_KEY, lifeAbility, moveAbility, collisionAbility, positionAbility);

        world.addEntity(player);

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(ASSET_PATH)) {
            assetManager.loadAsset(ASSET_KEY, stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(final float dt) {
        for (Player player : world.getEntities(Player.class)) {

            MoveAbility move = player.getMoveAbility();
            PositionAbility position = player.getPositionAbility();
            CollisionAbility collision = player.getCollisionAbility();
            LifeAbility life = player.getLifeAbility();

            move.setMoveForward(inputService.keyDown(Key.UP));
            move.setTurnLeft(inputService.keyDown(Key.LEFT));
            move.setTurnRight(inputService.keyDown(Key.RIGHT));

            collision.getCollisions().stream()
                    .map(ICollideAble::getCollisionAbility)
                    .map(CollisionAbility::getDamage)
                    .forEach(life::reduceHealthPoints);

            if (life.getHealthPoints() <= 0)
                world.removeEntity(player);

            if (inputService.keyDown(Key.SPACE)) {
                world.addEntity(projectileService.createProjectile(position.getRotation(),
                        (float) (cos(position.getRotation()) * 150 + position.getX()),
                        (float) (sin(position.getRotation()) * 150 + position.getY())));
            }
        }
    }

}
