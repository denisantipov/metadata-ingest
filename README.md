# Metadata Ingest Service

This is a demo service that includes patterns and technologies that can be used in other types of Scala on Spring Boot microservices.
The main use case of this service is to read Yaml files from an AWS S3 account and present them in JSON format at a specific endpoint.
This microservice uses circuit breaker request pattern to alleviate potential bottleneck of S3 under load, however unlikely it is.
As of writing, there are the latest Scala Spring Boot libraries utilized, which gives the best options for future code maintenance.


The package includes

* Spring Boot 3
* Scala 3
* Swagger OpenAPI
* Maven build POM
* Docker image and template
* JUnit5 tests
* Actuator endpoints for liveness and readiness endpoints

## Building
The easiest is to use IntelliJ Ultimate to build this module, or alternatively have command line maven, docker, Oracle Java 21 setup

```bash
mvn package
mvn spring-boot:run
```
## Running

make sure you have an .env file or have the following variables set in the shell with the appropriate values from the AWS account
```bash
APP_AWS_ACCESS_KEY=...
APP_AWS_SECRET_KEY=...
APP_AWS_S3_BUCKET=...
```
Run using executable jar:
```bash
java -jar target/metadata-ingest-0.0.1-SNAPSHOT.jar
```

Run using Docker:
```bash
docker run -p 8080:8080 dennissibborn/metadata-ingest:main
```


## Endpoints


### http://localhost:8080/swagger-ui/index.html
Swagger default Web UI for displaying all the endpoints in OpenAPI format

### http://localhost:8080/health_check
The health check endpoint that returns 200 response and "OK" string when it's operating normally.
This endpoint can be used for K8S health and liveness check for scaling

### http://localhost:8080/api/list
This endpoint provides a list of files in the AWS S3 bucket named in the environment variable

### http://localhost:8080/api/view/json/{filename}
This endpoint provides the JSON content of the filename specified in the URL path parameter

### http://localhost:8080/api/view/{filename}
This endpoint provides the original file content (Yaml) of the filename specified in the URL path parameter

### http://localhost:8080/actuator/health
Actuator health check with liveness and readiness status.
This endpoint can also be used for K8S health and liveness check for scaling


## Kubernetes Setup (TBD)
* Install helm (Mac OS: `brew install helm`)

Steps TBD

Connect to the app locally, create a tunnel to the service:
```bash
kubectl port-forward service/metadata-ingest 8181:8080
curl http://localhost:8181
```

