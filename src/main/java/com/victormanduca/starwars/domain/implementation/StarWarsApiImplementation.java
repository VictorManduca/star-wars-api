package com.victormanduca.starwars.domain.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victormanduca.starwars.domain.entities.dtos.ApiResponseDto;
import com.victormanduca.starwars.domain.usecase.IStarWarsApi;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class StarWarsApiImplementation implements IStarWarsApi {

  public ApiResponseDto callPlanetApi(String planetName) throws Exception {
    return this.buildResponseBody(this.getApiResponse(planetName));
  }

  private HttpRequest buildRequest(String planetName) {
    return HttpRequest.newBuilder().GET()
      .uri(URI.create("https://swapi.dev/api/planets/?search=" + planetName))
      .build();
  }

  private String getApiResponse(String planetName) throws Exception {
    return HttpClient.newBuilder().build()
      .send(this.buildRequest(planetName), HttpResponse.BodyHandlers.ofString())
      .body();
  }

  private ApiResponseDto buildResponseBody(String apiResponseBody) throws JsonProcessingException {
    return new ObjectMapper().readValue(apiResponseBody, ApiResponseDto.class);
  }
}
