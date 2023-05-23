package com.victormanduca.starwars.presentation;

import com.victormanduca.starwars.domain.entities.PlanetEntity;
import com.victormanduca.starwars.domain.entities.dtos.CreatePlanetRequestDto;
import com.victormanduca.starwars.domain.entities.dtos.PlanetResponseDto;
import com.victormanduca.starwars.domain.entities.dtos.UpdatePlanetRequestDto;
import com.victormanduca.starwars.domain.entities.exceptions.PlanetNotFoundedException;
import com.victormanduca.starwars.domain.usecase.IPlanet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(value = {MockitoExtension.class})
public class PlanetControllerTests {

  @Mock
  private IPlanet planetImplementation;

  @InjectMocks
  private PlanetController planetController;

  @Test
  public void testCreateSuccess() throws Exception {
    final CreatePlanetRequestDto payload = new CreatePlanetRequestDto();
    final ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).build();

    ResponseEntity<String> response = planetController.create(payload);

    Mockito.verify(planetImplementation, Mockito.times(1)).create(payload);
    assertEquals(expectedResponse, response);
  }

  @Test
  public void testFindAll() {
    final int page = 0;
    final int size = 10;
    final Pageable pageable = PageRequest.of(page, size);
    final Page<PlanetEntity> planetPage = Mockito.mock(Page.class);

    Mockito.when(planetImplementation.findAll(pageable)).thenReturn(planetPage);

    final ResponseEntity<PlanetResponseDto> response = planetController.findAll(page, size);

    Mockito.verify(planetImplementation, Mockito.times(1)).findAll(pageable);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    PlanetResponseDto responseBody = response.getBody();
    assertNotNull(responseBody);
    assertEquals(0, responseBody.getContent().size());
    assertEquals(0, responseBody.getTotalElements());
    assertEquals(0, responseBody.getTotalPages());
  }

  @Test
  public void testFindByName() {
    final IPlanet implementation = Mockito.mock(IPlanet.class);
    final PlanetController planetController = new PlanetController(implementation);
    final List<PlanetEntity> planets = new ArrayList<>();
    String name = "Utapau";

    planets.add(buildPlanet());

    Mockito.when(implementation.findBy(name)).thenReturn(planets);

    final ResponseEntity<List<PlanetEntity>> response = planetController.findBy(name);

    Mockito.verify(implementation, Mockito.times(1)).findBy(name);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(planets, response.getBody());
  }

  @Test
  public void testFindById() throws PlanetNotFoundedException {
    final IPlanet implementation = Mockito.mock(IPlanet.class);
    final PlanetController planetController = new PlanetController(implementation);
    final PlanetEntity planet = buildPlanet();
    final int requestId = 0;

    Mockito.when(implementation.findBy(requestId)).thenReturn(planet);

    final ResponseEntity<Object> response = planetController.findBy(requestId);

    Mockito.verify(implementation, Mockito.times(1)).findBy(requestId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(planet, response.getBody());
  }

  @Test
  public void testDeleteById() {
    final int requestId = 0;
    final ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.OK).build();

    ResponseEntity<String> response = planetController.deleteBy(requestId);

    Mockito.verify(planetImplementation, Mockito.times(1)).deleteBy(requestId);
    assertEquals(expectedResponse, response);
  }

  @Test
  public void testUpdateById() throws PlanetNotFoundedException {
    final int requestId = 0;
    final UpdatePlanetRequestDto payload = new UpdatePlanetRequestDto();
    final ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.OK).build();

    ResponseEntity<String> response = planetController.updateBy(requestId, payload);

    Mockito.verify(planetImplementation, Mockito.times(1)).updateBy(requestId, payload);
    assertEquals(expectedResponse, response);
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
}
