# FreshLink Payment Service

This microservice handles payments for the FreshLink platform, including:

- UPI / Card / Wallet payment processing
- Atomic ledger accounting
- Wallet balance engine
- Refund pipeline
- JWT secured APIs
- Kafka event publication (in progress)

## Features

### 🔹 Payment Intent
Accepts payment requests for checkout orders and processes them with ledger updates.

### 🔹 Payment Gateway Abstraction
Supports multiple payment modes (UPI, Card, Wallet) with pluggable gateway implementations.

### 🔹 Wallet & Ledger
Records every debit/credit in the ledger, enabling wallet balance tracking and auditability.

### 🔹 Secure APIs
Uses JWT tokens for secure service-to-service and user-to-service communication.

---

## API Endpoints

### POST /api/payments/pay
Processes a payment intent
**Body:**
```json
{
  "checkoutId": 1,
  "amount": 512.5,
  "paymentMode": "UPI"
}
