package com.victormanduca.starwars.domain.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePlanetRequestDto {
  private String weather;
  private String terrain;

  public UpdatePlanetRequestDto() {
  }

  public UpdatePlanetRequestDto(String weather, String terrain) {
    this.weather = weather;
    this.terrain = terrain;
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
