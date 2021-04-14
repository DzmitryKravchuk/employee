# REST приложение для управления базой данных сотрудников

### Описание проекта

Приложение позволяет:
1) получать данные о всех сотрудниках в системе;
2) удалять данные сотрудника;
3) создавать нового сотрудника, либо менять данные о присутствующих в базе;

Взаимодействие с данными с помощью Spring Data JDBC;
СУБД PostgreSQL.

### Запуск приложения

* создать базу данных 'CREATE DATABASE employee'
* запустить класс EmployeeApplication из пакета com.example.employee
* все необходимые таблицы liquibase создаст сам из changelog

### Пример создания запроса создание данных о новом сотруднике

* в адресной строке в качестве URL передать: (method POST) http://localhost:8080/employee
* в теле запроса передать json, ex:
  {
  "firstName": "Дмитрий",
  "lastName": "Нагиев",
  "departmentId": 1,
  "jobTitle": "маркетолог",
  "gender": "м",
  "dateOfBirth": "1990-01-01"
  }

### Используемые технологии

* Spring Boot
* Spring Data JDBC
* Lombok
* Liquibase
* JUnit для тестирования сервисов