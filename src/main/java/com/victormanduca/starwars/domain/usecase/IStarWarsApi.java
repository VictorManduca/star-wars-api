package com.victormanduca.starwars.domain.usecase;

import com.victormanduca.starwars.domain.entities.dtos.ApiResponseDto;

public interface IStarWarsApi {
  ApiResponseDto callPlanetApi(String planetName) throws Exception;
}
