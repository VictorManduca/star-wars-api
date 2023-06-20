package com.victormanduca.starwars.domain.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePlanetRequestDto {
  private String name;
  private String weather;
  private String terrain;

  public CreatePlanetRequestDto() {}

  public CreatePlanetRequestDto(String name, String weather, String terrain) {
    this.name = name;
    this.weather = weather;
    this.terrain = terrain;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWeather() {
    return weather;
  }

  public void setWeather(String weather) {
    this.weather = weather;
  }

  public String getTerrain() {
    return terrain;
  }

  public void setTerrain(String terrain) {
    this.terrain = terrain;
  }
}
