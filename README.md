# Showcasing a reactive spring-boot webapp

## Prerequisites

### mysql

`docker run --name rmysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=reactive -e MYSQL_DATABASE=reactive -d mysql`

## Build

`./mvnw clean package`