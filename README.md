# spring-boot-phrase-api-client

Very basic REST API client in Spring Boot & Java 17 serving the data into a simple web App

Best served in IntelliJ Idea (Ultimate)

## What is working

 - showing a user detail, e.g. http://localhost:8080/users/20011318
 - showing a user list, e.g. http://localhost:8080/users
 - showing a project list, e.g. http://localhost:8080/projects

## Thought process and decision-making

### Spring Boot

 - as a newbie with Spring-Boot I visited the https://start.spring.io/ and generated a simple war archived web app
 - I went through the recommended documentation and tutorials and started to build the app from scratch

### RestTemplate vs FasterXML Jackson

 - I wanted to learn as much as possible and use the possibilities of Spring itself as extensively as possible
 - in the end I benefited from my knowledge of the FasterXML Jackson library to speed up the process - it was simpler to me to traverse through Phrase's JSON hierarchy design 

## What I want to implement (TODOs)

### H2 DB

 - I intended to take an inspiration in e.g. Vaadin CRM (https://vaadin.com/docs/latest/overview)
 - nice guide how to implement simple H2 DB approach
 - usable front-end, although too complex for this exercise 

### Front End

 - HTML forms for creating the values (POST)
 - HTML forms for editing the values (PUT)
 - adding proper navigation
 - [com.phrase.mkafka.demo.entity](demo/src/main/java/com/phrase/mkafka/demo/entity) is designed with the DB CRUD in mind

### Possible improvements

 - improving security - utilize Spring Security for custom login page, also improve handling of the API token (I am used to have secrets in the AWS Security Manager) 
 - Common abstract predecessor for the POJO entities from the [com.phrase.mkafka.demo.entity](demo/src/main/java/com/phrase/mkafka/demo/entity) package to carry the wrapper for the paging, etc.  