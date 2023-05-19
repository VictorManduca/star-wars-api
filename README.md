<h1 align="center">Star Wars API</h1>
<p align="center">A Star Wars Planet API</p>

Requisites:
- An REST API
- For each planet, these data need to be retrieved from the database:
  - name
  - weather
  - terrain
  - Number of times that a planet appeared in the movies, from the public Star Wars API (https://swapi.co/)

Desirable functionalities:
  - [x] Add a planet (name, weather and terrain)
  - [X] List planets
  - [X] Search by name
  - [X] Search by ID
  - [X] Remove a planet

## Docs
URLs
- Swagger: http://localhost:8080/swagger-ui/index.html#/
- Planet: http://localhost:8080/v1/planet

Insomnia collections on docs/insomnia_collection

### How to run the project
Just need to run:
```shell
docker-compose up -d
```
And the application will start already connected with the database

### Technologies
 - Java 17
 - Spring
 - Postgres
 - Docker
 - Swagger
