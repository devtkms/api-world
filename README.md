# api-world

This project aims to create and share various APIs based on Java and Spring.

## Setup
### Clone the Project
Run the following command in the folder where you want to create the project:
```
git clone https://github.com/devtkms/api-world.git
```
### Create Docker Containers
Run the following command in the root directory of the project:
```
docker-compose up --build
```
### Setup PostgreSQL
Follow the steps below to set up the database:
- Enter the PostgreSQL Docker container by running the following command:
```
docker exec -it apiworld-postgres-container bash
```

- Access the `api_world` database by running the following command:
```
psql -U postgres -d api_world
```
- Execute the SQL files located in the `sql` folder to create the necessary tables, if needed.

## About the API
### User Management API
This API provides the following functionalities:
- User registration
- Retrieve user information
- Update user information
- Delete user information
- Login

#### ◆User Registration
This functionality takes a `RegisterUserRequestDto` and returns a `RegisterUserResponseDto`.

The password from the received `RegisterUserRequestDto` is hashed and registered in the `users` table.

#### ◆Retrieve user information
This functionality retrieves user information by user ID. 

It takes the user ID as a path variable and returns a `GetUserResponseDto` containing the user's information.

#### ◆Update user information
This functionality updates user information. 

It takes an `UpdateUserRequestDto` as input and returns an `UpdateUserResponseDto` with the updated user's information.

#### ◆Delete user information
This functionality deletes a user by user ID. 

It takes the user ID as a path variable and does not return any data, only a success response.

#### ◆Login
This functionality authenticates a user. 

It takes a `LoginRequestDto` containing the user ID and password, and upon successful authentication, creates a security context and returns a success message wrapped in an `ApiResponseDto`.

## Naming Conventions
We are following the naming conventions below for this project:
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
