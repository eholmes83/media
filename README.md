# Social Media Application

A Spring Boot RESTful API for managing social media entities including users, groups, posts, and profiles with JPA/Hibernate ORM and optimistic locking.

## 🚀 Quick Start

```bash
# Build
./mvnw clean install

# Run
./mvnw spring-boot:run

# Test endpoints
curl http://localhost:8080/api/v1/social/users
```

---

## 📋 Project Overview

Spring Boot 4.0.2 application with Java 21 providing comprehensive REST endpoints for social user management with proper transaction handling, cascading operations, and optimistic locking.

**Current Version**: `0.1.0` (Released)  
**Last Updated**: February 23, 2026  
**Status**: Active Development

---

## 🛠 Technology Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 4.0.2 | Application framework |
| Java | 21 | Programming language |
| Maven | 3.9.12 | Build tool |
| H2 Database | Latest | In-memory database |
| JPA/Hibernate | Latest | ORM framework |
| Lombok | Latest | Boilerplate reduction |
| Spring Data JPA | Latest | Data access layer |
| Spring Web MVC | Latest | REST API framework |
| Spring DevTools | Latest | Development tools |

---

## 📁 Project Structure

```
src/main/java/com/echapps/social/media/
├── controllers/
│   └── SocialController.java              # REST API endpoints
├── models/
│   ├── SocialUser.java                    # Main user entity with cascade ops
│   ├── SocialGroup.java                   # Group entity
│   ├── Post.java                          # Post entity
│   └── SocialProfile.java                 # User profile entity
├── repositories/
│   ├── SocialUserRepository.java          # User data access
│   ├── SocialGroupRepository.java         # Group data access
│   ├── PostRepository.java                # Post data access
│   └── SocialProfileRepository.java       # Profile data access
├── services/
│   └── SocialService.java                 # Business logic with delete support
├── DataInit.java                          # Initial data population
└── MediaApplication.java                  # Application entry point
```

---

## 📊 Entity Relationships

```
┌─────────────────────────────────────────────────────────┐
│                    SocialUser (Main Entity)             │
├─────────────────────────────────────────────────────────┤
│ • id (PK)                                               │
│ • socialProfile (1:1) ──→ SocialProfile                │
│ • posts (1:M) ──→ List<Post>                           │
│ • groups (M:M) ──→ Set<SocialGroup>                    │
│                                                         │
│ Cascade Types:                                          │
│ • socialProfile: {REMOVE, PERSIST, MERGE}             │
│ • posts: {PERSIST, MERGE}                             │
│ • groups: EAGER fetch strategy                         │
└─────────────────────────────────────────────────────────┘
       │                 │                    │
       ▼                 ▼                    ▼
  SocialProfile      Post (1:M)      SocialGroup (M:M)
  (1:1, owning)   (backref: user)    (mapped by: groups)
```

---

## 🌐 REST API Endpoints

### Users Resource

| Method | Endpoint | Status | Description |
|--------|----------|--------|-------------|
| GET | `/api/v1/social/users` | ✅ 200 | Get all users |
| POST | `/api/v1/social/users` | ✅ 201 | Create new user |
| DELETE | `/api/v1/social/users/{userId}` | ✅ 200 | Delete user by ID |

### API Examples

#### Get All Users
```bash
curl -X GET http://localhost:8080/api/v1/social/users \
  -H "Content-Type: application/json"
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "socialProfile": null,
    "posts": [],
    "groups": []
  }
]
```

#### Create User
```bash
curl -X POST http://localhost:8080/api/v1/social/users \
  -H "Content-Type: application/json" \
  -d '{
    "posts": [],
    "groups": []
  }'
```

**Response (201 Created):**
```json
{
  "id": 1,
  "socialProfile": null,
  "posts": [],
  "groups": []
}
```

#### Delete User
```bash
curl -X DELETE http://localhost:8080/api/v1/social/users/1 \
  -H "Content-Type: application/json"
```

**Response (200 OK):**
```
Deleted 1 successfully
```

**Error Response (404 Not Found):**
```
{
  "error": "User not found"
}
```

---

## 💾 Database

### Configuration
- **Type**: H2 (In-memory, embedded)
- **URL**: `jdbc:h2:mem:testdb`
- **Console**: http://localhost:8080/h2-console
- **Default Credentials**: username: `sa`, password: (empty)

