#!/usr/bin/env sh

set +x
cd helpers
./destroy_terraform.sh
./uninstall_localstack.sh