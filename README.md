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
Need to have Java 17 or higher and gradle 8.4 or higher to run this application.

### Backend Setup

1. Clone this repository.
2. Navigate to the `todolist(root directory where build.gradle is present` directory.
3. Run the `gradle build` command
4. Pick up the jar `todolist-0.0.1-SNAPSHOT-plain.jar` from `build/libs` and run it with your favourite server.
5. If you want to run is directly use the command `java -jar todolist-0.0.1-SNAPSHOT.jar` by navigating to the directory where you have jar present.
6. You can access h2 console at http://localhost:8081/h2-console
7. You can access swagger api at http://localhost:8081/swagger-ui/index.html
8. if you want to run it directly from IDEA then navigate to the class `com/techlab/todolist/ToDoListApplication.java` run/debug as java application.