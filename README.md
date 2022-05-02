# Image-to-Conway
Turn any image into an initial _Conway's Game of life_ grid... and start living!

![](https://raw.githubusercontent.com/mendes-r/image-to-conway/main/asimov-example.gif)

## Introduction
The idea behind this project is to transform any image into a grid for the famous Conway´s Game of Life.
After this transformation - the implementation of filters and resizing - it will be possible to play the game according to Conways' rules.

## Possible future new functionalities
This project could be turned into a web-application.

PS: this project is also a playground to learn new tools.

## How to run locally

First you need to create the docker image of the application.
To do this, run the following gradle task:

```shell
$ ./gradlew docker
```

After that you run the docker-compose file.
Run this command inside the folder docker/local/

```shell
$ docker-compose up -d
```

### API

- POST **/upload
- GET **/iterate
- GET **/gif

## Environment Variables

| Variables       | CICD    | Local | 
|-----------------|---------|-------|
| DOCKER_HUB_REPO |    X    |       |       
| LOKI_URL        |         |   X   |     
| S3_URL          |         |   X   |     
