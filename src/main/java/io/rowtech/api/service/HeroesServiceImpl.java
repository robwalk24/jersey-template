package io.rowtech.api.service;

import io.rowtech.api.dao.CrudDao;
import io.rowtech.api.domain.Hero;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class HeroesServiceImpl implements HeroesService {
    private final CrudDao<Hero> heroesDao;

    @Inject
    public HeroesServiceImpl(CrudDao<Hero> heroesDao){
        this.heroesDao = heroesDao;
    }

    @Override
    public Optional<Hero> getOne(Integer id) {
        return heroesDao.get(id);
    }

    @Override
    public List<Hero> getAll() {
        return heroesDao.getAll();
    }

    @Override
    public Hero create(Hero entity ) {
        return heroesDao.create(entity);
    }

    @Override
    public void update(Hero entity) {
        heroesDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        heroesDao.delete(id);
    }
}
