# Organization-Microservice-App
Sample Microservice Architecture with Api Gateway and Service Registry


This project demonstrate a Sample Microservice Architecture with Api Gateway and Service Registry and JWT Security.

OrganizationUIApp is the UI for the solution. It is a Angular 5 application.

CommonUtils application is the library for microservices. Common utilities like security is handled here.

Microservices are built with Spring Boot 2.0.5 and maven 3.5. Solution components and technologies used are below:

- ApiGateway - Zuul Proxy
- Service Registry - Eureka
- Config Server - Spring Boot Config
- Security - Spring Boot in memory Authentication and JWT

Microservices uses mongo db as database.

To run the solution follow that stps:

- Start your local mongo db with default port 27017 or use docker mongo image with below command:

    docker run --name my-local-mongo -v mongo-data:/data/db -p 27017:27017 -d mongo
    
- Package your CommonUtils application with below command and add package to your local maven repo
    
    mvn clean install package
    mvn install:install-file -Dfile=pathToCommonUtilsJar file -DgroupId=com.example.common-utils -DartifactId=CommonUtils -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -DgeneratePom=true
 
- Package other applications
- Run ConfigServer Application with below command:
    
    mvn spring-boot:run
    
- Run ServiceResistry application
- Run remaning microservice applications
- Run angular application with below command
    
    ng serve --port=4200
    
  After all steps completed,  you can use the solution from http://localhost:4200 address
  
  You can login with user/user or admin/admin.
  
