# Spring Project

- Java 19
- Spring Boot
- Spring MVC
- Spring Data
- Spring Rest
-  Spring Security

## System Login
>Register
> Login(username + password)
> Authorization(Jwt)

## Lesson Steps
> Spring Framework
> Spring Boot
> Spring MVC
> Spring Data(Jpa Hibernate)
> Spring Rest (Restfull ==> Jersey)
> Security

* [Github](https://github.com/epenespolat/SpringBootTutorial)

```sh
docker
```

## Docker Deployment
```sh 
1.ADIM
$ ./mvnw clean package -DskipTests

2.ADIM
$ docker-compose up
$ docker ps

// CREATE
http://localhost:8080/docker/v1/create/product

// LIST
http://localhost:8080/docker/v1/list/product

// FIND
http://localhost:8080/docker/v1/find/product/1

// DELETE
http://localhost:8080/docker/v1/delete/product/1

4.ADIM
$ docker exec -it spring_docker_postgresqldb_1 psql -U postgres studentdb

5.ADIM
$ studentdb=# \dt ==> Tabloları göstermek
$ studentdb=# select* from product
$ studentdb=# \q ==> Çıkış
```
