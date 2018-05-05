package com.mycompany.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mycompany.api.IInputService;
import org.springframework.stereotype.Service;

@Service
public class InputService implements IInputService {

    @Override
    public boolean keyDown(Key key) {
        switch (key) {
            case RIGHT:
                return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
            case LEFT:
                return Gdx.input.isKeyPressed(Input.Keys.LEFT);
            case UP:
                return Gdx.input.isKeyPressed(Input.Keys.UP);
            default:
                return false;
        }
    }

}
