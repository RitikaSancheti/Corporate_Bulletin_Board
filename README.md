# 📰 Corporate Bulletin Board

A full-stack internal news feed for corporate communication, built with an **Angular** front end and a **Spring Boot RESTful** back end. Designed to keep every employee in the loop on the latest technical developments — Slack-feed simplicity, enterprise-grade architecture underneath.

![Corporate Bulletin Board Screenshot](https://raw.githubusercontent.com/RitikaSancheti/Corporate_Bulletin_Board/main/Screenshot.png)

---

## Features

- ➕ **Add** posts with a short paragraph, author name, and an auto-generated timestamp
- **Edit** existing posts
- **Delete** posts no longer relevant
- **Server-side timestamping** — date/time is set programmatically in a Java service layer, never trusted to the client
- **Newest-first ordering** — posts are queried in reverse-chronological order straight from the repository layer
- **H2 in-memory database**, pre-populated with sample posts at launch
- **Decoupled architecture** — Angular communicates with Spring Boot exclusively through a REST API; only the Spring Boot service talks to the database

---

## Tech Stack

| Layer | Technology |
|---|---|
| Frontend | Angular (compiled & webpacked into Spring Boot static resources) |
| Backend | Java Spring Boot (RESTful Web Service) |
| Database | H2 (in-memory) |
| ORM | Spring Data JPA |
| Build Tools | Maven (backend) · npm / Angular CLI (frontend) |

---

## Architecture & Separation of Concerns

This project follows a strict layered architecture on the backend:

```
Angular (Frontend)
        │
        │  HTTP requests (GET / POST / PUT / DELETE)
        ▼
RestController          ← handles HTTP requests/responses only
        │
        ▼
Service Layer           ← business logic + dynamic timestamp generation
        │
        ▼
JPA Repository           ← data access, ORDER BY queries (newest first)
        │
        ▼
H2 Database              ← persistence
```

Only the **Spring Boot service** ever talks to the H2 database directly — Angular never touches it. All date/time values are generated **server-side** inside the service layer, keeping the client dumb and the source of truth consistent.

---

## Project Structure

```
Corporate_Bulletin_Board/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/bulletinboard/
│       │       ├── controller/        # REST Controllers
│       │       ├── service/           # Business logic, timestamp generation
│       │       ├── repository/        # JPA Repositories (OrderBy queries)
│       │       ├── model/             # Post entity (POJO)
│       │       └── bootstrap/         # Seed data on startup
│       └── resources/
│           ├── static/                # Webpacked Angular build output
│           └── application.properties
├── angular-src/                       # Original Angular source (pre-build)
├── pom.xml
└── Screenshot.png
```

> Note: The Angular `node_modules` folder has been removed prior to upload. To rebuild the frontend, run `npm install` inside the Angular source folder first.

---

## How to Run This Project

### Prerequisites
- ✅ [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- ✅ [Eclipse IDE for Enterprise Java Developers](https://www.eclipse.org/downloads/packages/)
- ✅ [Node.js & npm](https://nodejs.org/) *(only needed if you want to rebuild the Angular source)*
- ✅ [Angular CLI](https://angular.io/cli) *(optional, for frontend development)*

---

### Step 1 — Get the Project

```bash
git clone https://github.com/RitikaSancheti/Corporate_Bulletin_Board.git
```

Or download as ZIP via **Code → Download ZIP** on the repo page.

---

### Step 2 — Import into Eclipse

1. Open **Eclipse**
2. **File → Import → Maven → Existing Maven Projects**
3. Browse to the cloned/extracted folder
4. Select the project (with `pom.xml`) → **Finish**
5. Wait for Maven to resolve dependencies

---

### Step 3 — Run the Spring Boot App

1. In **Project Explorer**, find the main class ending in `Application.java`
2. **Right-click → Run As → Spring Boot App**
3. Wait for the console to show:
   ```
   Tomcat started on port(s): 8080
   ```

---

### Step 4 — Open in Browser

Since Angular is already webpacked into `src/main/resources/static`, the entire app — frontend and backend — runs from a single port:

```
http://localhost:8080
```

No separate Angular dev server needed to view the working app! ✅

---

### (Optional) Rebuilding the Angular Frontend

If you want to modify the Angular source and rebuild:

```bash
cd angular-src
npm install
ng build --output-path=../src/main/resources/static --prod
```

This regenerates the webpacked, minified bundle directly into Spring Boot's static resources folder.

---

## H2 Database Console

Inspect the database directly while the app is running:

```
http://localhost:8080/h2-console
```

| Field | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:testdb` |
| Username | `sa` |
| Password | *(leave blank)* |

---

## REST API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/posts` | Retrieve all posts, newest first |
| `POST` | `/api/posts` | Create a new post (timestamp set server-side) |
| `PUT` | `/api/posts/{id}` | Edit an existing post |
| `DELETE` | `/api/posts/{id}` | Delete a post |

---

## Seed Data

The database is bootstrapped at launch with sample posts so the feed is never empty on first run — each with a tech-specs paragraph, an author, and a server-generated timestamp.

---

## 🙋‍♀️ Author

**Ritika Sancheti**
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=flat-square&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ritikasancheti30/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/RitikaSancheti)

---

<div align="center">
<sub>Built with ☕ Spring Boot & 🔺 Angular · Sheridan College</sub>
</div>
