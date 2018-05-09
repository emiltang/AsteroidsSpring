package com.mycompany.asteroid;

import com.mycompany.api.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import static java.lang.Math.PI;
import static java.lang.Math.random;

/**
 * @author emil
 */
@Service
public class AsteroidPlugin implements IProcessor {

    private static final String BIG_ASTEROID = "asteroid";
    private static final String MEDIUM_ASTEROID = "asteroid-half";
    private static final String SMALL_ASTEROID = "asteroid-quarter";
    private static final LinkedHashMap<String, String> ASSET_MAP = new LinkedHashMap<>();

    private static final float ACCELERATION = Integer.MAX_VALUE;
    private static final float DECELERATION = 0;
    private static final float MAX_SPEED = 20;
    private static final float ROTATION_SPEED = 0;
    private static final int NUM_ASTEROIDS = 10;
    private static final int DAMAGE = 100;
    private static final int HIT_RADIUS = 64;

    static {
        ASSET_MAP.put(BIG_ASTEROID, "asteroid.png");
        ASSET_MAP.put(MEDIUM_ASTEROID, "asteroid-half.png");
        ASSET_MAP.put(SMALL_ASTEROID, "asteroid-quarter.png");
    }

    private final IAssetManager assetManager;
    private final IWorld world;

    public AsteroidPlugin(final IAssetManager assetManager,
                          final IWorld world) {
        this.assetManager = assetManager;
        this.world = world;
    }

    private Asteroid createAsteroid(final String size, final float x, final float y) {
        MoveAbility moveAbility = new MoveAbility(ACCELERATION, DECELERATION, MAX_SPEED, ROTATION_SPEED);
        CollisionAbility collisionAbility = new CollisionAbility(DAMAGE, HIT_RADIUS);
        PositionAbility positionAbility = new PositionAbility(x, y, (float) (random() * 2 * PI));
        LifeAbility lifeAbility = new LifeAbility(2);

        Asteroid asteroid = new Asteroid(size, moveAbility, collisionAbility, positionAbility, lifeAbility);
        asteroid.getMoveAbility().setMoveForward(true);
        return asteroid;
    }

    @PostConstruct
    public void start() {
        ASSET_MAP.forEach((key, path) -> {
            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(path)) {
                assetManager.loadAsset(key, stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < NUM_ASTEROIDS; i++) {
            world.addEntity(createAsteroid(BIG_ASTEROID, (float) (random() * IWorld.WIDTH), (float) (random() * IWorld.WIDTH)));
        }
    }

    @Override
    public void process(float deltaTime) {

        for (Asteroid asteroid : world.getEntities(Asteroid.class)) {

            CollisionAbility collision = asteroid.getCollisionAbility();
            LifeAbility life = asteroid.getLifeAbility();
            PositionAbility position = asteroid.getPositionAbility();

            collision.getCollisions().stream()
                    .map(ICollideAble::getCollisionAbility)
                    .map(CollisionAbility::getDamage)
                    .forEach(life::reduceHealthPoints);

            if (life.getHealthPoints() <= 0) {
                if (asteroid.getAsset().equals(BIG_ASTEROID)) {
                    world.addEntity(createAsteroid(MEDIUM_ASTEROID, position.getX(), position.getY()));
                    world.addEntity(createAsteroid(MEDIUM_ASTEROID, position.getX(), position.getY()));
                } else if (asteroid.getAsset().equals(MEDIUM_ASTEROID)) {
                    world.addEntity(createAsteroid(SMALL_ASTEROID, position.getX(), position.getY()));
                    world.addEntity(createAsteroid(SMALL_ASTEROID, position.getX(), position.getY()));
                }
                world.removeEntity(asteroid);
            }
        }
    }
}
