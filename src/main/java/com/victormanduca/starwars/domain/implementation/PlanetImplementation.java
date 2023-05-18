package com.victormanduca.starwars.domain.implementation;

import com.victormanduca.starwars.domain.entities.PlanetEntity;
import com.victormanduca.starwars.domain.entities.dtos.ApiResponseDto;
import com.victormanduca.starwars.domain.entities.dtos.ApiResponsePlanetDto;
import com.victormanduca.starwars.domain.entities.dtos.PlanetRequestDto;
import com.victormanduca.starwars.domain.entities.exceptions.PlanetNotFoundedException;
import com.victormanduca.starwars.domain.usecase.IPlanet;
import com.victormanduca.starwars.domain.usecase.IStarWarsApi;
import com.victormanduca.starwars.infra.repositories.IPlanetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanetImplementation implements IPlanet {
  private final IPlanetRepository planetRepository;
  private final IStarWarsApi starWarsApi;

  public PlanetImplementation(IPlanetRepository planetRepository, IStarWarsApi starWarsApi) {
    this.planetRepository = planetRepository;
    this.starWarsApi = starWarsApi;
  }

  public void create(PlanetRequestDto payload) throws Exception {
    final int appearedAmount = this.getAppearedFilms(payload.getName());
    final PlanetEntity planet = new PlanetEntity();

    planet.setName(payload.getName());
    planet.setTerrain(payload.getTerrain());
    planet.setWeather(payload.getWeather());
    planet.setAppearedInFilms(appearedAmount);

    this.planetRepository.save(planet);
  }

  public Page<PlanetEntity> findAll(Pageable pageable) {
    return this.planetRepository.findAll(pageable);
  }

  public List<PlanetEntity> findBy(String name) {
    return this.planetRepository.findByName(name);
  }

  public PlanetEntity findBy(int id) throws PlanetNotFoundedException {
    return this.planetRepository.findById(id).orElseThrow(PlanetNotFoundedException::new);
  }

  public void deleteBy(int id) {
    this.planetRepository.deleteById(id);
  }

  private int getAppearedFilms(String planetName) throws Exception {
    final ApiResponseDto response = starWarsApi.callPlanetApi(planetName);
    final List<ApiResponsePlanetDto> apiResults = response.getResults();

    if (apiResults.size() != 1) {
      throw new PlanetNotFoundedException();
    }

    final ApiResponsePlanetDto planet = apiResults.get(0);
    return planet.getFilms().size();
  }
}
