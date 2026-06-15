FROM ubuntu:latest
LABEL authors="kamaev"

FROM gradle:8-jdk21-alpine AS builder

WORKDIR /build

COPY build.gradle settings.gradle ./
RUN gradle dependencies -q

COPY src ./src
RUN gradle bootJar -x test -q

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN mkdir -p /app/logs
COPY --from=builder /build/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]