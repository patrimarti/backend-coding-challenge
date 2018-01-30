Engage tech coding test
====
Tasks
--------------
All the stories have been finished.
User story `1` and user story `2` have been completed in the backend side and 
user stories `3` and `4` are implemented in the client-side.

Technology stack
--------------
* Spring boot;
* Tomcat embedded;
* Maven;
* JUnit;
* Hibernate implemented as JPA;
* MySQL database;
* Angular JS
* Fixer API for exchange rates and currency

Build and Run the Project
--------------
Prerequisites:
* Install Maven
* Install MySQL

#### MySQL requisites:
Set MySQL credentials in order to connect the dabatase. Go to `project_dir/solution/backend-coding-test/src/main/resources/application.properties` and edit:

```text
spring.datasource.username=<user_db>
spring.datasource.password=<password_db>
```
By default MySQL connects to the port 3306 in localhost and is using a database called `engagetech_pmg`. 

This can be changed editing:
```text
spring.datasource.url=jdbc:mysql://localhost:3306/engagetech_pmg?useSSL=false
```
Run the following DDL in order to initialize the database:
```sql
CREATE DATABASE IF NOT EXISTS `engagetech_pmg` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `engagetech_pmg`;

CREATE TABLE `expense` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `vat` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `expense`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `expense`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
```

#### Build and run the Rest Service

```bash
cd solution/backend-coding-test/
mvn spring-boot:run
  ```

The application will be started in `https://localhost:4884`
In order to change the port, go to: 
`project_dir/solution/backend-coding-test/src/main/resources/application.properties` and edit the line:
```text
server.port=4884 
```

REST API
--------------

* POST request in `/expenses` that returns a list of expenses;
* GET request in `/expenses` with a JSON object (Expense) that creates a new object and save it in the db;

