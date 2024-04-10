# HR Management System SOAP API

Welcome to the HR Management System SOAP API repository! This repository contains the source code and documentation for the SOAP API that facilitates managing various aspects of human resources including employees, job openings, leave management, salary, departments, and performance reviews.

## Features

- **Employee Management**: CRUD operations for managing employee data.
- **Job Openings**: Create, update, and delete job postings.
- **Leave Management**: Submit and approve leave requests.
- **Salary Management**: Adjust salaries and process payroll.
- **Department Management**: Create, modify, and delete organizational departments.
- **Performance Reviews**: Schedule, conduct, and summarize performance reviews for employees.

## Technologies Used

- **Programming Language**: Java
- **SOAP Framework**: JAX-WS (Java API for XML Web Services)
- **Database**: MySQL
- **Authentication**: Basic Authentication, WS-Security
- **Dependency Management**: Maven
- **ORM (Object-Relational Mapping)**: Hibernate Core
- **Connection Pooling**: HikariCP
- **Validation**: Hibernate Validator
- **SOAP Implementation Libraries**:
  - Jakarta XML Web Services API
  - JAX-WS Runtime
  - JAXB (Java Architecture for XML Binding) Implementation
  - Jakarta XML Bind API
  - GlassFish JAXB 
- **Web Server Deployment**: Apache Tomcat (via tomcat7-maven-plugin)
- **JSON Handling**: Jackson Databind, Jackson Datatype JSR310 (for date format)
- **Testing**: JUnit, Mockito

## Getting Started

To get started with the HR Management System SOAP API, follow these steps:

1. **Clone the Repository**: `git clone <repository-url>`
2. **Set Up Database**: Configure the database schema and connection properties. 
3. **Build the Project**: Compile the Java source files into bytecode using Maven.
4. **Run the Application**: Deploy the SOAP service on a web server or application server (e.g., Apache Tomcat, JBoss).
5. **Explore API Documentation**: Access the WSDL (Web Services Description Language) file to understand the available SOAP operations and message formats.


## API Documentation

Detailed documentation for the API endpoints can be found [here](https://documenter.getpostman.com/view/33815865/2sA3BgBw72).

