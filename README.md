# 🎫 EventMaster

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.7-green?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-blue?style=for-the-badge&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-Authentication-black?style=for-the-badge&logo=jsonwebtokens)

**EventMaster** to kompleksowa platforma typu full-stack do zarządzania wydarzeniami i rezerwacji biletów. System obsługuje zaawansowany podział ról (Użytkownik, Organizator, Admin) oraz dba o integralność danych dzięki mechanizmom *Soft Delete* i rygorystycznym więzom SQL.

---

## ✨ Kluczowe Funkcjonalności

### 👤 Moduł Użytkownika
* **Przeglądanie**: Intuicyjny widok wydarzeń z systemem rekomendacji **"Podobne wydarzenia"**.
* **Rezerwacje**: System zakupu biletów z walidacją dostępnej puli w czasie rzeczywistym.
* **Historia**: Wgląd w szczegóły swoich zamówień i statusy płatności.

### 🏢 Moduł Organizatora
* **Zarządzanie Eventami**: Tworzenie i edycja wydarzeń (Szkic, Opublikowane, Anulowane).
* **Kontrola Sprzedaży**: Zarządzanie widocznością biletów i zmiana statusów rezerwacji klientów.
* **Profil Firmy**: Możliwość budowania wizerunku organizatora.

### 🛠️ Moduł Administratora
* **Moderacja**: Blokowanie/aktywowanie organizatorów oraz zarządzanie kategoriami i miejscami.
* **Bezpieczeństwo**: Automatyczne anulowanie wydarzeń przy dezaktywacji nieuczciwego organizatora.

---

## 🏗️ Architektura Techniczna

| Warstwa | Technologia | Opis |
| :--- | :--- | :--- |
| **Backend** | Java 17 + Spring Boot 3 | Silnik aplikacji oparty na architekturze warstwowej (Controller-Service-Repository). |
| **Baza Danych** | PostgreSQL | Przechowywanie danych z wykorzystaniem `JdbcTemplate` dla maksymalnej wydajności. |
| **Security** | Spring Security + JWT | Bezpieczna autoryzacja bezstanowa z podziałem na role. |
| **Model** | Lombok | Czysty kod dzięki automatycznej generacji getterów/setterów. |

---

## 📋 Database Schema Summary

Projekt wykorzystuje relacyjną strukturę zoptymalizowaną pod kątem sprzedaży biletów:

* **uzytkownicy & role**: Uwierzytelnianie i autoryzacja.
* **wydarzenia**: Główne dane wydarzeń wraz z ograniczeniami statusów (**Szkic**, **Opublikowane**, **Anulowane**).
* **typy_biletow**: Inwentarz biletów z flagami widoczności i śledzeniem dostępności.
* **rezerwacje & pozycje_rezerwacji**: Hierarchiczne dane dotyczące zakupów.
* **organizatorzy, miejsca, kategorie_wydarzen**: Metadane platformy.

---

## 🚀 Uruchomienie Projektu

1. **Wymagania**: Zainstalowane JDK 17 oraz PostgreSQL.
2. **Konfiguracja**: Ustaw parametry bazy w `src/main/resources/application.properties`.
3. **Kompilacja i Start**:
   ```bash
   mvn clean install
   mvn spring-boot:run
