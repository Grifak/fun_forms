FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /build
COPY . /build/
RUN mvn package

FROM openjdk:17-alpine

ENV SERVER_PORT="8080"

WORKDIR /app
COPY --from=build /build/target/*.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]