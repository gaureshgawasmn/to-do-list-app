# To-Do List Application

This To-Do List application is a simple project built using Spring Boot for the backend with REST API and Angular/React for the frontend.

## Overview

The application allows users to manage their tasks effectively through a user-friendly interface. It enables users to create, update, delete, and view tasks.

## Features

- **Task Management**: Create, update, delete, and view tasks.
- **Task Filtering**: Sort and filter tasks based on completion status, due date, etc.
- **User Authentication** (Optional): Secure user accounts and tasks.

## Tech Stack

- **Backend**:
    - Spring Boot for RESTful API development
    - Java Persistence API (JPA) for managing entities and database interaction
    - Database: H2-Database

- **Frontend**:
    - [Angular / React]: Choose your preferred frontend framework
    - HTML, CSS, JavaScript/TypeScript

## Setup Instructions

### Backend Setup

1. Clone this repository.
2. Navigate to the `backend` directory.
3. Run the `gradle build` command
4. Pick up the jar from and run it with your favourite server
5. You can access h2 console at http://localhost:8081/h2-console
6. You can access swagger api at http://localhost:8081/swagger-ui/index.html