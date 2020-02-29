# Weather-Forecast-Api
The project is about checking weather using opensource API

## Table of contents
* [Build with](#build-with)
* [Technologies](#technologies)
* [Building Locally](#building-locally)

## Build with
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org)

## Technologies
* Java - version 8
* Swagger UI - API Development Tools 

## Running the application locally
There are several ways to run a application your local machine. One way is to run script `build.sh` directly.

- Download the zip to your desktop
- Unzip the zip file
- Run `build.sh` script to build and start project
- Head out to http://localhost:8080/swagger-ui.html
    - /api/v1/wb/cityCountry/{cityCountry}
    - /api/v1/wb/cityCountryAndTime

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
