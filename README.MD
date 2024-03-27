# NotXApp Blog

## Application Description
NotXApp is a dynamic blogging platform designed to foster a community of sharing and open communication. The application allows users to create, edit, and delete blog posts, as well as comment on posts by others. It aims to provide a space for individuals to express their thoughts and connect with others on a variety of topics.

## Technologies Used
- **Spring Boot**: For creating stand-alone, production-grade Spring-based applications with ease.
- **Thymeleaf**: As the server-side Java template engine for web interfaces.
- **Spring Data JPA**: To abstract data layer, allowing seamless integration with various databases.
- **Spring Security**: For robust authentication and authorization.
- **Maven**: Dependency management and build automation.
- **Lombok**: To minimize boilerplate code in Java classes.
- **Bootstrap**: For responsive front-end design.



## Data Relationships
- **User**: Central to the application, representing both blog authors and commenters.
- **Post**: Linked to User with a Many-to-One relationship; a user can have many posts.
- **Comment**: Linked to both User and Post with a Many-to-One relationship; a post can have many comments, and a user can post many comments.
- **Role**: Linked to User with a Many-to-Many relationship; a user can have many roles, and a role can be assigned to many users.
  ![ERD.jpg](src%2Fmain%2Fresources%2FImages%2FERD.jpg)
## Future Enhancements

- **Social Media Integration**: Allowing users to share posts on their social media.
- **User Profiles**: Giving users the ability to create and edit detailed profiles.
- **Advanced Search**: Implementing full-text search to easily find relevant posts and comments.
- **Likes and comments**: Adding likes and being able to make comments
## Getting Started
To run this project, you will need to have Java and Maven installed on your machine. From your preferred Java IDE, run as a Spring Boot Application. On the initial run of the app, the necessary tables will be created in your database.
