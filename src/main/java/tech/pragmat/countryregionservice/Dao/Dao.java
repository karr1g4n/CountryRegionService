package tech.pragmat.countryregionservice.Dao;

import java.util.List;

public interface Dao<Entity,id> {
    public boolean update(Entity entity);

    public boolean deleteByCountry(String name);

    public void create(Entity entity);

    public List<Entity> findAll();

    public Entity getById(id id);

}
