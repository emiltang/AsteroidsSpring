package com.mycompany.projectile;

import com.mycompany.api.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ProjectilePlugin implements IProjectileService, IProcessor {

    private final static float ACCELERATION = 1000;
    private final static float DECELERATION = 5;
    private final static float MOVE_SPEED = 1000;
    private final static float ROTATION_SPEED = 0;
    private final static int DAMAGE = 1;
    private final static int HIT_RADIUS = 3;

    private final IWorld world;
    private final IAssetManager assetManager;

    public ProjectilePlugin(final IWorld world,
                            final IAssetManager assetManager) {
        this.world = world;
        this.assetManager = assetManager;
    }

    @PostConstruct
    public void start(){
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("projectile.png")) {
            assetManager.loadAsset("projectile", stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IEntity createProjectile(final float direction, final float x, final float y) {
        MoveAbility moveAbility = new MoveAbility(ACCELERATION, DECELERATION, MOVE_SPEED, ROTATION_SPEED);
        CollisionAbility collisionAbility = new CollisionAbility(DAMAGE, HIT_RADIUS);
        PositionAbility positionAbility = new PositionAbility(x, y, direction);
        moveAbility.setMoveForward(true);
        return new Projectile("projectile", moveAbility, collisionAbility, positionAbility);
    }

    @Override
    public void process(final float deltaTime) {
        for (Projectile projectile : world.getEntities(Projectile.class)) {
            if (!projectile.getCollisionAbility().getCollisions().isEmpty()) {
                world.removeEntity(projectile);
            }
        }
    }

}
