Queriless CMS
---

### Introduction

This project was created in April/May 2022 as the end project for my Java developer programme. Queriless is a content management system (CMS) to manipulate a database (MYSQL) through Create Read Update and Delete (CRUD) operations. 

### Tool-stack

- [Spring](https://spring.io/)
- [Javax validation](https://docs.oracle.com/javaee/7/api/javax/validation/package-summary.html)
- [JPA Data](https://javadoc.io/doc/org.springframework.data/spring-data-jpa/latest/index.html)
- [Lombok](https://projectlombok.org/api/)
- [LibrePDF](https://github.com/LibrePDF/OpenPDF)
- [Thymeleaf](https://www.thymeleaf.org/)
- [MYSQL](https://dev.mysql.com/doc/)
- [Java Mail Sender](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSender.html)

Testing:

- [JUnit](https://junit.org/junit5/docs/current/api/)
- [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)

### Usage

When you start the application you first need to login. There are 3 levels of authentication.

- Administrator: Is able to perform all CRUD operations.
- Editor: Can create, read and update.
- User: Is able to read. 

All security levels are able to create PDF files based on the database. Only the administrator can see and create a PDF file of the users table. Furthermore, the search function in the database menu will give you all search results based on a keyword and will list these on a page for you. 