### Database Tables

| Table | Purpose | Key Fields |
|-------|---------|-----------|
| `social_user` | Users | id (PK) |
| `social_group` | Groups | id (PK) |
| `post` | User posts | id (PK), user_id (FK) |
| `social_profile` | User profiles | id (PK), social_user (FK) |
| `user_group` | Many-to-many mapping | user_id (FK), group_id (FK) |

### Database Access
```bash
# H2 Console URL: http://localhost:8080/h2-console
# Query example:
SELECT * FROM social_user;
SELECT * FROM post WHERE user_id = 1;
SELECT * FROM user_group WHERE user_id = 1;
```

---

## ✨ Key Features

✅ **RESTful API** - Full CRUD operations (GET, POST, DELETE)  
✅ **JPA/Hibernate ORM** - Object-relational mapping for all entities  
✅ **Cascade Operations** - Automatic persistence and deletion of related entities  
✅ **Relationship Management** - Proper one-to-many and many-to-many handling  
✅ **H2 Database** - Embedded database for development and testing  
✅ **Spring Data Repositories** - Repository pattern for data access  
✅ **Lombok** - Reduces boilerplate with annotations  
✅ **Spring DevTools** - Hot reload during development  
✅ **Transactional Safety** - Proper cascade types for data integrity  
✅ **EAGER Fetching** - ManyToMany relationships loaded immediately  

---

## 📝 Recent Changes (Version 0.1.0)

**Released**: February 23, 2026

### New Features ✅

1. **DELETE User Endpoint**
   - **File**: `SocialController.java`
   - **Method**: `deleteUser(@PathVariable Long userId)`
   - **Endpoint**: `DELETE /api/v1/social/users/{userId}`
   - **Status**: 200 OK
   - **Returns**: Confirmation message "Deleted {userId} successfully"
   - **Why**: Complete CRUD operations for user management
   - **Impact**: Users can now delete users from the system

2. **Enhanced User Save Logic**
   - **File**: `SocialService.java`
   - **Method**: `saveUser(SocialUser socialUser)`
   - **Enhancement**: Added automatic profile association logic
   - **Why**: Ensures bidirectional relationship consistency
   - **Impact**: Profiles are automatically linked when saving users

3. **Improved Cascade Configuration**
   - **File**: `SocialUser.java`
   - **Changes**:
     - socialProfile cascade: `{REMOVE, PERSIST, MERGE}`
     - posts cascade: `{PERSIST, MERGE}`
     - groups fetch: `EAGER`
   - **Why**: Prevent orphaned records and ensure data integrity
   - **Impact**: Deleted users automatically remove related data

### Bug Fixes ✅

- ✅ Optimistic locking resolved (from v0.0.1)
- ✅ Bidirectional relationship consistency improved
- ✅ Cascade operations properly configured

### Refactoring ✅

- ✅ Service layer enhanced with profile association logic
- ✅ Entity cascade types properly configured
- ✅ Eager fetching strategy applied to ManyToMany relationships

### Modified Files

| File | Changes |
|------|---------|
| `SocialController.java` | Added DELETE endpoint for users |
| `SocialService.java` | Added deleteUser() method, enhanced saveUser() |
| `SocialUser.java` | Improved cascade configuration |

---

## 🏗 Build & Run

### Prerequisites
- Java 21+
- Maven 3.9.12+
- Git (optional)

### Build
```bash
# Clean build
./mvnw clean install

# Build without running tests
./mvnw clean package -DskipTests

# Run with specific profile
./mvnw clean install -Pprod
```

### Run
```bash
# Development mode
./mvnw spring-boot:run

# Or using Java directly
java -jar target/media-0.0.1-SNAPSHOT.jar

# With custom port
java -Dserver.port=8081 -jar target/media-0.0.1-SNAPSHOT.jar
```

### Test
```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=MediaApplicationTests

# Run with coverage
./mvnw test jacoco:report
```

---

## ⚙️ Configuration

### Application Properties

