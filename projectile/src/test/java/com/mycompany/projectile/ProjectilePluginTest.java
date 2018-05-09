package com.mycompany.projectile;

import com.mycompany.api.IAssetManager;
import com.mycompany.api.IEntity;
import com.mycompany.api.IWorld;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectilePluginTest {

    @Mock
    private IWorld world;

    @Mock
    private IAssetManager assetManager;

    @Test
    void createProjectile() {
        ProjectilePlugin projectilePlugin = new ProjectilePlugin(world, assetManager);
        IEntity projectile = projectilePlugin.createProjectile(1, 100, 100);
        assertAll(
                () -> assertEquals(100, projectile.getPositionAbility().getX()),
                () -> assertEquals(100, projectile.getPositionAbility().getY()),
                () -> assertEquals(1, projectile.getPositionAbility().getRotation())
        );
    }
}
