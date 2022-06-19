#!/usr/bin/env sh

set +x
cd helpers
./install_localstack.sh
./build_terraform.sh