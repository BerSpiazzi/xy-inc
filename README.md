# XY Inc - POI Service

## Descrição

Esta aplicação fornece serviços RESTful para gerenciar e consultar pontos de interesse (POIs).

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- SqLite
- JUnit

## Configuração

1. Clone o repositório:
   ```
   git clone https://github.com/BerSpiazzi/xy-inc.git

2. Navegue até o diretório do projeto:
   ```
   cd xy-inc

3. Crie a DataBase em Postgres:
   ```
   CREATE DATABASE xyinc;

4. Compile e execute o projeto:
   ````
   ./mvnw spring-boot:run

5. Acesse a aplicação em:
    ```
   http://localhost:8080/xy-inc/api/poi

## Endpoints

### Cadastrar POI

- **URL**
  ```
  /xy-inc/api/poi

- **Método**
  POST

- **Corpo da Requisição**
    ```
    {
        "nome": "Lanchonete",
        "coordenadaX": 27,
        "coordenadaY": 12
    }

- **Resposta de Sucesso**
    ```
    {
        "id": 1,
        "nome": "Lanchonete",
        "coordenadaX": 27,
        "coordenadaY": 12
    }

- **Resposta de Erro**
    ```
    {
        "timestamp": "2021-10-10T20:00:00.000+00:00",
        "status": 400,
        "error": "Bad Request",
        "message": "O atributo 'nome' é obrigatório",
        "path": "/xy-inc/api/poi"
    }

### Listar POIs

- **URL**
  ```
  /xy-inc/api/poi

- **Método**
  GET

- **Resposta de Sucesso**
    ```
    [
        {
            "id": 1,
            "nome": "Lanchonete",
            "coordenadaX": 27,
            "coordenadaY": 12
        }
    ]

- **Resposta de Erro**
    ```
    StatusCode: 204 No Content

### Listar POIs por Proximidade

- **URL**
  ```
  /xy-inc/api/poi

- **Método**
  GET

- **Body**
    ```
    {
        "coordenadaX": 20,
        "coordenadaY": 10,
        "distanciaMaxima": 10
    }

- **Resposta de Sucesso**
    ```
    [
        {
            "id": 1,
            "nome": "Lanchonete",
            "coordenadaX": 27,
            "coordenadaY": 12
        }
    ]

- **Resposta de Sucesso vazia**
    ```
  StatusCode: 204 No Content
    []

- **Resposta de Erro**
    ```
    StatusCode: 400 Bad Request
    {
        "timestamp": "2021-10-10T20:00:00.000+00:00",
        "status": 400,
        "error": "Bad Request",
        "message": "O atributo 'distanciaMaxima' é obrigatório",
        "path": "/xy-inc/api/poi"
    }
