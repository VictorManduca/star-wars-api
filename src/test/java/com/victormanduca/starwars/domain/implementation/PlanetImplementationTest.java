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

    Mockito.when(page.getContent()).thenReturn(List.of(buildPlanet()));
    Mockito.when(repository.findAll(pageable)).thenReturn(page);

    Page<PlanetEntity> result = implementation.findAll(pageable);
    assertEquals(List.of(buildPlanet()), result.getContent());
  }

  @Test
  public void testFindByName() {
    final PlanetEntity planet = buildPlanet();

    Mockito.when(repository.findByName(planet.getName())).thenReturn(List.of(planet));
    List<PlanetEntity> result = implementation.findBy(planet.getName());

    assertEquals(List.of(planet), result);
  }

  @Test
  public void testFindById() throws PlanetNotFoundedException {
    final PlanetEntity planet = buildPlanet();

    Mockito.when(repository.findById(this.getRequestId())).thenReturn(Optional.of(planet));
    PlanetEntity result = implementation.findBy(this.getRequestId());

    assertEquals(planet, result);
  }

  @Test
  public void testDeleteById() {
    implementation.deleteBy(this.getRequestId());
    Mockito.verify(repository, Mockito.times(1)).deleteById(this.getRequestId());
  }

  @Test
  public void testUpdateById() throws PlanetNotFoundedException {
    final UpdatePlanetRequestDto payload = buildUpdatePayload();

    Mockito.when(repository.findById(this.getRequestId())).thenReturn(Optional.of(buildPlanet()));

    implementation.updateBy(this.getRequestId(), payload);

    Mockito.verify(repository, Mockito.times(1)).findById(this.getRequestId());
    Mockito.verify(repository, Mockito.times(1)).save(updatedPlanet(payload));
  }

  @Test
  public void testCreate() throws Exception {
    final CreatePlanetRequestDto payload = buildCreatePayload();

    Mockito.when(starWarsApi.callPlanetApi(payload.getName())).thenReturn(buildApiStarWarsResponse());
    implementation.create(payload);

    Mockito.verify(repository, Mockito.times(1)).save(buildPlanet());
  }

  private int getRequestId() {
    return 0;
  }

  private PlanetEntity buildPlanet() {
    return new PlanetEntity(0, "Utapau", "arid", "desert", 1);
  }

  private PlanetEntity updatedPlanet(UpdatePlanetRequestDto payload) {
    return new PlanetEntity(0, "Utapau", payload.getWeather(), payload.getTerrain(), 1);
  }

  private UpdatePlanetRequestDto buildUpdatePayload() {
    return new UpdatePlanetRequestDto("frozen", "terrainProperty");
  }

  private CreatePlanetRequestDto buildCreatePayload() {
    return new CreatePlanetRequestDto("Utapau", "frozen", "terrainProperty");
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
