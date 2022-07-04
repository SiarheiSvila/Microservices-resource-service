#!/usr/bin/env sh

set +x
cd ../docker
docker-compose up -d
cd ../scripts/helpers
./build_terraform.sh