## RESTful - приложение с использованием JPA и Hibernate без Spring Data

---

### В проекте используются:
* Java 21
* Spring Boot 3.5.0
* Lombok
* JPA
* Hibernate
* Liquibase
* Docker
* Postman

---

Это RESTful приложение, написанное на Spring Boot, имеющее сущности, контроллеры, сервисы и репозитории. 

Взаимодействие с базой данных (Postgres) возможно двумя способами: 
* С помощью спецификации JPA (Java Persistence Api) с использованием 
EntityManagerFactory + EntityManager (под капотом используется Hibernate).
* С помощью чистого Hibernate напрямую с использованием SessionFactory + Session.

Переключение между реализациями осуществляется с помощью профилей `jpa` и `hibernate`.
Для их переключения в файле **src/main/resources/application.yaml** необходимо изменить параметр `spring.profiles.active` 
на `jpa` или `hibernate` в зависимости от того, какой необходим.

В приложении присутствуют **Liquibase** - миграции базы данных по созданию таблиц и их заполнению, 
которые накатываются автоматически при запуске приложения.
Их также можно запустить отдельно (например с помощью команды `liquibase update`)

---

### Инструкция по запуску приложения
1. Запустить файл `docker-compose.yaml` в корневой директории. Он запускает базу данных Postgres на порту `5437`.
2. Запустить само приложение (класс `HibernateWithoutSpringDataApplication`). Приложение запускается на порту `8080`.
    Миграции по созданию и заполнению таблиц накатываются автоматически, если они еще не были выполнены.
3. Можно выполнять запросы по эндпоинтам, реализованным в контроллере. Например:

    `GET http://localhost:8080/hibernate/test/clients`
    `GET http://localhost:8080/hibernate/test/clients/2`

---

### Сравнение взаимодействия с помощью EntityManagerFactory + EntityManager (EMF) и SessionFactory + Session (SF)

1. * Когда используем EMF - мы работаем только со спецификацией JPA. В это время под капотом используется реализация
    (например Hibernate)
   * Когда используем SF - работаем напрямую с Hibernate (то есть с реализацией JPA)


2. * Используя EMF - уровень абстракции выше
   * Используя SF - уровень абстракции ниже


3. * EMF в транзакциях использует JpaTransactionManager
   * SF в транзакциях использует HibernateTransactionManager