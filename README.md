# Offline Mesh UPI System

A distributed offline-first payment simulation system inspired by real-world UPI resilience architecture.

This project demonstrates how payment packets can propagate across nearby devices using a mesh network when internet connectivity is unavailable. Once a bridge device gets internet access, packets are synchronized with the backend settlement engine.

---

# Features

- Offline payment packet simulation
- Gossip protocol based packet propagation
- Bridge device synchronization
- Idempotent transaction handling
- Settlement engine for balance transfer
- Account balance management
- Mesh network simulator
- Real-time dashboard UI
- REST APIs using Spring Boot
- MySQL database integration
- Swagger API documentation

---

# Tech Stack

## Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## Frontend
- HTML
- CSS
- JavaScript

## Tools
- Postman
- Swagger UI
- GitHub

---

# Architecture

```text
User Device
     ↓
Mesh Gossip Propagation
     ↓
Bridge Device
     ↓
Backend Settlement Engine
     ↓
MySQL Database
```

---

# System Flow

1. User creates payment packet offline  
2. Packet propagates across nearby devices using gossip protocol  
3. Bridge device uploads packets to backend  
4. Backend validates and decrypts packet  
5. Settlement engine transfers balances  
6. Transaction stored in database  

---

# API Endpoints

| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST | `/api/demo/create-packet` | Create payment packet |
| POST | `/api/mesh/inject` | Inject packet into mesh |
| POST | `/api/mesh/gossip` | Run gossip propagation |
| POST | `/api/mesh/flush` | Upload bridge packets |
| POST | `/api/mesh/reset` | Reset mesh |
| GET | `/api/mesh/state` | Get mesh state |
| GET | `/api/accounts` | Get balances |
| GET | `/api/transactions` | Get transaction history |

---

# Dashboard Preview

## Main Dashboard

![Dashboard](https://raw.githubusercontent.com/Ashutoshpandey2580/offline-mesh-upi-simulation/main/images/dashboard-main.png)

---

## Mesh State API

![Mesh State](https://raw.githubusercontent.com/Ashutoshpandey2580/offline-mesh-upi-simulation/main/images/postman-mesh-state.png)

---

## Account Balances API

![Balances](https://raw.githubusercontent.com/Ashutoshpandey2580/offline-mesh-upi-simulation/main/images/postman-balances.png)

---

## Transactions API

![Transactions](https://raw.githubusercontent.com/Ashutoshpandey2580/offline-mesh-upi-simulation/main/images/postman-transactions.png)

---

## Swagger Documentation

![Swagger](https://raw.githubusercontent.com/Ashutoshpandey2580/offline-mesh-upi-simulation/main/images/swaggerdocumentation-ui.png)

# How To Run

## Clone Repository

```bash
git clone <your-github-repo-link>
```

---

## Open Project

Open project in IntelliJ IDEA or VS Code.

---

## Configure MySQL

Create database:

```sql
CREATE DATABASE offline_mesh_upi;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/offline_mesh_upi
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

## Open Dashboard

```text
http://localhost:8080
```

---

## Open Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Learning Outcomes

- Built scalable REST APIs using Spring Boot
- Implemented layered backend architecture
- Worked with MySQL + JPA
- Simulated distributed system behavior
- Understood gossip-based propagation
- Implemented idempotent transaction processing
- Built interactive frontend dashboard

---

# Future Improvements

- JWT Authentication
- End-to-end encryption
- WebSocket real-time sync
- Docker deployment
- Kubernetes scaling
- Redis caching

---

# Author

Ashutosh Pandey

Backend Developer | Java + Spring Boot
