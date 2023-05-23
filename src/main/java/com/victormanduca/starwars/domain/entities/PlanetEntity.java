package com.victormanduca.starwars.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "planet")
public class PlanetEntity implements Serializable {
  static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String weather;
  private String terrain;

  @Column(name = "appeared_in_films")
  private int appearedInFilms;

  public PlanetEntity() {
  }

  public PlanetEntity(int id, String name, String weather, String terrain, int appearedInFilms) {
    this.id = id;
    this.name = name;
    this.weather = weather;
    this.terrain = terrain;
    this.appearedInFilms = appearedInFilms;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public int getAppearedInFilms() {
    return appearedInFilms;
  }

  public void setAppearedInFilms(int appearedInFilms) {
    this.appearedInFilms = appearedInFilms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PlanetEntity that = (PlanetEntity) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "PlanetEntity{" + "id=" + id + ", name='" + name + '\'' + ", weather='" + weather + '\'' + ", terrain='" + terrain + '\'' + ", appearedInFilms=" + appearedInFilms + '}';
  }
}
