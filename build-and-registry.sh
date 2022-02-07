#!/bin/bash

repo_name="personal-data-approval"
region="us-east-1"
account_id=$(aws sts get-caller-identity --query "Account" --output text)


# Criar o repositório caso não exista
aws ecr describe-repositories --repository-names $repo_name 2>&1 > /dev/null
status=$?
if [[ ! "$status" -eq 0 ]]; then
    aws ecr create-repository --repository-name $repo_name
fi

# Criar a imagem do projeto
docker build . -t "$repo_name:latest"

# Criar a tag na imagem a ser enviada para AWS ECR
docker tag personal-data-approval:latest "$account_id.dkr.ecr.$region.amazonaws.com/$repo_name:latest"

# Efetuar o login do Docker no repositório
aws ecr get-login-password --region $region | docker login --username AWS --password-stdin "$account_id.dkr.ecr.$region.amazonaws.com/$repo_name:latest"

# Enviar a imagem para AWS ECR
docker push "$account_id.dkr.ecr.$region.amazonaws.com/$repo_name:latest"

