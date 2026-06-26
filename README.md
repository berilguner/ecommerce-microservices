# Microservice E-Commerce Project

Spring Boot ile geliştirilmiş mikroservis tabanlı e-ticaret backend projesi.

## Services
- API Gateway (8080)
- User Service (8081)
- Product Service (8082)
- Order Service (8083)

## Tech Stack
- Spring Boot
- Spring Cloud Gateway
- Feign Client
- JWT Authentication
- MySQL
- Maven

## Features
- User register/login
- JWT authentication
- Role-based access (USER / ADMIN)
- API Gateway routing
- Microservice communication

## Run
Her servisi ayrı ayrı başlat:
user-service → product-service → order-service → api-gateway

Sonra:
http://localhost:8080 üzerinden test et.
