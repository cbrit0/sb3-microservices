# 🛒 Java Microservices E-Commerce Backend

This is my **first Java and microservices project**, built as a learning exercise. It is **not production-ready**, but showcases key architectural patterns and technologies commonly used in distributed systems.

---

## 🧩 Overview

This backend simulates a small e-commerce system using Spring Boot microservices. It includes:

- **API Gateway** with Resilience4J for routing and fault tolerance
- **Authentication** via Keycloak
- **Product, Order, Inventory, and Notification services**
- **Synchronous (REST)** and **Asynchronous (Kafka)** communication
- **MongoDB** and **MySQL** for data storage
- **Observability** with Prometheus, Grafana, Loki, and Tempo
- **Dockerized** services for local development

> 🐳 Deployment is containerized but **Kubernetes setup is still pending** and will be added soon.

---

## ⚙️ Stack

- **Backend**: Java, Spring Boot, Spring Cloud Gateway, Kafka, Resilience4J
- **Auth**: Keycloak
- **Databases**: MongoDB, MySQL
- **Monitoring**: Prometheus, Grafana, Loki, Tempo
- **Containerization**: Docker
- **Orchestration**: Kubernetes *(coming soon)*

---

## 🖼️ Frontend

The frontend is available in a separate repository, forked from the original course project.

---

## 🙏 Credits

This project is based on the excellent [Spring Boot 3 Observability with Grafana Stack](https://programmingtechie.com/articles/spring-boot3-observability-grafana-stack) course by **SaiUpadhyayula**. Huge thanks to the creator for providing a structured learning path.

---

## 📌 Note

This project is for educational purposes only. If you're reviewing it and have suggestions for improvement, feel free to reach out!
