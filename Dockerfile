FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar .
COPY --from=build /app/src/main/resources /app/resources

ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=${ENVIRONMENT} $(ls *.jar)"]