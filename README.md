# Backend API

This project provides a backend API for user registration, login, donor creation, and recipient requests. It uses Spring Boot, JWT for authentication, and bcrypt for password encoding.

## Features

- User registration
- User login
- JWT-based authentication
- Secure API endpoints
- Donation creation
- Recipient request handling

## Prerequisites

- Java 17
- Maven
- MySQL

## Getting Started

### Setup

1. **Clone the repository:**
    https://github.com/rock-02/foodwastemanagement.git
  

2.spring.application.name=backend
```
server.port=8081

# DataSource Configuration
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3307/foodwaste
spring.datasource.username=root
spring.datasource.password=root

# Jackson Configuration
spring.jackson.serialization.fail-on-empty-beans=false

# JPA Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Mail Configuration (SMTP)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@example.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

3. **Build the project:**
    ```bash
    mvn clean install
    ```

4. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication

- **Register a new user:**
    ```http
    POST http://localhost:8080/auth/signup
    ```
    **Request Body:**
    ```json
    {
      "userName": "user5",
      "email": "user5@example.com",
      "password": "password123",
      "role": "DONOR",
      "profilePicture": "profile1.jpg",
      "address": {
        "area": "Area 1",
        "city": "City 1",
        "district": "District 1",
        "state": "State 1",
        "pinCode": "12345"
      }
    }
    ```

- **Login:**
    ```http
    POST http://localhost:8081/auth/login
    ```
    **Request Body:**
    ```json
    {
       "email": "user10@example.com",
       "password": "password123"
    }
    ```
    **Response:**
    ```json
    {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTBAZXhhbXBsZS5jb20iLCJlbWFpbCI6InVzZXIxMEBleGFtcGxlLmNvbSIsImlzcyI6Im1lZGlhU2NvY2lhbCIsImlhdCI6MTcxODc0Mjg2MiwiZXhwIjoxNzE5NjA2ODYyfQ.je0Te4_W18IF65IdDG7tB27vG8LabuJY3GZJOek9b4g",
        "message": "Login Success"
    }
    ```

### Donor

- **Create a donor entry:**
    ```http
    POST http://localhost:8081/api/donor/create
    ```
    **Request Body:**
    ```json
    {
        "foodDescription": "noveg",
        "foodType": "Perishable",
        "quantity": 15
    }
    ```
    **Response:**
    ```json
    {
        "id": 7,
        "foodDescription": "noveg",
        "foodType": "Perishable",
        "quantity": 15,
        "donor": {
            "id": 11,
            "userName": "user7",
            "email": "tarunmaidur@gmail.com",
            "role": {
                "id": 11,
                "name": "DONOR"
            },
            "profilePicture": null,
            "address": {
                "area": "Area 1",
                "city": "City 1",
                "district": "District 1",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": false
        },
        "donationStatus": "On the Way",
        "createdAt": "2024-06-18T20:33:57.100+00:00",
        "recipient": {
            "id": 7,
            "userName": "user7",
            "email": "user6@example.com",
            "role": {
                "id": 7,
                "name": "RECIPIENT"
            },
            "profilePicture": "profile1.jpg",
            "address": {
                "area": "State",
                "city": "City 1",
                "district": "dkkdkd",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": false
        },
        "deleveryBoy": {
            "id": 10,
            "userName": "user7",
            "email": "user10@example.com",
            "role": {
                "id": 10,
                "name": "DELEVERY_AGENT"
            },
            "profilePicture": null,
            "address": {
                "area": "Area 1",
                "city": "City 1",
                "district": "District 1",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": false
        },
        "deleveryDateTimeStarted": "2024-06-18T20:33:57.437+00:00",
        "deleveryExpectedDateTime": "2024-06-18T23:33:57.437+00:00"
    }
    ```

### Recipient

- **Submit a request:**
    ```http
    PUT http://localhost:8081/api/recipient/request
    ```
    **Request Body:**
    ```json
    {
        "NGOName": "Environmental Awareness Group",
        "contactPerson": "Jane Smith",
        "registrationNumber": "",
        "email": "",
        "phoneNumber": "987-654-3210",
        "address": "456 Park Ave, Townsville, State, Country",
        "requestDescription": ""
    }
    ```
    **Response:**
    ```json
    {
        "id": 7,
        "userName": "user7",
        "email": "user6@example.com",
        "role": {
            "id": 7,
            "name": "RECIPIENT"
        },
        "profilePicture": "profile1.jpg",
        "address": {
            "area": "State",
            "city": "City 1",
            "district": "dkkdkd",
            "state": "State 1",
            "pinCode": "12345"
        },
        "requestDonation": true,
        "requestDateTime": "2024-06-18T20:32:48.052+00:00",
        "availableToDelevery": false
    }
    ```

    ***Get UserDonations:***
    ```http
    Get http://localhost:8081/api/donor/donations
    ```
    **json Respnse:**
    ```
    [
    {
        "id": 6,
        "foodDescription": "noveg",
        "foodType": "Perishable",
        "quantity": 15,
        "donor": {
            "id": 11,
            "userName": "user7",
            "email": "tarunmaidur@gmail.com",
            "role": {
                "id": 11,
                "name": "DONOR"
            },
            "profilePicture": null,
            "address": {
                "area": "Area 1",
                "city": "City 1",
                "district": "District 1",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": false
        },
        "donationStatus": "Delevered",
        "createdAt": "2024-06-18T20:18:36.773+00:00",
        "recipient": {
            "id": 7,
            "userName": "user7",
            "email": "user6@example.com",
            "role": {
                "id": 7,
                "name": "RECIPIENT"
            },
            "profilePicture": "profile1.jpg",
            "address": {
                "area": "State",
                "city": "City 1",
                "district": "dkkdkd",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": false
        },
        "deleveryBoy": {
            "id": 10,
            "userName": "user7",
            "email": "user10@example.com",
            "role": {
                "id": 10,
                "name": "DELEVERY_AGENT"
            },
            "profilePicture": null,
            "address": {
                "area": "Area 1",
                "city": "City 1",
                "district": "District 1",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": true
        },
        "deleveryDateTimeStarted": "2024-06-18T20:18:36.801+00:00",
        "deleveryExpectedDateTime": "2024-06-18T23:18:36.801+00:00"
    },
    {
        "id": 7,
        "foodDescription": "noveg",
        "foodType": "Perishable",
        "quantity": 15,
        "donor": {
            "id": 11,
            "userName": "user7",
            "email": "tarunmaidur@gmail.com",
            "role": {
                "id": 11,
                "name": "DONOR"
            },
            "profilePicture": null,
            "address": {
                "area": "Area 1",
                "city": "City 1",
                "district": "District 1",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": false
        },
        "donationStatus": "Delevered",
        "createdAt": "2024-06-18T20:33:57.100+00:00",
        "recipient": {
            "id": 7,
            "userName": "user7",
            "email": "user6@example.com",
            "role": {
                "id": 7,
                "name": "RECIPIENT"
            },
            "profilePicture": "profile1.jpg",
            "address": {
                "area": "State",
                "city": "City 1",
                "district": "dkkdkd",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": false
        },
        "deleveryBoy": {
            "id": 10,
            "userName": "user7",
            "email": "user10@example.com",
            "role": {
                "id": 10,
                "name": "DELEVERY_AGENT"
            },
            "profilePicture": null,
            "address": {
                "area": "Area 1",
                "city": "City 1",
                "district": "District 1",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": true
        },
        "deleveryDateTimeStarted": "2024-06-18T20:33:57.437+00:00",
        "deleveryExpectedDateTime": "2024-06-18T23:33:57.437+00:00"
    }
     ]
    ```
### Feedback

- **Submit feedback:**
    ```http
    POST http://localhost:8080/api/recipient/feedback/18
    ```
    **Request Body:**
    ```json
    {
        "feedback": "really nice food",
        "rating": 4.5
    }
    ```
### Delivery Status Update

- **Update donation delivery status:**
    ```http
    PUT http://localhost:8081/api/delivery/donation/7/Delevered
    ```
    **Request Body:**
    ```json
    {
        "id": 7,
        "foodDescription": "noveg",
        "foodType": "Perishable",
        "quantity": 15,
        "donor": {
            "id": 11,
            "userName": "user7",
            "email": "tarunmaidur@gmail.com",
            "role": {
                "id": 11,
                "name": "DONOR"
            },
            "profilePicture": null,
            "address": {
                "area": "Area 1",
                "city": "City 1",
                "district": "District 1",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": false
        },
        "donationStatus": "Delevered",
        "createdAt": "2024-06-18T20:33:57.100+00:00",
        "recipient": {
            "id": 7,
            "userName": "user7",
            "email": "user6@example.com",
            "role": {
                "id": 7,
                "name": "RECIPIENT"
            },
            "profilePicture": "profile1.jpg",
            "address": {
                "area": "State",
                "city": "City 1",
                "district": "dkkdkd",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": false
        },
        "deleveryBoy": {
            "id": 10,
            "userName": "user7",
            "email": "user10@example.com",
            "role": {
                "id": 10,
                "name": "DELEVERY_AGENT"
            },
            "profilePicture": null,
            "address": {
                "area": "Area 1",
                "city": "City 1",
                "district": "District 1",
                "state": "State 1",
                "pinCode": "12345"
            },
            "requestDonation": false,
            "requestDateTime": null,
            "availableToDelevery": true
        },
        "deleveryDateTimeStarted": "2024-06-18T20:33:57.437+00:00",
        "deleveryExpectedDateTime": "2024-06-18T23:33:57.437+00:00"
    }
    ```


