#!/usr/bin/env sh

set +x
cd ../../src/main/resources/terraform
terraform init
terraform fmt
terraform validate
terraform apply