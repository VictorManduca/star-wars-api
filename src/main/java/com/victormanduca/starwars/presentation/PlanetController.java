package com.victormanduca.starwars.presentation;

import com.victormanduca.starwars.domain.entities.PlanetEntity;
import com.victormanduca.starwars.domain.entities.dtos.CreatePlanetRequestDto;
import com.victormanduca.starwars.domain.entities.dtos.PlanetResponseDto;
import com.victormanduca.starwars.domain.entities.dtos.UpdatePlanetRequestDto;
import com.victormanduca.starwars.domain.entities.exceptions.PlanetNotFoundedException;
import com.victormanduca.starwars.domain.usecase.IPlanet;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/planet")
public class PlanetController {
  private final IPlanet implementation;

  public PlanetController(IPlanet implementation) {
    this.implementation = implementation;
  }

  @PostMapping
  public ResponseEntity<String> create(@Valid @RequestBody CreatePlanetRequestDto payload) {
    try {
      this.implementation.create(payload);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (PlanetNotFoundedException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + exception.getMessage());
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + exception.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<PlanetResponseDto> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    final Pageable pageable = PageRequest.of(page, size);
    final Page<PlanetEntity> planets = this.implementation.findAll(pageable);
    final PlanetResponseDto response = new PlanetResponseDto(planets);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping(value = "/name/{name}")
  public ResponseEntity<List<PlanetEntity>> findBy(@PathVariable String name) {
    final List<PlanetEntity> planets = this.implementation.findBy(name);
    return ResponseEntity.status(HttpStatus.OK).body(planets);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> findBy(@PathVariable int id) {
    try {
      final PlanetEntity planet = this.implementation.findBy(id);
      return ResponseEntity.status(HttpStatus.OK).body(planet);
    } catch (PlanetNotFoundedException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + exception.getMessage());
    }
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteBy(@PathVariable int id) {
    this.implementation.deleteBy(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PatchMapping(value = "/{id}")
  public ResponseEntity<String> updateBy(@PathVariable int id, @Valid @RequestBody UpdatePlanetRequestDto payload) {
    try {
      this.implementation.updateBy(id, payload);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (PlanetNotFoundedException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + exception.getMessage());
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + exception.getMessage());
    }
  }
}
