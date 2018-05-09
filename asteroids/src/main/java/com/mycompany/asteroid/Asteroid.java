package com.mycompany.asteroid;

import com.mycompany.api.*;

/**
 * @author emil
 */
public class Asteroid implements IMoveAble, ICollideAble {

    private final String asset;
    private final CollisionAbility collisionAbility;
    private final MoveAbility moveAbility;
    private final PositionAbility positionAbility;
    private final LifeAbility lifeAbility;

    Asteroid(final String asset,
             final MoveAbility moveAbility,
             final CollisionAbility collisionAbility,
             final PositionAbility positionAbility, LifeAbility lifeAbility) {
        this.asset = asset;
        this.collisionAbility = collisionAbility;
        this.moveAbility = moveAbility;
        this.positionAbility = positionAbility;
        this.lifeAbility = lifeAbility;
    }

    @Override
    public String getAsset() {
        return asset;
    }

    public PositionAbility getPositionAbility() {
        return positionAbility;
    }

    @Override
    public CollisionAbility getCollisionAbility() {
        return collisionAbility;
    }

    @Override
    public MoveAbility getMoveAbility() {
        return moveAbility;
    }

    public LifeAbility getLifeAbility() {
        return lifeAbility;
    }

}
