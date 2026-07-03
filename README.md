# E-Commerce Website

A full-stack E-Commerce web application developed using **Spring Boot**, **Spring Data JPA (Hibernate)**, **MySQL**, **HTML**, **CSS**, and **JavaScript**.

The application provides REST APIs and an admin interface for managing products, categories, orders, users, coupons, invoices, delivery slots, HSN/GST data, reviews, carts, and payments.

---

# Features

### User Management
- User Registration
- User Login
- Address Management

### Product Management
- Add Product
- Update Product
- Delete Product
- View Products
- Product Categories

### Category Management
- Create Category
- Update Category
- Delete Category
- HSN Code Mapping

### HSN & GST
- Import HSN Codes from CSV
- Automatic GST Rate Mapping
- View Available HSN Codes

### Shopping Cart
- Add to Cart
- Update Cart
- Remove Items
- Cart Total Calculation

### Order Management
- Place Order
- Order Items
- Order History

### Payment
- Payment Details
- Payment Tracking

### Coupon System
- Coupon Management
- Discount Calculation

### Reviews
- Product Reviews
- Ratings

### Delivery
- Delivery Slot Management

### Invoice
- Invoice Generation

---

# Tech Stack

## Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

## Database
- MySQL

## Frontend
- HTML5
- CSS3
- JavaScript

## IDE
- IntelliJ IDEA

---

# Project Structure

```
src/main/java/com/ecommerce

├── Controller
│   ├── AddressController
│   ├── CartController
│   ├── CartItemController
│   ├── CategoryController
│   ├── CouponController
│   ├── DeliverySlotController
│   ├── HsnController
│   ├── InvoiceController
│   ├── OrderController
│   ├── OrderItemController
│   ├── PaymentController
│   ├── ProductController
│   ├── ReviewController
│   └── UserController
│
├── Entity
│   ├── Address
│   ├── Cart
│   ├── CartItem
│   ├── Category
│   ├── Coupon
│   ├── DeliverySlot
│   ├── HsnData
│   ├── Order
│   ├── OrderItem
│   ├── Payment
│   ├── Product
│   ├── Review
│   └── User
│
├── Repository
│   ├── AddressRepository
│   ├── CartRepository
│   ├── CartItemRepository
│   ├── CategoryRepository
│   ├── CouponRepository
│   ├── DeliverySlotRepository
│   ├── HsnDataRepository
│   ├── OrderRepository
│   ├── OrderItemRepository
│   ├── PaymentRepository
│   ├── ProductRepository
│   ├── ReviewRepository
│   └── UserRepository
│
├── Service
│   ├── DiscountService
│   ├── HsnDataService
│   ├── HsnImportService
│   └── InvoiceService
│
└── EcommerceProjectApplication.java
```

---

# Database

The project uses MySQL.

Major tables include:

- User
- Address
- Product
- Category
- Cart
- Cart Item
- Order
- Order Item
- Coupon
- Payment
- Delivery Slot
- Review
- HSN Data

---

# Configuration

Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=1234a

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# Running the Project

Clone the repository

```bash
git clone https://github.com/aniketbirdawade/Ecommerce-website.git
```

Move into the project

```bash
cd Ecommerce-website
```

Run using Maven

```bash
mvn spring-boot:run
```

or simply run

```
EcommerceProjectApplication.java
```

from IntelliJ IDEA.

---

# HSN Import

HSN codes are imported from

```
src/main/resources/GST.csv
```

API

```
POST /api/hsn/import
```

Retrieve all HSN codes

```
GET /api/hsn/get-hsn
```

---

# REST APIs

The project contains REST APIs for:

- User
- Product
- Category
- Cart
- Cart Item
- Order
- Order Item
- Coupon
- Invoice
- Payment
- Review
- Delivery Slot
- Address
- HSN Data

---

# Author

**Aniket Birdawade**

- GitHub: https://github.com/aniketbirdawade/Ecommerce-website.git

---
