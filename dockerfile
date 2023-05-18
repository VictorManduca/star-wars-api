FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY . .
ENTRYPOINT ["java", "-jar", "build/libs/starwars-0.0.1-SNAPSHOT.jar"]
