#!/usr/bin/env sh

set +x
docker kill resources-localstack
docker rm resources-localstack