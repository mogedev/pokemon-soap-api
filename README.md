# Getting Started
## POKEMON SOAP API

This API provides a set of endpoints to find pokemons by name.

### Prerequisites
- Java JDK: Ensure you have the Java Development Kit (JDK) installed on your system. You can verify if Java is installed by opening a terminal or command prompt and running the command java -version. If you don't have Java installed, you can download it from the Oracle website or adopt an OpenJDK distribution.

- Maven: You need to have Maven installed. If you don't have it, you can download and install it from the official Apache Maven website.

- IDE (Optional): While not strictly necessary, an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse can make development much easier.

- Docker

### Steps

**Obtain the project code:**

Download code from github:

```bash
    git clone <repository-url>
```

**Import the project into your IDE (optional):**

- If you're using an IDE, import the Maven project. This will allow you to navigate the code, edit it, and run it from the IDE.

- Build the project with Maven:

  Open a terminal or command prompt and navigate to the project's root directory.

  Run the command `mvn clean package -P dev` to clean the project, compile the code, run the tests, and package it into an executable JAR file.

- Run the service

  Once the build is successful, a JAR file will be created in the target directory.

  Run the service with the command `java -jar target/emploees-0.0.1-SNAPSHOT.jar`.


**Build the project with Docker:**

- Open a terminal or command prompt and navigate to the project's root directory.

- Run the command `docker pull postgres:latest` to download the latest postgres image.

- Run the command `docker pull openjdk:17-jdk` to download openjdk image.

- Run the command `docker compose -f ./docker-compose.yaml up --build` to build and deploy services.

**Try the service:**

You can try the service with:

- In this folder, you have a Postman collection `pokemon-soap-api.postman_collection.json`, you can copy it and import it to the Postman application.
  [__Postman collection__](./pokemon-soap-api.postman_collection.json)