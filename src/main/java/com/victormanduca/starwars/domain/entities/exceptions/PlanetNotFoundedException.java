package com.victormanduca.starwars.domain.entities.exceptions;

public class PlanetNotFoundedException extends Exception {
  public PlanetNotFoundedException() {
    super("No planet with the given name/id was founded. Please, make sure that you provided the right resource.");
  }
}
