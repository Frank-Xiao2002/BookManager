# Book List

_Author: [Frank-Xiao2002](https://github.com/Frank-Xiao2002)_

_Date: 2024/4_

------

This project is a simple book list webapp that can simulate book management.

It utilizes the [SpringBoot](https://spring.io/projects/spring-boot) 3.2.5 and
[Vaadin](https://vaadin.com/) 24. Vaadin is a Java framework for building modern web applications that look great,
perform well, and make you and your users happy. Besides Vaadin, it also
uses [Spring Data JPA](https://spring.io/projects/spring-data-jpa) to interact with an in-memory
database [H2](https://h2database.com/),
and [Spring Security](https://spring.io/projects/spring-security) to secure the webapp.

## Features

- Book listing

  Display the book list with the following columns:
    - Title
    - Description
    - Amount
    - Author

  It allows you to change it, delete it, and add a new book.

  And you can find a book easily by searching for the title or author in the search bar.


- Pie Chart display *(Read the tips below)*

  Display the books on a chart based on the amount of each book.

## How to run

1. Clone the project & import it to your favorite IDE, for example, [IntelliJ IDEA](https://www.jetbrains.com/idea/).
2. run the main method in VaadinDemoApplication class, or use the run configuration together with the project.
3. The run configuration should open the browser and navigate to `http://localhost:8080/app` automatically if you're
   using IntelliJ IDEA. If not, you can open the browser and navigate to `http://localhost:8080/app` manually.
4. You can log in with the following credentials: (two users have no difference in permissions)
    - Username: `frank`, Password: `password`
    - Username: `admin`, Password: `password`

## Tips

To display the pie chart, you **must** have a **[Vaadin Pro](https://vaadin.com/pricing)** subscription.

If you're a student, be aware that you can get a **_free_** Vaadin Pro subscription by using your
[GitHub student pack](https://education.github.com/students).