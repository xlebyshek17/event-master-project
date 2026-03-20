# 🎫 EventMaster

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.7-green?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-blue?style=for-the-badge&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-Authentication-black?style=for-the-badge&logo=jsonwebtokens)


**EventMaster** is a comprehensive full-stack platform for event management and ticket reservations. The system supports advanced role separation (User, Organizer, Admin) and ensures data integrity through *Soft Delete* mechanisms and rigorous SQL constraints.

---

## ✨ Key Features

### 👤 User Module
* **Browsing**: An intuitive view of events featuring a **"Similar Events"** recommendation system.
* **Reservations**: A ticket purchasing system with real-time validation of available ticket pools.
* **History**: Access to order details and payment statuses.

### 🏢 Organizer Module
* **Event Management**: Creating and editing events with specific statuses (**Draft**, **Published**, **Canceled**).
* **Sales Control**: Managing ticket visibility and updating customer reservation statuses.
* **Company Profile**: Tools for building and managing the organizer's professional image.

### 🛠️ Administrator Module
* **Moderation**: Ability to block or activate organizers and manage categories and venues.
* **Security**: Automatic cancellation of events upon the deactivation of a fraudulent organizer.

---

## 🏗️ Technical Architecture

| Layer | Technology | Description |
| :--- | :--- | :--- |
| **Backend** | Java 17 + Spring Boot 3 | Application engine based on a layered architecture (Controller-Service-Repository). |
| **Database** | PostgreSQL | Data storage utilizing `JdbcTemplate` for maximum performance. |
| **Security** | Spring Security + JWT | Secure stateless authentication with role-based access control. |
| **Model** | Lombok | Clean code achieved through automatic generation of getters and setters. |

---

## 📋 Database Schema Summary

The project uses a relational structure optimized for ticket sales:

* **users & roles**: Authentication and authorization.
* **events**: Main event data with status constraints (**Draft**, **Published**, **Canceled**).
* **ticket_types**: Ticket inventory with visibility flags and availability tracking.
* **bookings & booking_items**: Hierarchical data regarding purchases.
* **organizers, venues, event_categories**: Platform metadata.

---

## 🚀 Running the Project

1. **Requirements**: Installed JDK 17 and PostgreSQL.
2. **Configuration**: Set the database parameters in `src/main/resources/application.properties`.
3. **Compilation and Launch**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

Would you like me to help you refine this documentation further or perhaps generate a technical summary for your LinkedIn profile?
