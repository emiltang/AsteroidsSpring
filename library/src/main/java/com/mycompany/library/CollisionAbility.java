package com.mycompany.library;

import com.mycompany.api.ICollideAble;
import com.mycompany.api.ICollisionAbility;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class CollisionAbility implements ICollisionAbility {

    private final Set<ICollideAble> collisions = new HashSet<>();
    private final int damage;
    private final int hitRadius;

    public CollisionAbility(int damage, int hitRadius) {
        this.damage = damage;
        this.hitRadius = hitRadius;
    }

    public int getHitRadius() {
        return hitRadius;
    }

    public int getDamage() {
        return damage;
    }

    public void addCollision(ICollideAble entity) {
        collisions.add(entity);
    }

    public Collection<ICollideAble> getCollisions() {
        return collisions;
    }

    public void clearCollisions() {
        collisions.clear();
    }

}
