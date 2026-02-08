# Vaadin Login Application

A Spring Boot application with Vaadin framework featuring a login page and home page navigation.

## Features

- Login page with username and password authentication
- Secure navigation to home page after successful login
- **Top navigation panel** with:
   - Application name
   - Current username display
   - Logout button
   - Menu toggle (hamburger icon)
- **Left side vertical menu panel** with:
   - Home
   - Dashboard
   - Users
   - Settings
   - Reports
   - Messages
- Responsive layout using Vaadin AppLayout
- Spring Security integration
- Icon-based navigation menu

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Default Users

The application comes with two pre-configured users:

1. **Regular User**
   - Username: `user`
   - Password: `password`
   - Role: USER

2. **Admin User**
   - Username: `admin`
   - Password: `admin`
   - Roles: ADMIN, USER

## How to Run

1. Navigate to the project directory:
   ```bash
   cd vaadin-login-app
   ```

2. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

   Or build and run the JAR:
   ```bash
   mvn clean package
   java -jar target/vaadin-login-app-1.0.0.jar
   ```

3. Open your browser and navigate to:
   ```
   http://localhost:8080
   ```

4. You will be redirected to the login page. Use one of the default credentials above to login.

## Project Structure

```
vaadin-login-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/vaadinapp/
│       │       ├── VaadinLoginApplication.java
│       │       ├── security/
│       │       │   ├── SecurityConfiguration.java
│       │       │   └── SecurityService.java
│       │       └── views/
│       │           ├── MainLayout.java
│       │           ├── LoginView.java
│       │           ├── HomeView.java
│       │           ├── DashboardView.java
│       │           ├── UsersView.java
│       │           ├── SettingsView.java
│       │           ├── ReportsView.java
│       │           └── MessagesView.java
│       └── resources/
│           └── application.properties
├── pom.xml
└── .gitignore
```

## Key Components

### MainLayout.java
- AppLayout with top navigation bar and side drawer
- Top panel contains:
   - Hamburger menu toggle button
   - Application name
   - Username display
   - Logout button
- Side drawer contains vertical navigation menu with icons
- Provides consistent layout for all authenticated views

### SecurityConfiguration.java
Configures Spring Security with:
- In-memory user authentication
- BCrypt password encoding
- Login view routing
- Protected routes

### LoginView.java
- Vaadin login form
- Username and password fields
- Error handling for failed login attempts
- Redirects to home page on success

### HomeView.java
- Welcome page with user information
- Uses MainLayout for consistent navigation
- Protected route (requires authentication)

### Other Views
- **DashboardView**: Analytics and statistics
- **UsersView**: User management
- **SettingsView**: Application settings
- **ReportsView**: Business reports
- **MessagesView**: Messages and notifications

### SecurityService.java
- Provides authentication context
- Handles logout functionality

## Technologies Used

- Spring Boot 3.2.0
- Vaadin 24.3.0
- Spring Security
- Maven

## Customization

### Adding More Users

Edit `SecurityConfiguration.java` and add users in the `userDetailsService` method:

```java
UserDetails newUser = User.builder()
        .username("newuser")
        .password(passwordEncoder.encode("newpassword"))
        .roles("USER")
        .build();
```

### Changing the Port

Edit `application.properties`:
```properties
server.port=9090
```

### Styling

Vaadin uses Lumo theme by default. You can customize the look and feel by modifying the components or adding custom CSS.

## License

This project is provided as-is for educational purposes.