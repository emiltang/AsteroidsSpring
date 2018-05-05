/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.mycompany.player;

import com.mycompany.api.IAssetManager;
import com.mycompany.api.ICollisionAbility;
import com.mycompany.api.IMoveAbility;
import com.mycompany.api.IWorld;
import com.mycompany.library.CollisionAbility;
import com.mycompany.library.MoveAbility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Math.PI;
import static java.lang.Math.random;

/**
 * @author Emil
 */
@Service
public class PlayerPlugin {

    private static final String ASSET_KEY = "player";
    private static final String ASSET_PATH = "spaceship.png";
    private static final float ACCELERATION = 64;
    private static final float DECELERATION = 8;
    private static final float MAX_SPEED = 512;
    private static final float ROTATION_SPEED = 2;
    private static final int HEALTH_POINTS = 2;
    private static final int DAMAGE = 0;
    private static final int HIT_RADIUS = 100;

    private IAssetManager assetManager;
    private IWorld world;

    @PostConstruct
    public void start() {
        IMoveAbility moveAbility = new MoveAbility(ACCELERATION, DECELERATION, MAX_SPEED, ROTATION_SPEED);
        ICollisionAbility collisionAbility = new CollisionAbility(DAMAGE, HIT_RADIUS);
        Player player = new Player(ASSET_KEY, HEALTH_POINTS, moveAbility, collisionAbility);

        player.setX(IWorld.WIDTH / 2);
        player.setY(IWorld.HEIGHT / 2);
        player.setRotation((float) (random() * 2 * PI));
        world.addEntity(player);

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(ASSET_PATH)) {
            assetManager.loadAsset(ASSET_KEY, stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Autowired
    public void setWorld(IWorld world) {
        this.world = world;
    }
}
