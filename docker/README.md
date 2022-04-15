# Create docker image

The following command needs to be run in the projects root.

```shell
$ ./gradlew clean build 
$ docker build -t app-i2c -f docker/Dockerfile .
```