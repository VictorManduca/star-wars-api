package com.victormanduca.starwars.domain.implementation;

import com.victormanduca.starwars.domain.entities.PlanetEntity;
import com.victormanduca.starwars.domain.entities.dtos.ApiResponseDto;
import com.victormanduca.starwars.domain.entities.dtos.ApiResponsePlanetDto;
import com.victormanduca.starwars.domain.entities.dtos.CreatePlanetRequestDto;
import com.victormanduca.starwars.domain.entities.dtos.UpdatePlanetRequestDto;
import com.victormanduca.starwars.domain.entities.exceptions.PlanetNotFoundedException;
import com.victormanduca.starwars.domain.usecase.IStarWarsApi;
import com.victormanduca.starwars.infra.repositories.IPlanetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(value = {MockitoExtension.class})
public class PlanetImplementationTest {
  @Mock
  private IPlanetRepository repository;

  @Mock
  private IStarWarsApi starWarsApi;

  @InjectMocks
  private PlanetImplementation implementation;

  @Test
  public void testFindAll() {
    final Pageable pageable = Pageable.ofSize(10).withPage(0);
    final Page<PlanetEntity> page = Mockito.mock(Page.class);
    final List<PlanetEntity> planets = List.of(buildPlanet());

    Mockito.when(page.getContent()).thenReturn(planets);
    Mockito.when(repository.findAll(pageable)).thenReturn(page);

    Page<PlanetEntity> result = implementation.findAll(pageable);
    assertEquals(planets, result.getContent());
  }

  @Test
  public void testFindByName() {
    final PlanetEntity planet = buildPlanet();
    final List<PlanetEntity> planets = List.of(planet);

    Mockito.when(repository.findByName(planet.getName())).thenReturn(planets);

    List<PlanetEntity> result = implementation.findBy(planet.getName());
    assertEquals(planets, result);
  }

  @Test
  public void testFindById() throws PlanetNotFoundedException {
    final int requestId = 0;
    final PlanetEntity planet = buildPlanet();

    Mockito.when(repository.findById(requestId)).thenReturn(Optional.of(planet));

    PlanetEntity result = implementation.findBy(requestId);
    assertEquals(planet, result);
  }

  @Test
  public void testDeleteById() {
    final int requestId = 0;
    implementation.deleteBy(requestId);
    Mockito.verify(repository, Mockito.times(1)).deleteById(requestId);
  }

  @Test
  public void testUpdateById() throws PlanetNotFoundedException {
    final int requestId = 1;
    final UpdatePlanetRequestDto payload = buildUpdatePayload();
    final PlanetEntity planet = buildPlanet();
    final PlanetEntity updatedPlanet = updatedPlanet(payload);

    Mockito.when(repository.findById(requestId)).thenReturn(Optional.of(planet));

    implementation.updateBy(requestId, payload);

    Mockito.verify(repository, Mockito.times(1)).findById(requestId);
    Mockito.verify(repository, Mockito.times(1)).save(updatedPlanet);
  }

  @Test
  public void testCreate() throws Exception {
    final CreatePlanetRequestDto payload = buildCreatePayload();
    final ApiResponseDto apiResponse = buildApiStarWarsResponse();
    final PlanetEntity planet = buildPlanet();

    Mockito.when(starWarsApi.callPlanetApi(payload.getName())).thenReturn(apiResponse);

    implementation.create(payload);

    Mockito.verify(repository, Mockito.times(1)).save(planet);
  }

  private PlanetEntity buildPlanet() {
    final PlanetEntity planet = new PlanetEntity();
    planet.setId(0);
    planet.setWeather("arid");
    planet.setTerrain("desert");
    planet.setName("Utapau");
    planet.setAppearedInFilms(1);

    return planet;
  }

  private PlanetEntity updatedPlanet(UpdatePlanetRequestDto payload) {
    final PlanetEntity planet = new PlanetEntity();
    planet.setId(0);
    planet.setWeather(payload.getWeather());
    planet.setTerrain(payload.getTerrain());
    planet.setName("Utapau");
    planet.setAppearedInFilms(1);

    return planet;
  }

  private UpdatePlanetRequestDto buildUpdatePayload() {
    final UpdatePlanetRequestDto payload = new UpdatePlanetRequestDto();
    payload.setWeather("frozen");
    payload.setTerrain("terrainProperty");

    return payload;
  }

  private CreatePlanetRequestDto buildCreatePayload() {
    final CreatePlanetRequestDto payload = new CreatePlanetRequestDto();
    payload.setWeather("frozen");
    payload.setTerrain("terrainProperty");
    payload.setName("Utapau");

    return payload;
  }

  private ApiResponseDto buildApiStarWarsResponse() {
    final ApiResponseDto response = new ApiResponseDto();
    final List<ApiResponsePlanetDto> responseResultContent = new ArrayList<>();

    List<String> movies = new ArrayList<>();
    movies.add("A New Hope");

    ApiResponsePlanetDto planetDto = new ApiResponsePlanetDto();
    planetDto.setFilms(movies);

    responseResultContent.add(planetDto);
    response.setResults(responseResultContent);

    return response;
  }
}
