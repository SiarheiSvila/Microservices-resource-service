# Resource service
Built for [EPAM Microservices learn program](https://learn.epam.com/myLearning/program?groupGuid=15826ffb-49c9-4ad6-8518-4c0438d17fb8&tab=panels).

Simple service to perform CRUD operations for files.

For file storing [Localstack](https://github.com/localstack/localstack) is used. For creating S3 bucket at Localstack [Terraform](https://learn.hashicorp.com/terraform) is used.



## Installation

### Localstack
To install and startup Localstack run [install_localstack.sh](scripts/helpers/install_localstack.sh)

### Terraform
Install Terraform as described at the [documentation](https://learn.hashicorp.com/tutorials/terraform/install-cli?in=terraform/aws-get-started).

To build Terraform infrastructure run [build_terraform.sh](scripts/helpers/build_terraform.sh).

To verify the installation, run the command and see tha bucket exists:
`aws --endpoint-url=http://localhost:4566 s3api list-buckets`


## Usage
