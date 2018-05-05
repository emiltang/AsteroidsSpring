package com.mycompany.api;

import java.util.List;

/**
 * @author Emil
 */
public interface IWorld {

    /**
     * Height of World
     */
    int HEIGHT = 2048;
    /**
     * Width of World
     */
    int WIDTH = 2048;

    /**
     * @return A list of all entities in the game
     */
    List<IEntity> getEntities();

    /**
     * @param type Specify the type of entities
     * @param <E>  Entity type
     * @return A list of entities with the specified type
     */
    <E extends IEntity> List<E> getEntities(Class<E> type);

    /**
     * @param entity Entity to add to the game
     */
    void addEntity(IEntity entity);

    /**
     * @param entities List of entities to remove from the game
     * @param <E>      Type of entity
     */
    <E extends IEntity> void removeEntities(List<E> entities);

    void removeEntity(IEntity entity);

}
