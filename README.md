# spring_boot
Welcome to  Spring Boot classroom repository

![image](https://user-images.githubusercontent.com/29695491/119985600-c3849380-bfba-11eb-88e7-86894ab7012d.png)

### How to run :runner:

**1° step** - Start docker container with `postgres` and `sonarqube` :whale:

```bash
docker-compose -f docker-compose.yml up -d
```

**2° step** - Start item-storage-service :computer:

```bash
mvn clean install -DskipTests && mvnw spring-boot:run
```

### API :fire:

Try the API with:

```
http://127.0.0.1:8888/1
```

to get the response to one with one of the items details

```
http://127.0.0.1:8888/all
```

To get all the items available in the repository
