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
    String url = "https://swapi.dev/api/planets/?search=" + planetName;
    final HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(url))
            .build();

    final String response = HttpClient
            .newBuilder()
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString())
            .body();

    return this.buildResponseBody(response);
  }

  private ApiResponseDto buildResponseBody(String apiResponseBody) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(apiResponseBody, ApiResponseDto.class);
  }
}
