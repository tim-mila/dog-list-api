# Dog Breed List App API

[![Build Status](https://travis-ci.org/tim-mila/dog-list-api.svg?branch=master)](https://travis-ci.org/tim-mila/dog-list-api)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) app integrating with [Dog API](https://dog.ceo/dog-api/) to retrieve a list of dog breeds and images.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
sh mvnw spring-boot:run
```