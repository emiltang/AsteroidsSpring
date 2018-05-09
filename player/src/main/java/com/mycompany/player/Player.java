/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.mycompany.player;

import com.mycompany.api.*;

/**
 * @author Emil
 */
public class Player implements IMoveAble, IEntity, ICollideAble {

    private final String asset;
    private final MoveAbility moveAbility;
    private final CollisionAbility collisionAbility;
    private final LifeAbility lifeAbility;
    private final PositionAbility positionAbility;

    Player(final String asset,
           final LifeAbility lifeAbility,
           final MoveAbility moveAbility,
           final CollisionAbility collisionAbility,
           final PositionAbility positionAbility) {
        this.asset = asset;
        this.lifeAbility = lifeAbility;
        this.moveAbility = moveAbility;
        this.collisionAbility = collisionAbility;
        this.positionAbility = positionAbility;
    }

    @Override
    public String getAsset() {
        return asset;
    }

    @Override
    public MoveAbility getMoveAbility() {
        return moveAbility;
    }

    @Override
    public CollisionAbility getCollisionAbility() {
        return collisionAbility;
    }

    public LifeAbility getLifeAbility() {
        return lifeAbility;
    }

    public PositionAbility getPositionAbility() {
        return positionAbility;
    }
}
