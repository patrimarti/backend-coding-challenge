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
By default MySQL connects to the port 3306 in localhost and is using/creating (if it doesn't exist) a database called `engagetech`. This can be changed editing:
```text
spring.datasource.url=jdbc:mysql://localhost:3306/engagetech?useSSL=false&createDatabaseIfNotExist=true
```

#### Build and run the app

```bash
cd solution/backend-coding-test/
mvn spring-boot:run
  ```

The application will be started in `http://localhost:4884`
In order to change the port, go to: 
`project_dir/solution/backend-coding-test/src/main/resources/application.properties` and edit the line:
```text
server.port=4884 
```

REST API
--------------

* POST request in `/expenses` that returns a list of expenses;
* GET request in `/expenses` with a JSON object (Expense) that creates a new object and save it in the db;

