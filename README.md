# KioskMarketPlace

Welcome to KioskMarketPlace, a web application developed using Java Spring Boot for the backend, and JavaScript Vue.js with Bootstrap for the frontend. This project aims to provide a marketplace platform accessible via kiosks, facilitating transactions and interactions between users and vendors.

## Features

- **User Authentication**: Secure user authentication system allowing users to register, login, and manage their accounts securely.
- **Product Listings**: Browse through a wide range of products listed by various vendors.
- **Search and Filter**: Easily search for products and filter results based on categories, prices, etc.
- **Vendor Management**: Vendors can register, login, and manage their product listings and orders.
- **Order Processing**: Seamless order processing system for users to purchase products and vendors to manage orders.

## Technologies Used

- **Backend**:
  - Java Spring Boot
  - MySQL for local database management
  - Hibernate ORM for entity mapping and database interaction

- **Frontend**:
  - Vue.js for dynamic user interfaces
  - Bootstrap for responsive and mobile-first design

## Getting Started

### Prerequisites

- Java JDK
- Maven
- Node.js
- Vue CLI
- MySQL Server

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/KioskMarketPlace.git

2. Navigate to the backend directory:
   ```bash
   cd backend
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run

4. Navigate to the frontend directory:
   ```bash
   cd ../frontend

5. Install dependencies:
   ```bash
   npm install

6. Run the Vue.js application:
   ```bash
   npm run serve

7. Access the application at http://localhost:8080 in your web browser.

**Note: Since the database is local, each developer will need to set up their own local MySQL instance with appropriate schema and data for testing purposes.**