```properties
# Application name
spring.application.name=media

# H2 Console (development only)
spring.h2.console.enabled=true

# Database URL (in-memory)
spring.datasource.url=jdbc:h2:mem:testdb

# JPA/Hibernate Logging
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Profile-Specific Configurations

#### Development (application-dev.properties)
```properties
spring.h2.console.enabled=true
spring.jpa.show-sql=true
```

#### Production (application-prod.properties)
```properties
spring.h2.console.enabled=false
spring.jpa.show-sql=false
```

---

## 🔍 Troubleshooting

### Issue: User Not Found
**Error**: `RuntimeException: User not found`

**Solution**: 
- Verify user ID exists in database
- Check H2 console to see available users
- Use GET /api/v1/social/users to list all users

**Example**:
```bash
# First get all users
curl http://localhost:8080/api/v1/social/users

# Then use a valid ID
curl -X DELETE http://localhost:8080/api/v1/social/users/1
```

### Issue: H2 Console Not Loading
**Problem**: 404 error accessing http://localhost:8080/h2-console

**Solution**:
1. Verify `spring.h2.console.enabled=true` in application.properties
2. Restart the application
3. Ensure you're using correct URL path
4. Check that Spring Boot is running on port 8080

### Issue: Cascade Delete Not Working
**Problem**: Related data not deleted when deleting user

**Solution**:
- Check cascade types in entity annotations
- Current SocialUser has: `cascade = {REMOVE, PERSIST, MERGE}`
- Verify @OneToOne and @OneToMany annotations include REMOVE
- Restart application to apply changes

### Issue: OptimisticLockException
**Problem**: "Row was already updated or deleted by another transaction"

**Reference**: See `OPTIMISTIC_LOCK_ERROR_ANALYSIS.md` for detailed solutions

---

## 📈 Version History

| Version | Date | Type | Changes |
|---------|------|------|---------|
| 0.1.0 | Feb 23, 2026 | Minor Release | DELETE endpoint, enhanced cascade, improved service logic |
| 0.0.1 | Feb 23, 2026 | Initial | OptimisticLockException fixes, entity setup, initial endpoints |

---

## 📚 API Documentation

### Request/Response Formats

All endpoints accept and return **JSON** format.

#### User Object
```json
{
  "id": 1,
  "socialProfile": {
    "id": 1,
    "description": "User description"
  },
  "posts": [
    {
      "id": 1,
      "socialUser": { /* user reference */ }
    }
  ],
  "groups": [
    {
      "id": 1,
      "name": "Group name"
    }
  ]
}
```

#### Error Response
```json
{
  "error": "Error message",
  "timestamp": "2026-02-23T10:30:00Z",
  "status": 404
}
```

---

## 🔒 Security Considerations

⚠️ **Development Only**: This application uses H2 in-memory database suitable for development.

**For Production**:
- [ ] Replace H2 with persistent database (PostgreSQL, MySQL)
- [ ] Add Spring Security for authentication/authorization
- [ ] Implement rate limiting
- [ ] Add CORS configuration
- [ ] Validate input parameters
- [ ] Add request logging
- [ ] Implement error handling with proper status codes

---

## 🚀 Future Enhancements

- [ ] Add authentication and authorization (Spring Security)
- [ ] Implement pagination and filtering for list endpoints
- [ ] Add comprehensive error handling and validation
- [ ] Implement soft delete for entities
- [ ] Add auditing (created_at, updated_at fields)
- [ ] Integrate with JWT for API security
- [ ] Add request/response DTO layer
- [ ] Implement full CRUD operations for all entities (Groups, Posts, Profiles)
- [ ] Add OpenAPI/Swagger documentation
- [ ] Add integration tests with testcontainers
- [ ] Migrate to PostgreSQL for production
- [ ] Add caching (Redis)
- [ ] Implement GraphQL API
- [ ] Add message queue support (RabbitMQ, Kafka)

---

## 📚 References & Documentation

### Official Documentation
- [Spring Boot 4.0.2 Documentation](https://docs.spring.io/spring-boot/4.0.2)
- [Spring Data JPA Reference](https://docs.spring.io/spring-boot/4.0.2/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [Hibernate ORM Documentation](https://hibernate.org/orm/documentation/)
- [H2 Database Documentation](http://www.h2database.com)
- [Lombok Features](https://projectlombok.org/features/all)

### Project Documentation
- [OPTIMISTIC_LOCK_ERROR_ANALYSIS.md](./OPTIMISTIC_LOCK_ERROR_ANALYSIS.md) - Concurrency and locking details
- [FIX_SUMMARY.md](./FIX_SUMMARY.md) - Summary of recent fixes
- [MASTER_SCAN_PROMPT.md](./MASTER_SCAN_PROMPT.md) - README update prompts
- [HELP.md](./HELP.md) - Spring Boot reference

---

## 💻 Development Commands

### Useful Maven Commands
```bash
./mvnw clean                    # Clean build directory
./mvnw compile                  # Compile source code
./mvnw test                     # Run all tests
./mvnw install                  # Build and install to local repository
./mvnw spring-boot:run         # Run application
./mvnw dependency:tree         # Show dependency tree
./mvnw dependency:purge-local-repository  # Clear cache
```

### Git Workflow
```bash
git add .
git commit -m "docs: update README with DELETE endpoint - version 0.1.0"
git push origin main
git tag -a v0.1.0 -m "Release version 0.1.0"
git push origin --tags
```

---

## 📞 Support & Contributing

For issues or questions:
1. Check the [Troubleshooting](#troubleshooting) section
2. Review relevant documentation files
3. Check application logs: `./mvnw spring-boot:run`
4. Verify H2 console: http://localhost:8080/h2-console

---

## Using Prompts to Update README

### Quick Prompt Template

Copy and use this prompt with an AI assistant to scan for changes and update the README:

```
Scan my Spring Boot project for recent changes and update the README.md file:

