package com.mycompany.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.api.IAssetManager;
import com.mycompany.api.IProcessor;
import com.mycompany.api.IWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.Gdx.graphics;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.graphics.GL20.GL_DEPTH_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.radDeg;

/**
 * @author Emil
 */
@Service
public class GameScreen implements ApplicationListener {

    private List<IProcessor> processors;
    private IAssetManager assetManager;

    private IWorld world;
    private SpriteBatch batch;
    private Viewport viewport;
    private Map<String, Texture> assets = new HashMap<>();

    @Autowired
    public void setWorld(IWorld world) {
        this.world = world;
    }

    @PostConstruct
    public void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "my-gdx-game";
        cfg.useGL30 = true;
        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {
        viewport = new FitViewport(IWorld.WIDTH, IWorld.HEIGHT);
        batch = new SpriteBatch();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("bg5.jpg")) {
            assetManager.loadAsset("bg", stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        viewport.apply(true);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        assetManager.getAssets().forEach((key, bytes) -> assets.put(key, new Texture(new Pixmap(bytes, 0, bytes.length))));
    }

    @Override
    public void resize(final int x, final int y) {
        viewport.update(x, y, true);
    }

    @Override
    public void render() {
        // Update processors
        processors.forEach(processor -> processor.process(graphics.getDeltaTime()));

        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        batch.begin();
        batch.draw(assets.get("bg"), 0, 0, IWorld.WIDTH, IWorld.HEIGHT);
        world.getEntities().forEach(entity -> {
            Texture texture = assets.get(entity.getAsset());
            if (texture == null) return;
            batch.draw(
                    texture,
                    entity.getX() - texture.getWidth() / 2, entity.getY() - texture.getHeight() / 2,
                    texture.getWidth() / 2, texture.getHeight() / 2,
                    texture.getWidth(), texture.getHeight(),
                    1, 1,
                    entity.getRotation() * radDeg,
                    0, 0,
                    texture.getWidth(), texture.getHeight(),
                    false, false
            );
        });
        batch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Autowired
    public void setProcessors(List<IProcessor> processors) {
        this.processors = processors;
    }

    @Autowired
    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

}
