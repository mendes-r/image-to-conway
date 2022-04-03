# How to run locally

## Usage

To start the services locally using Docker Compose:

```console
$ docker-compose up
```

## Cleanup

Elasticsearch data is persisted inside a volume by default.

In order to entirely shutdown and remove all persisted data, use the following Docker Compose command:

```console
$ docker-compose down -v
```

*:information_source: You can also run all services in the background (detached mode) by appending the `-d` flag to the
above command.*

## Elastic Stack 

Source: https://github.com/deviantony/docker-elk

Based on the official Docker images from Elastic:

* [Elasticsearch](https://github.com/elastic/elasticsearch/tree/master/distribution/docker)
* [Logstash](https://github.com/elastic/logstash/tree/master/docker)
* [Kibana](https://github.com/elastic/kibana/tree/master/src/dev/build/tasks/os_packages/docker_generator)

By default, the stack exposes the following ports:

* 5044: Logstash Beats input
* 5001: Logstash TCP input
* 9600: Logstash monitoring API
* 9200: Elasticsearch HTTP
* 9300: Elasticsearch TCP transport
* 5601: Kibana

### Logstash

### Elasticsearch

To confirm the Elasticsearch is running:

```shell
$ curl http://localhost:9200 -u elastic:changeme
```

### Kibana

Open the Kibana web UI by opening <http://localhost:5601> in a web browser and use the following credentials to log in:

* user: *elastic*
* password: *\<your generated elastic password>*

## Localstack

First you need to install [Localstack](https://docs.localstack.cloud/):

```shell
$ python3 -m pip install localstack
```

### AWS

If you have already AWS CLI configured, please go to the next step.

Otherwise, install AWS CLI and run the following command:

```shell
$ aws configure
...
```

All the necessary configuration can be fakes, since we will not interact with the real AWS cloud infrastructure.

### Access S3

List buckets:

```shell
$ aws --endpoint-url=http://localhost:4566/ s3 ls
```

Create bucket:

```shell
$ aws --endpoint-url=http://localhost:4566/ s3 mb s3://<bucket-name>
```

Upload file:

```shell
$ aws s3 cp <file-url> s3://<bucket-name>/<file-name> --endpoint-url=http://localhost:4566/
```

Download file:

```shell
$ aws s3 cp s3://<bucket-name>/<file-name> <file-name> --endpoint-url=http://localhost:4566/
```

List bucket content

```shell
$ aws s3 ls s3://<bucket-name> --endpoint-url=http://localhost:4566/
```

