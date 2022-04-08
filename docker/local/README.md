# How to run locally

## Usage

To start the services locally using Docker Compose:

```console
$ docker-compose up
```

## Cleanup

Some data is persisted inside a volume by default.

In order to entirely shut down and remove all persisted data, use the following Docker Compose command:

```console
$ docker-compose down -v
```

*:information_source: You can also run all services in the background (detached mode) by appending the `-d` flag to the
above command.*

## Grafana Loki and Promtail

Source: https://www.springcloud.io/post/2022-02/springboot-loki-1/#gsc.tab=0

* [Grafana](https://github.com/grafana/grafana)
* [Loki](https://github.com/grafana/loki)
* Promtail

Open Grafana on your browser http://localhost:3000

Add Loki and your data source

Then explore your logs by entering this query: 
```
{filename="/var/log/<log-file-name>.log"}
```

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

