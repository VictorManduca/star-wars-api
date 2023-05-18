package com.victormanduca.starwars.infra.repositories;

import com.victormanduca.starwars.domain.entities.PlanetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPlanetRepository extends JpaRepository<PlanetEntity, Integer> {

  @Query(value = "SELECT * FROM planet WHERE LOWER(name) LIKE LOWER(concat('%', ?1, '%'))", nativeQuery = true)
  List<PlanetEntity> findByName(String name);
}
