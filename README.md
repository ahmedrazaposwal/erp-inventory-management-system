# ERP Inventory Management System

A full-stack inventory management system for managing products, suppliers, purchase orders, stock movements, and warehouse operations.

The backend provides secured REST APIs, while the React frontend provides a responsive user interface.

## Technology Stack

### Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT authentication
- PostgreSQL
- Maven

### Frontend

- React
- Vite
- React Router
- CSS
- ESLint
- Lucide React icons

## Features

### Backend

- User registration and login
- JWT-based authentication
- Role-based API security
- Product CRUD operations with pagination and search
- Supplier management
- Purchase-order creation and receiving
- Automatic inventory updates when orders are received
- Stock-in and stock-out transactions
- Inventory transaction history
- PostgreSQL persistence

### Frontend

- Responsive React application layout
- Protected routes
- Login and logout workflow
- JWT-authenticated API requests
- Responsive sidebar and header
- API-powered inventory dashboard
- Product statistics
- Paginated product table
- Debounced product search
- Product creation form
- Product soft deletion with confirmation dialog
- Loading, success, empty, and error states
- Accessible controls and responsive styling

### In Progress

- Product editing
- Supplier interface
- Purchase-order interface
- Inventory-movement interface
- Reports and charts
- Role-based frontend navigation
- Improved authentication and token-expiration handling

## Project Structure

```text
erp-inventory-management-system/
├── backend/                 # Spring Boot REST API
│   ├── src/
│   ├── pom.xml
│   └── mvnw
├── frontend/                # React application
│   ├── public/
│   ├── src/
│   └── package.json
└── README.md
```

## Prerequisites

Install the following before running the project:

- Java 21
- Node.js and npm
- PostgreSQL
- Git

## Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE <database_name>;
```

The backend connects to:

```text
jdbc:postgresql://localhost:5432/<database_name>
```

## Backend Configuration

Create the following file:

```text
backend/.env
```

Add your local configuration:

```env
DB_USERNAME=your_postgresql_username
DB_PASSWORD=your_postgresql_password
SECRET_KEY=replace_with_a_long_secure_secret

# JWT expiration time in milliseconds (1,234,567 ms ≈ 20 minutes 35 seconds)
JWTExpirationMS=1234567

Do not commit this file. It contains sensitive information and is ignored by Git.

## Frontend Configuration

Create:

```text
frontend/.env.local
```

Add:

```env
VITE_API_BASE_URL=http://localhost:8080
```

Frontend environment variables are visible in the browser. Never place database passwords or JWT signing secrets in frontend environment files.

## Running the Backend

```bash
cd backend
./mvnw spring-boot:run
```

The backend runs at:

```text
http://localhost:8080
```

## Running the Frontend

Open another terminal:

```bash
cd frontend
npm install
npm run dev
```

The frontend runs at:

```text
http://localhost:5173
```

## Main API Endpoints

### Authentication

```text
POST /api/auth/register
POST /api/auth/login
```

### Products

```text
POST   /api/products
GET    /api/products
GET    /api/products/{id}
PUT    /api/products/{id}
DELETE /api/products/{id}
GET    /api/products/search
GET    /api/products/category
```

### Suppliers

```text
POST   /api/suppliers
GET    /api/suppliers
GET    /api/suppliers/{id}
PUT    /api/suppliers/{id}
DELETE /api/suppliers/{id}
GET    /api/suppliers/search
```

### Purchase Orders

```text
POST /api/purchase-orders
GET  /api/purchase-orders
GET  /api/purchase-orders/{id}
PUT  /api/purchase-orders/{id}/receive
```

### Inventory

```text
POST /api/inventory/stock-in
POST /api/inventory/stock-out
GET  /api/inventory/history/{productId}
```

Protected endpoints require a JWT:

```http
Authorization: Bearer YOUR_JWT_TOKEN
```

## Frontend Checks

Run ESLint:

```bash
cd frontend
npm run lint
```

Create a production build:

```bash
npm run build
```

The generated production files are placed in `frontend/dist`.

## Backend Tests

```bash
cd backend
./mvnw test
```


### 3. Replace `## Development Status`

```markdown
## Development Status

This project is under active development.

The Spring Boot backend supports authentication, products, suppliers, purchase orders, and inventory transactions. The React frontend currently includes authentication, protected navigation, an API-powered dashboard, and product creation, search, pagination, and deletion workflows.

Product editing and the remaining supplier, purchasing, inventory, and reporting interfaces are planned next.

## Author

Developed by [Ahmed Raza Poswal](https://github.com/ahmedrazaposwal).
