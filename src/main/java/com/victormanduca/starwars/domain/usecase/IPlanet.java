package com.victormanduca.starwars.domain.usecase;

import com.victormanduca.starwars.domain.entities.PlanetEntity;
import com.victormanduca.starwars.domain.entities.dtos.PlanetRequestDto;
import com.victormanduca.starwars.domain.entities.exceptions.PlanetNotFoundedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlanet {
  void create(PlanetRequestDto request) throws Exception;

  Page<PlanetEntity> findAll(Pageable pageable);

  List<PlanetEntity> findBy(String name);

  PlanetEntity findBy(int id) throws PlanetNotFoundedException;

  void deleteBy(int id);
}
