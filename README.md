# Desafio NT

### Tecnologias Usadas
- Java
- Apache Kafka
- Springboot
- Lombok
- Swagger3
- PostgresSQL
- Maven
- Docker
- Hibernate
- Flyway
-Heroku

## Instruções de execuçao
#### Pre Requisitos
- Docker
- Git


- `git clone https://github.com/LuisFfer12/desafiont.git`
- `cd desafiont`

### Kafka
`docker run -p 2181:2181 -p 9092:9092 -e ADVERTISED_HOST=127.0.0.1  -e NUM_PARTITIONS=10 johnnypark/kafka-zookeeper`

### Postgres
`docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres`

#### Api
`mvnw spring-boot:run`

## URL's
`http://localhost:9000/swagger-ui/index.html#/`

## API Heroku
`https://desafiont.herokuapp.com/swagger-ui/index.html#/`

