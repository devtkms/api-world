# api-world
This project aims to create various APIs using Java and Spring, while learning about topics such as OOP principles, design patterns, security, and database operations.

## About the API
### User Management API
This API allows for user registration, information retrieval, updating, and deletion.

- **POST** `/api/user/register`: Registers a new user.
- **GET** `/api/user/{userId}`: Retrieves the information of the specified user.
- **PUT** `/api/user/{userId}`: Updates the information of the specified user.
- **DELETE** `/api/user/{userId}`: Deletes the specified user.

Through this API, I learned about SOLID, DRY, and KISS principles.

#### ◆SOLID
Responsibilities are divided among the Controller, Service, Repository, and DAO.

#### ◆DRY
Utility classes are used to avoid duplication of processing.

#### ◆KISS
Complex processing is avoided, keeping the implementation simple.

## Naming Conventions
In this project, we follow the naming conventions below. This enhances code readability and facilitates maintenance.

- **Controller**  
  `[API Purpose]Controller.java`
- **Service**  
  `[API Purpose]Service.java`
- **DTO**  
  `[API Purpose]RequestDto.java` : for request  
  `[API Purpose]ResponseDto.java` : for response
- **Repository**  
  `[API Purpose]Repository.java`
- **DAO**  
  `[Table Name]Dao.java`