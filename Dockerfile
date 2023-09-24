# Fase de construcción
FROM maven:3.6-openjdk-17 AS build

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

# Fase de ejecución
FROM openjdk:17-jdk-alpine

EXPOSE 8080

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://silly.db.elephantsql.com:5432/urnkcqdx
ENV SPRING_DATASOURCE_USERNAME=urnkcqdx
ENV SPRING_DATASOURCE_PASSWORD=3w0DO_uPTYMIXejb4NXeb_gBkdOEZxRY
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

COPY --from=build /app/target/repair-api-0.0.1-SNAPSHOT.jar /app/repair-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/repair-api-0.0.1-SNAPSHOT.jar"]
