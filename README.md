**URL Shortener Project**

Project Overview 
 
This is a URL Shortener application built using Spring Boot, Thymeleaf, and MySQL.  
It allows:  
 Anonymous users to shorten URLs.  
 Registered users to create custom short URLs.  
 User authentication via Login/Signup.  

 ## **Features**  
- Anonymous URL Shortening**: Anyone can shorten a long URL.  
- Custom Short URLs: Registered users can create custom short links.  
- User Authentication: Login/Signup system with session handling.  
- Database Storage: URLs and user data stored in MySQL.  
- CI/CD Pipeline: Automated build & deployment using GitHub Actions and Docker.  

## **Tech Stack**  
| Component           | Technology                       |
|---------------------|----------------------------------|
| Backend             | Java 17, Spring Boot 3.4.2       |
| Frontend            | Thymeleaf, HTML, CSS, Bootstrap  |
| Database            | MySQL                            |
| Build Tool          | Maven                            |
| Version Control     | Git & GitHub                     |
| CI/CD Pipeline      | GitHub Actions, Docker Hub       |
| Deployment (Future) | AWS Fargate / EC2                |

## **Project Structure**  
```
/src/main/java/com/urlshortener/
│── controller/    # Handles HTTP requests
│── model/         # Defines database entities
│── repository/    # Handles database queries
│── service/       # Business logic (if needed in future)
│── config/        # Security & App Configuration (if needed in future)
│── resources/templates/  # Thymeleaf HTML files
│── resources/static/     # CSS & JS files
```
## **Setup & Installation**  

### **Prerequisites**  
Java 17+ installed  
MySQL installed & running  
Maven installed  

### **Steps to Run Locally**  
1️. Clone the repository  
```bash
git clone https://github.com/iram2002/url-shortener.git
cd url-shortener
```
2️. Configure MySQL in `application.properties`  
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener_db
spring.datasource.username=root
spring.datasource.password=yourpassword
```
3️. Build & Run  
```bash
mvn clean package
mvn spring-boot:run
```
4️. Open in browser: `http://localhost:8080`

## **API Endpoints**  
| Method | Endpoint            | Description        |
|--------|---------------------|-------------       |
| GET    | `/`                 | Home Page          |
| GET    | `/login`            | Login Page         |
| GET    | `/signup`           | Signup Page        |
| POST   | `/signup`           | Create new user    |
| POST   | `/login`            | Authenticate user  |
| GET    | `/auth/check-login` | Check user session |
| POST   | `/shorten`          | Create short URL   |

## **Future Enhancements**  
- Hashing passwords (Currently stored as plain text)  
- Implementing JWT authentication  
- Deploying to AWS

## **📝 Authors**  
- **Iram Naaz** - Developer  
