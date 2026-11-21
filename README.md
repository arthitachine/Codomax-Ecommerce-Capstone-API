# E-Commerce Backend Capstone (Spring Boot REST API)

Capstone project for **Module 06: Java Capstone Project** in the Codomax Digital Solutions Java Development Internship.

## Multi-Tiered Architecture
- **Controller (`StoreController`):** Exposes REST endpoints under `/api/products` and `/api/orders`.
- **Service (`StoreService`):** Handles inventory stock validation, order total calculation, and domain exceptions.
- **Model & Schema (`Product`, `Order`, `schema.sql`):** Defines entities and relational `products` -> `orders` foreign key schema.
- **Exception Handling (`GlobalExceptionHandler`):** Returns structured `404` and `400` JSON responses.

## API Endpoints
- `POST /api/products` — Create a product (`201 Created`)
- `GET /api/products` — List all products (`200 OK`)
- `GET /api/products/{id}` — Fetch product by ID (`200 OK` / `404 Not Found`)
- `POST /api/orders` — Place an order & decrement product stock (`201 Created` / `400 Bad Request`)
- `GET /api/orders` — List all placed orders (`200 OK`)

## Build & Run
```bash
mvn spring-boot:run
