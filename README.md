# Flight Booking System

## Overview
Flight Booking System is a microservices-based backend project built with Spring Boot. It supports airline registration, airline admin authentication, aircraft registration, flight creation, and notification handling.

This project is designed around different user roles such as system admin, airline admin, and customer, with role-based access for protected operations.

## Tech Stack
- Java
- Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- Maven
- REST APIs
- JWT Authentication
- MySQL
- Postman

## Project Structure
This project contains multiple services:

- `central-api`
  - Main service for business logic, authentication, airline registration, aircraft registration, and flight creation
- `db-api`
  - Handles database operations for users, airlines, aircraft, flights, and seat mappings
- `notification-api`
  - Handles notification and email-related operations

## Features
- Airline registration
- Airline approval and rejection flow
- Airline admin login with JWT token
- Aircraft registration by airline admin
- Flight creation by airline admin
- Seat mapping support
- Sub-flight creation for connecting flights
- Notification integration
- Flight search support

## User Roles
The system currently supports the following roles:

- `SYSTEM_ADMIN`
- `AIRLINE_ADMIN`
- `CUSTOMER`

### Role Permissions
- `AIRLINE_ADMIN`
  - Can register aircraft
  - Can create flights
- `SYSTEM_ADMIN`
  - Can manage airline approval/rejection flow
- `CUSTOMER`
  - Intended for booking-related features

## Authentication
JWT-based authentication is used for secured endpoints.

After login, a token is generated and must be passed in the request header:

```http
Authorization: Bearer <your_token>
