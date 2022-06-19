#!/usr/bin/env sh

set +x
docker run -d --name resources-localstack -p 4566:4566 -p 4571:4571 localstack/localstack