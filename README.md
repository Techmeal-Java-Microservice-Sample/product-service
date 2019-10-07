# product-service
Sample microservice application with springboot to manage products

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.example.product.ProductServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Explore Swagger API:

swagger.json: `http://127.0.0.1:9071/product-service/v2/api-docs`

swagger-ui: `http://127.0.0.1:9071/product-service/swagger-ui.html`


## Running the junit tests locally

To execute junit test and generate test report and coverage report use following maven command. 

```shell
mvn site
```

This will create html report under /target/site/index.html 


