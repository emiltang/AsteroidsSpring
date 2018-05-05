package com.mycompany.world;

import com.mycompany.api.IEntity;
import com.mycompany.api.IWorld;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Emil
 */
@Service
public class World implements IWorld {

    private final List<IEntity> entities = new ArrayList<>();

    @Override
    public List<IEntity> getEntities() {
        return entities;
    }

    @Override
    public <E extends IEntity> List<E> getEntities(Class<E> type) {
        return entities.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(toList());
    }

    @Override
    public void addEntity(IEntity entity) {
        entities.add(entity);
    }

    @Override
    public <E extends IEntity> void removeEntities(List<E> entities) {
        this.entities.removeAll(entities);
    }

    @Override
    public void removeEntity(IEntity entity) {
        entities.remove(entity);
    }

}
