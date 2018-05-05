package com.mycompany.api;

import java.util.Collection;

public interface ICollisionAbility {

    int getHitRadius();

    int getDamage();

    void addCollision(ICollideAble entity);

    Collection<ICollideAble> getCollisions();

    void clearCollisions();
}
