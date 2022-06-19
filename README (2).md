# 시작하기

## Base Project 다운로드 및 실행
먼저, 새로운 브라우저 탭을 열고, base project 를 gitpod 로 접속합니다.

https://gitpod.io/#https://github.com/msa-school/ddd-petstore-level5-layered

GidPod 내에 터미널을 열고(왼쪽 상단의 햄버거 버튼 > Terminal > New Terminal), 프로젝트가 잘 컴파일 되는지 확인합니다:
```
mvn spring-boot:run
```

## 미션
- JPA를 통한 Database Input/Output Adapter 자동생성
- Spring Data REST 를 통한 REST API Input/Output Adaptor 자동생성 (코드 비교 하면서 추가)
- 등록된 강아지를 httpie 로 확인한다:
```
http :8080/pets
```
> httpie 가 설치되지 않은 경우: pip install httpie

## 미션2
- maria db 를 이용하여 연결:
- maria db server 를 기동하기 (docker)
```
 docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin  mysql:latest 
```
- 접속 및 DB 생성
```
> docker exec -it mysql  /bin/bash

root@251ce07fd6fc:/# mysql --user=root --password=$MYSQL_ROOT_PASSWORD
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 4
Server version: 10.7.3-MariaDB-1:10.7.3+maria~focal mariadb.org binary distribution

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MariaDB [(none)]> 
MariaDB [(none)]> 
MariaDB [(none)]> create database petstore;
Query OK, 1 row affected (0.000 sec)

MariaDB [(none)]> 

```
- application.yaml 의 설정
```
spring:
  jpa:
    hibernate:
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      ddl-auto: update
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        dialect: org.hibernate.dialect.MySQL57Dialect
  datasource:
    url: jdbc:mysql://localhost:3306/petstore
    username: root
    password: admin
    driverClassName: com.mysql.cj.jdbc.Driver
```
- 데이터 확인
```
MariaDB [(none)]> use petstore
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
MariaDB [petstore]> show tables;
+--------------------+
| Tables_in_petstore |
+--------------------+
| Pet                |
| hibernate_sequence |
+--------------------+
2 rows in set (0.000 sec)

MariaDB [petstore]> 
MariaDB [petstore]> 
MariaDB [petstore]> select * from Pet;
+----------+----+------------+--------+------+
| pet_type | id | appearance | energy | name |
+----------+----+------------+--------+------+
| dog      |  1 |          0 |      3 | ??   |
+----------+----+------------+--------+------+
1 row in set (0.000 sec)

```

## 다음: 도메인 영역의 확장 (Single Model, Big Ball Of Mud)
- Store 업무 영역의 추가
- 비대한 Monolith
- https://github.com/msa-school/ddd-petstore-level7-big-ball-of-mud



## 

### Connect to h2 console 

visit: https://8080-jinyoung-hibernatespati-wxejiyiapht.ws-us47.gitpod.io/h2-console/

```
CREATE ALIAS CreateSpatialIndex for "geodb.GeoDB.CreateSpatialIndex";
CREATE ALIAS DropGeometryColumn for "geodb.GeoDB.DropGeometryColumn";
CREATE ALIAS DropGeometryColumns for "geodb.GeoDB.DropGeometryColumns";
CREATE ALIAS DropSpatialIndex for "geodb.GeoDB.DropSpatialIndex";
CREATE ALIAS EnvelopeAsText for "geodb.GeoDB.EnvelopeAsText";
CREATE ALIAS GeometryType for "geodb.GeoDB.GeometryType";
CREATE ALIAS ST_Area FOR "geodb.GeoDB.ST_Area";
CREATE ALIAS ST_AsEWKB FOR "geodb.GeoDB.ST_AsEWKB";
CREATE ALIAS ST_AsEWKT FOR "geodb.GeoDB.ST_AsEWKT";
CREATE ALIAS ST_AsHexEWKB FOR "geodb.GeoDB.ST_AsHexEWKB";
CREATE ALIAS ST_AsText FOR "geodb.GeoDB.ST_AsText";
CREATE ALIAS ST_BBOX FOR "geodb.GeoDB.ST_BBox";
CREATE ALIAS ST_Buffer FOR "geodb.GeoDB.ST_Buffer";
CREATE ALIAS ST_Centroid FOR "geodb.GeoDB.ST_Centroid";
CREATE ALIAS ST_Crosses FOR "geodb.GeoDB.ST_Crosses";
CREATE ALIAS ST_Contains FOR "geodb.GeoDB.ST_Contains";
CREATE ALIAS ST_DWithin FOR "geodb.GeoDB.ST_DWithin";
CREATE ALIAS ST_Disjoint FOR "geodb.GeoDB.ST_Disjoint";
CREATE ALIAS ST_Distance FOR "geodb.GeoDB.ST_Distance";
CREATE ALIAS ST_Envelope FOR "geodb.GeoDB.ST_Envelope";
CREATE ALIAS ST_Equals FOR "geodb.GeoDB.ST_Equals";
CREATE ALIAS ST_GeoHash FOR "geodb.GeoDB.ST_GeoHash";
CREATE ALIAS ST_GeomFromEWKB FOR "geodb.GeoDB.ST_GeomFromEWKB";
CREATE ALIAS ST_GeomFromEWKT FOR "geodb.GeoDB.ST_GeomFromEWKT";
CREATE ALIAS ST_GeomFromText FOR "geodb.GeoDB.ST_GeomFromText";
CREATE ALIAS ST_GeomFromWKB FOR "geodb.GeoDB.ST_GeomFromWKB";
CREATE ALIAS ST_Intersects FOR "geodb.GeoDB.ST_Intersects";
CREATE ALIAS ST_IsEmpty FOR "geodb.GeoDB.ST_IsEmpty";
CREATE ALIAS ST_IsSimple FOR "geodb.GeoDB.ST_IsSimple";
CREATE ALIAS ST_IsValid FOR "geodb.GeoDB.ST_IsValid";
CREATE ALIAS ST_MakePoint FOR "geodb.GeoDB.ST_MakePoint";
CREATE ALIAS ST_MakeBox2D FOR "geodb.GeoDB.ST_MakeBox2D";
CREATE ALIAS ST_Overlaps FOR "geodb.GeoDB.ST_Overlaps";
CREATE ALIAS ST_SRID FOR "geodb.GeoDB.ST_SRID";
CREATE ALIAS ST_SetSRID FOR "geodb.GeoDB.ST_SetSRID";
CREATE ALIAS ST_Simplify FOR "geodb.GeoDB.ST_Simplify";
CREATE ALIAS ST_Touches FOR "geodb.GeoDB.ST_Touches";
CREATE ALIAS ST_Within FOR "geodb.GeoDB.ST_Within";
CREATE ALIAS Version FOR "geodb.GeoDB.Version";
```


# Test

```

pip install httpie

http :8080/cats name="몽이" point[x]=0.1 point[y]=0.1
http :8080/cats/search/findWithin x==0 y==0

```