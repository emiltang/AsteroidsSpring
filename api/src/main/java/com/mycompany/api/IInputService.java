package com.mycompany.api;

/**
 * @author Emil
 */
public interface IInputService {

    /**
     * @param key key
     * @return key pressed status
     */
    boolean keyDown(Key key);

    enum Key {
        LEFT,
        RIGHT,
        UP
    }

}
