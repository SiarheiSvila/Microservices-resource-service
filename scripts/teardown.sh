#!/usr/bin/env sh

set +x
docker kill docker_resources-localstack_1
docker kill docker_resources_db_1
docker rm docker_resources-localstack_1
docker rm docker_resources_db_1
cd helpers
./destroy_terraform.sh