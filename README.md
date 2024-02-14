# Project Name

This project is developed using the Spring Framework.

## Prerequisites

Make sure you have the following prerequisites before running the project:

- **Java Version:21** [Specify the Java version used in the project, e.g., Java 11]
- **Spring Version:3.2.2** [Specify the Spring version used in the project, e.g., Spring Boot 2.5.0]
- **Database:MySQL8** [Specify the database used, e.g., MySQL]
- **Postman Collections: attached in files** [Include instructions or a link to the Postman collections]

## Setup

1. **Database Setup: Setup in application.properties**
   - Create a database in MySQL.
   - Update the database connection string in the `application.properties` file.

   ```properties
   # Example MySQL Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
## API 
1. updateContact
   ```Properties
   # EndPoint @PostCall
   http://localhost:8080/identity/updateContacts
   # Request
   {
    "phoneNumber": "9445647485",
    "email": "anuj@gmail.com",
    "linkPrecedence": "primary"
   }
   
   #Response
   {
    "message": "Both the emailId and PhoneNo are present"
   }
   
2. getDetails
   ```Properties
   #EndPoint @PostCall
   http://localhost:8080/identity/getDetails
   #Request
   {
    "email": "abc@gmail.com",
    "phoneNumber":"9645647485"
   }
   #response
   {
    "message": "Details found",
    "details": [
        {
            "id": 3,
            "phoneNumber": "9645647485",
            "email": "shankar@gmail.com",
            "linkedID": 0,
            "linkPrecedence": "primary",
            "createdAt": "2024-02-14T22:58:09.519659",
            "updatedAt": "2024-02-14T22:58:10.064794",
            "deletedAt": null
        },
        {
            "id": 4,
            "phoneNumber": "9645647485",
            "email": "shankar@gmail.com",
            "linkedID": 3,
            "linkPrecedence": "primary",
            "createdAt": "2024-02-14T23:30:00.339398",
            "updatedAt": "2024-02-14T23:30:00.339956",
            "deletedAt": null
        }
    ]
   }


