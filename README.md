
# Smart MedApp 

**Smart MedApp** is a full-stack medicine recommendation web application that suggests medicines and age-specific dosages based on user-selected symptoms.

## Tech stack
- **Backend:** Java, Spring Boot, Spring Data JPA (Hibernate)  
- **Frontend:** Thymeleaf, HTML, CSS, JavaScript  
- **Database:** MySQL  
- **Build:** Maven

## Features
- Select symptoms (image cards) to get medicine recommendations.
- Age-based dose display (adult vs child).
- Search/autocomplete for symptoms.
- Emergency contacts page with quick-call buttons.
- Seeder that populates symptoms & medicines on first run.

## Quick start (local)
1. Copy sample config:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
