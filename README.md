# Order Management REST API ![CI Status](https://travis-ci.org/albanoj2/order-rest-backend.svg?branch=master)

Demonstrates a simple RESTful web service using Spring MVC and Java. This web service provides an in-memory order management service, with the capability to get a single order, get all orders, create an order, delete an order, and update an order. After exploring this project, the reader will understand the three common layers of a RESTful web service (namely domain, data source, and presentation), the common design decisions used when creating a web service, and the functionality provided by the Spring framework for creating a web service (as well as supplemental functionality, such as creating [HATEOAS](http://projects.spring.io/spring-hateoas/) links). In particular, the reader will learn

 - How to create a domain model
 - How to store domain objects in a persistence layer
 - How to wrap domain objects within resource objects
 - How to add HATEOAS links to a resource
 - How to serve up resources to a client over HTTP
 - How to provide RESTful Create, Read, Update, and Delete (CRUD) operations to change domain objects
 - How to create unit, integration, and acceptance tests that exercise a REST API

## Starting the Order Management System
To start this web service, install [Maven](https://maven.apache.org/install.html) and execute the following command

    mvn spring-boot:run
    
Once the web service is started, it can be reached at

    http://localhost:8080/order
