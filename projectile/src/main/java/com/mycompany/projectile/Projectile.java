package com.mycompany.projectile;

import com.mycompany.api.*;

public class Projectile implements ICollideAble, IMoveAble {

    private final String asset;
    private final CollisionAbility collisionAbility;
    private final MoveAbility moveAbility;
    private final PositionAbility positionAbility;

    Projectile(final String asset,
               final MoveAbility moveAbility,
               final CollisionAbility collisionAbility,
               final PositionAbility positionAbility) {
        this.asset = asset;
        this.collisionAbility = collisionAbility;
        this.moveAbility = moveAbility;
        this.positionAbility = positionAbility;
    }

    @Override
    public String getAsset() {
        return asset;
    }

    @Override
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

}
