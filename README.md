<p align="center">
    <img alt="Spring Doc Resources" title="Spring Doc Resources" src="https://i.imgur.com/fGi6EaT.png" width="450">
</p>

# course_registration_API

## Description

Spring Boot course registration API. Allows for administrators to authenticate, and access all possible resoources.

## Initial Requirement

```bash
# Must have all dependancies and java version 17
```


## Setting up the Database using Docker 

```bash
# in background
$ docker compose up dev-db -d
```

Don't forget to add all Maven dependencies in pom.xml

```bash
# Please use Swagger (Open API), this is the link to open in your browser
$ http://localhost:8080/swagger-ui/index.html#/
```

## WARNING

```bash
Even though Login generates and sends back token (which is generated properly),
if you use this token the application will not work. Token based authentication is yet
to be put in place. In addition, passwordsa are not hashed when stored in database,
for development purposes and to simplify it.
```

![course reg pic](https://user-images.githubusercontent.com/94652612/227973085-bb46c299-6c32-4f85-8290-ff6850b8c557.png)

