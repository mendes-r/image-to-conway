# How to run locally

## Localstack

First you need to install [Localstack](https://docs.localstack.cloud/):

```shell
$ python3 -m pip install localstack
```

## AWS

If you have already AWS CLI configured, please go to the next step.

Otherwise, install AWS CLI and run the following command:

```shell
$ aws configure
...
```

All the necessary configuration can be fakes, since we will not interact with the real AWS cloud infrastructure.

## Docker-compose

Run the docker-compose.yaml with the following command:

```shell
$ docker-compose up
```

It will create a container with a S3 bucket.

## Access S3

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
