# Alpas Coding Challenge – Price Service API

## Overview
The **Price Service Application** is responsible for recording price changes and calculating the average price over the last 30 seconds. It provides a RESTful API with endpoints to record, retrieve, and compute average prices.


---
## 🛠 Tech Stack
- **Java 21** – Core programming language
- **Spring Boot** – Backend framework for REST API
- **Gradle** – Build and dependency management
- **Docker** – Containerization for deployment
- **Swagger (SpringDoc OpenAPI)** – API documentation
- **SLF4J + Logback** – Logging framework

---
## 🚀 Features
</br>✅ **Record Price** – Store price data with timestamps (`POST /api/v1/prices`)
</br>✅ **Get Average Price** – Retrieve the average price for the last 30 seconds (`GET /api/v1/average-prices`)
</br>✅ **Get All Prices** – Fetch all recorded price data (`GET /api/v1/prices`)

---

## 📌 Prerequisites
Ensure you have the following installed and running before executing the application:
- Docker (Daemon must be running)

---

## 🛠 Building and Running the Service
### Using build script
The project includes a build script (`build.sh`) for 
compiling, packaging, and running the application inside a **Docker container**.

### Build the application
**1️⃣ Grant execution permission (only once):**

```sh
chmod +x build.sh
````
**2️⃣ Run the build script:**

```
./build.sh
```

This will:

- **Compile the application** using Gradle
- **Run tests** before packaging
- **Build a Docker image**
- **Start the containerized application** on port 8080

### Running the Application
Once the Docker image is built, the script will automatically run the container.
The service will be accessible at:
</br>📌 Base URL: http://localhost:8080/swagger-ui/index.html#/

---

## 📄 API Documentation
Swagger UI provides an interactive way to explore and test API endpoints.
After running the application, 
</br>📌 access it at: http://localhost:8080/swagger-ui/index.html#/