1. Check these directories for modifications:
   - src/main/java/com/echapps/social/media/models/ (Entity changes)
   - src/main/java/com/echapps/social/media/controllers/ (API changes)
   - src/main/java/com/echapps/social/media/services/ (Business logic changes)
   - pom.xml (Dependency changes)
   - application.properties (Configuration changes)

2. For each change detected, document:
   - Which file was modified
   - What specifically changed
   - Why it was changed
   - Impact on the application
   - Which components are affected

3. Update README.md with:
   - Increment the version number (semantic versioning)
   - Add a "Recent Changes & Updates" section with all modifications
   - List all "Modified Files" with brief descriptions
   - Update "API Endpoints" table if any endpoints were added/modified
   - Update "Entity Relationships" if entity structure changed
   - Update entity descriptions if they have new fields
   - Update the "Technology Stack" if dependencies changed
   - Add new features to the "Key Features" section
   - Update "Last Updated" timestamp to today
   - Update "Troubleshooting" if new issues discovered

4. Format updates as:
   - Use ✅ checkmarks for completed features
   - Use tables for API endpoints and technology stack
   - Use code blocks for configuration examples
   - Include curl command examples for new endpoints
   - Use proper Markdown formatting
   - Maintain consistency with existing style
```

---

## 📖 How to Use This Prompt

1. **Copy** the Quick Prompt Template above
2. **Paste** into GitHub Copilot
3. **Review** suggested README changes
4. **Accept** and commit with message:
   ```bash
   git commit -am "docs: update README with [feature name] - version X.X.X"
   ```

---

## 🔄 Update Workflow

**After Each Feature:**
```
1. Code and test feature
2. Run Quick Prompt Template (copy & paste into Copilot)
3. Review README changes
4. Commit with clear message
```

**Before Release:**
```
1. Run complete scan with Master Prompt
2. Increment version number appropriately
3. Verify all examples work
4. Fix any broken links
5. Tag and push release
```

---

## 📊 Semantic Versioning

Current: **0.1.0**

Format: **MAJOR.MINOR.PATCH**

| Change Type | Update | Example |
|-------------|--------|---------|
| Breaking changes | MAJOR | 0.1.0 → 1.0.0 |
| New features | MINOR | 0.1.0 → 0.2.0 |
| Bug fixes | PATCH | 0.1.0 → 0.1.1 |

---

## 📄 Additional Documentation Files

- **MASTER_SCAN_PROMPT.md** - Complete prompts for scanning and updating README
- **OPTIMISTIC_LOCK_ERROR_ANALYSIS.md** - Error analysis and solutions
- **FIX_SUMMARY.md** - Summary of recent fixes
- **SETUP_SUMMARY.md** - Project setup guide
- **PROMPTS_QUICK_LIST.txt** - Quick reference for all prompts

---

**Generated with Master Scan Prompt v1.0**
