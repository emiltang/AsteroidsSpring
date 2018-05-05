package com.mycompany.api;

/**
 * @author Emil
 */
public interface IProcessor {

    /**
     * @param deltaTime Time in seconds since last frame
     */
    void process(float deltaTime);

}
