# Spring-boot-Cities-Connection
The spring boot application identifies whether two cities are connected. This service exposes a REST Api ("/connected") which accepts Origin and Destination city as input and responds back whether those cities are connected.  
There could be a direct connection or series of roads connection (intermediate city hops), both qualifies as connected.

### Documentation Contents:
1. [Summary](#summary)
2. [Technologies](#technologies) 
3. [Rest API](#rest-api)
4. [Implementation](#implementation)
5. [Start the application](#start-the-application)
6. [Test](#test)

### Summary
Provided Origin and Destination cities, this service identifies whether they are connected. 

### Technologies
Spring Boot v2.x, Spring REST, Java 8, Junit, Spring Boot test, Maven

### Rest API
GET /connected?origin=<origin-city>&destination=<destination-city>

**Response**<br></br>
yes - If connected<br></br>
no - If not connected<br></br>
no - All other scenarios like invalid data. 

### Implementation
The City Connection is perfect use case for a **Graph data structure**. A Graph structure have been created with the input data provided (city.txt).<br></br> 
**Breadth first traversal** technique is used to traverse the Graph and connection between cities is identified.

Please look at a sample City Graph visualization @ Resources folder. This I generated out of the input provided. 
 
1. First using the ResourceLoader, input file - city.txt is read from classpath (city.txt is placed inside resources folder). A City Connection Graph have been created 
2. Using File NIO, the input file is read and utilizing Java 8 Streams api, the connections (lines) are read and a **City Graph** is constructed.
3. First two steps happens during application startup.
4. Now, once Spring-Boot application is up and running, the api endpoint _/connected_ is invoked by passing Origin and Destination cities.
5. Using Breadth First Traversal technique, the Graph is traversed and the connection is identified. 

## Start the application
Since Spring-boot app and the package is jar and embedded web container is used. You can start the application as a regular Java application<br></br>
In the terminal Navigate to Main Configuration and start as a java class. If you are using any IDE you may start with the option the IDE provides.<br></br>
At same time Spring-Boot Maven plugin is included in pom.xml, so you can also start using below command.<br></br>

**Main Configuration Class:** com.challenge.connection.cities.CitiesConnectionsApplication

```
mvn spring-boot:run
```
**Note:** Application runs on port 8080. And, the port to run is already configured in the property file so need not have to specify in the command line. 

## Test
#### Via Browser
```
http://localhost:8080/connected?origin=Boston&destination=Newark
http://localhost:8080/connected?origin=Boston&destination=Philadelphia
http://localhost:8080/connected?origin=Trenton&destination=Albany
http://localhost:8080/connected?origin=Phoenix&destination=Austin
http://localhost:8080/connected?origin=Boston&destination=Albany
http://localhost:8080/connected?origin=Boston&destination=Trenton
http://localhost:8080/connected?origin=Philadelphia&destination=Albany
```
#### Via Terminal 
Do a cURL
```
curl --location --request GET 'http://localhost:8080/connected?origin=Boston&destination=Newark'
```

And, Unit test is written for all the classes. **Spring Boot Test** capability is leveraged to the fullest.

