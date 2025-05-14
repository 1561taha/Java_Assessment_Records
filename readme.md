# RecordEase - CRUD Assessment Submission

## Overview

**RecordEase** is a full-stack CRUD application built with:
- **Backend:** Spring Boot (Java), MySQL, JWT authentication
- **Frontend:** React (functional components, hooks, Axios)
- **Database:** MySQL

This project fulfills all requirements of the assessment, including authentication, registration, CRUD operations, category management, filtering, searching, and a modern, responsive UI.

---

## Features Implemented

### 1. **Authentication**
- **Login** with email and password (JWT-based, all error scenarios handled)
- **Registration** with name, email, password, and gender

### 2. **CRUD for Records**
- **Create, Read, Update, Delete** records
- Each record has: name (text), description (textarea), category (dropdown from DB), active/inactive (boolean toggle)
- **Bulk delete** from the list view

### 3. **Category Management**
- Categories are stored in the database
- Users can **add new categories** directly from the record creation form

### 4. **Listing, Filtering, and Searching**
- List all records for the logged-in user (no pagination required)
- **Filter** by active/inactive status
- **Search** records by name (case-insensitive)

### 5. **Modern, Responsive UI**
- Clean, modern design using custom CSS
- Fully responsive for desktop and mobile
- Dynamic forms and modals

---

## How Each Assessment Requirement is Met

| Requirement | Implementation |
|-------------|----------------|
| **Login/Registration** | `/api/login` and `/api/register` endpoints, React forms, JWT auth |
| **CRUD Operations** | `/api/records` endpoints, React forms and tables |
| **Edit/Create Form Fields** | Name (text), Description (textarea), Category (dropdown), Active (checkbox) |
| **Category Dropdown** | Fetched from `/api/categories`, can add new category from form |
| **Active/Inactive Toggle** | Checkbox in form, stored as boolean in DB |
| **List All Records** | Table view, no pagination |
| **Filter by Active/Inactive** | Dropdown filter in UI, backend query param |
| **Search by Name** | Search input in UI, backend query param |
| **Delete Record** | Single delete (from detail view), bulk delete (from list) |
| **Modern, Presentable UI** | Custom CSS, responsive layout, clean forms and tables |
| **Backend Quality** | Clean, modular Spring Boot code, DTOs, services, repositories, error handling |

---

## How to Run

### **Backend (Spring Boot)**
1. Ensure MySQL is running and accessible.
2. Update `src/main/resources/application.properties` if needed (DB credentials).
3. In the `Backend/CRUD` directory:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```
   The backend will run on [http://localhost:8084](http://localhost:8084).

### **Frontend (React)**
1. In the `Frontend/frontend` directory:
    ```sh
    npm install
    npm start
    ```
   The frontend will run on [http://localhost:3000](http://localhost:3000).

---

## Usage

- **Register** a new user or login with existing credentials.
- **Create** a new record, selecting or adding a category.
- **Edit** or **delete** records.
- **Bulk delete** by selecting multiple records.
- **Filter** by active/inactive, or **search** by name.

---

## Project Structure

```
Backend/
  CRUD/
    src/
      main/
        java/com/Record/CRUD/
          Controller/
          Model/
          Repo/
          Service/
          Config/
        resources/
          application.properties
Frontend/
  frontend/
    src/
      components/
      services/
      styles.css
      App.js
      ...
```

---

## Notes

- All endpoints are protected with JWT except `/api/login` and `/api/register`.
- CORS is enabled for frontend-backend communication.
- The UI is fully responsive and tested on both desktop and mobile.

---



---

## Author

- **Taha Khan**
- **Submission Date:** 15 May 2025

---

**Thank you for reviewing my submission! All assessment requirements are fully implemented and demonstrated in this project.**