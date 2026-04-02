# Payment Service

Backend-сервис для управления платежами.

## 📌 Описание

Сервис позволяет:
- создавать платежи
- получать платеж по ID
- подтверждать платеж
- отменять платеж
- получать платежи по клиенту

Проект написан с использованием:
- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL

---

## ⚙️ Технологии

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- Hibernate

---

## 🗄️ Структура базы данных

### Таблица `clients`

- id (BIGINT, PK)
- external_id (VARCHAR, уникальный идентификатор клиента)

### Таблица `payments`

- id (BIGINT, PK)
- amount (DECIMAL)
- currency (VARCHAR)
- description (VARCHAR)
- status (VARCHAR)
- created_at (TIMESTAMP)
- client_id (BIGINT, FK → clients.id)


📡 API

Базовый URL:

http://localhost:8080
➕ Создать платеж
POST /payments
Content-Type: application/json

Body:
{
  "amount": 100.50,
  "currency": "USD",
  "description": "Test payment",
  "clientId": 1
}


📥 Получить платеж по ID
GET /payments/{id}
✅ Подтвердить платеж
POST /payments/{id}/confirm
❌ Отменить платеж
POST /payments/{id}/cancel
👤 Получить платежи клиента
GET /payments/client/{clientId}
📦 Пример ответа
{
  "paymentId": 1,
  "amount": 100.50,
  "currency": "USD",
  "description": "Test payment",
  "clientId": 1,
  "status": "PENDING"
}
