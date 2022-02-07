person-data-approval
======
Aplicação responsável por gerenciar aprovação/desaprovação de dados de pessoas

### API 

**Criar uma aprovação**

Método: POST   
URN: /v1/person/approval   
Request Body: 
```json
{
    "name": "João",
    "lastName": "Dias", 
    "age": "35",
    "country": "Brazil"
}
```

**Aprovar os dados de uma pessoa**

Method: POST  
URN: /v1/person/{person_id}}/approve  
Attribute:  
person_id - identificador da pessoa

**Rejeitar os dados de uma pessoa**

Method: POST  
URN: /v1/person/{person_id}}/reject  
Attribute:  
person_id - identificador da pessoa

**Listar os dados de aprovação de pessoas**

Method: GET 
URN: /v1/person/on-approval 
Response:  
```json
{
    "id": "b2ff16c9-c616-439d-b585-8cc1db6459e0",
    "name": "João",
    "last_name": "Dias",
    "age": 35,
    "country": "Brazil",
    "status": "APPROVED"
},
{
    "id": "bc1d3d13-b685-46d7-b670-49dfb3a9a88a",
    "name": "José",
    "last_name": "Souza",
    "age": 35,
    "country": "Brazil",
    "status": "REJECTED"
},
{
    "id": "c97f5897-9bf7-488c-a0a5-3957e3286c37",
    "name": "Maria",
    "last_name": "Preira",
    "age": 35,
    "country": "Brazil",
    "status": "PENDING"
}
```


### Versionamento do banco de dados
Para versionar (criar, alterar, deletar) as tabelas do banco de dados, usamos o [liquibase](https://liquibase.org/). 
Os script de versionamento podem ser encontrados em:
```xpath
src/main/resources/db.changelog
```

### Variáveis de ambiente
**DB_URL** - string de conexão jdbc com o banco de dados  
**DB_USERNAME** - usuário do banco de dados  
**DB_PASSWORD** - senha do banco de dados  


## Métricas

A aplicação expoe 3 métricas, elas podem ser consumidas na rota HTTP:
```
/actuator/prometheus
```
* **approved_counter_total** - quantidade de aprovações 
* **rejected_counter_total** - quantidade de rejeições
* **request_counter_total**  - quantidade de requisições de validação de dados

## Execução local
Clone o repositório da aplicação
```sh
git clone git@github.com:romerodias/person-data-approval-application.git
```

Execute o docker-compose.yaml que está na raiz do projeto
```shell
docker-copmose up
```

Configure as variáveis de ambiente
```sh
export DB_URL=jdbc:mysql://localhost:3306/person_data_approval;
export DB_USERNAME=app;
export DB_PASSWORD=pda_app_891;
```

Execute a aplicação
```shell
mvn spring-boot:run -Dcom.amazonaws.sdk.disableCertChecking=true 
```

Visualizar trace com [Jeager](https://www.jaegertracing.io/)  
http://localhost:16686/search

Collection Postman   
Para testar a API, clone o repositório do postman e importe as collections 
```sh
git clone git@github.com:romerodias/person-data-approval-postman.git
```

Instalação da Solução
====
**Esta solução não é recomendada para ambiente de produção**

Pre-requisitos:
* Docker e docker-compose
* Java 8+
* Maven
* git bash
* aws-cli
* Terraform


Na AWS, criar um usuário no IAM com a policy AdministratorAcess;

Configurar o aws-cli com as credenciais do usuário criado  

Instale e configure o aws-cli com as credenciais do usuário criado on IAM

Clone o repositório da aplicação
```sh
git clone git@github.com:romerodias/person-data-approval-application.git
```

Faça o build e registre a imagm docker do projeto
```sh
cd person-data-approval-application
./build-and-registry.sh
```

Clone o repositório de infraestrutura 
```sh
git clone git@github.com:romerodias/person-data-approval-infrastructure.git
```

WIP: Terraform

Crie um plano de execução para permitir uma visualização das mudanças que o 
Terraform planeja executar na infraestrutura
```sh
terraform plan
```

Provisionar a infraestrutura
```sh
terraform apply 
```


