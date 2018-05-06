package io.rowtech.api.service;

import io.rowtech.api.domain.Hero;

import java.util.List;
import java.util.Optional;

public interface HeroesService {
    Optional<Hero> getOne(Integer id);

    List<Hero> getAll();

    Hero create(Hero entity);

    void update(Hero entity);

    void delete(Integer id);
}
