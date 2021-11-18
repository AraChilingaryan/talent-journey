# talent-journey
The app simplifies the talent hiring process by automating some sub-processes, such as emailing candidates,creating interviews, receiving feedback, accepting candidates who have passed all the stages

For more information on how to this works with other frontends/backends, head over to the [Talent Journey](https://github.com/AraChilingaryan/talent-journey) repo.

# How it works

The application uses Spring Boot (Web).
* Use RESTFULapi
* Use the idea of Domain Driven Design to separate the business term and infrastructure term.
* Use Calandly for selecting slots
* Use Thymeleaf for sending email with html bodies



And the code is organized as this:

1. `api` is the web layer implemented by Spring MVC
2. `core` is the business model including entities and services
3. `application` is the high-level services for querying the data transfer objects
4. `infrastructure`  contains all the implementation classes as the technique details

# Security

Integration with Spring Security and add other filter for jwt token process.

The secret key is stored in `application.properties`.

# Database

It uses a ~~H2 in-memory database~~ sqlite database (for easy local test without losing test data after every restart), can be changed easily in the `application.properties` for any other database.
