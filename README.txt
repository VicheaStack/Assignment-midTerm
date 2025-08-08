# Lecture Registration API

This is a Spring Boot project for managing lectures, members, and registrations between them.

## Features

* Create, update, and list lectures
* Create, update, and list members
* Register members to lectures
* List students registered to a specific lecture
* List lectures a member has enrolled in

---

## API Endpoints

### Lecture APIs

| Method | URL                              | Description                        | Request Body    | Response                |
| ------ | -------------------------------- | ---------------------------------- | --------------- | ----------------------- |
| POST   | `/api/v1/lectures/add`           | Add a new lecture                  | LectureDTO JSON | Saved LectureDTO JSON   |
| PUT    | `/api/v1/lectures/{id}/edit`     | Edit existing lecture              | LectureDTO JSON | Updated LectureDTO JSON |
| GET    | `/api/v1/lectures/list`          | List all lectures                  | None            | List of LectureDTO JSON |
| GET    | `/api/v1/lectures/{id}/students` | Get students registered to lecture | None            | List of MemberDTO JSON  |

---

### Member APIs

| Method | URL                             | Description                       | Request Body   | Response                |
| ------ | ------------------------------- | --------------------------------- | -------------- | ----------------------- |
| POST   | `/api/v1/members/add`           | Add a new member                  | MemberDTO JSON | Saved MemberDTO JSON    |
| PUT    | `/api/v1/members/{id}/edit`     | Edit existing member              | MemberDTO JSON | Updated MemberDTO JSON  |
| GET    | `/api/v1/members/list`          | List all members                  | None           | List of MemberDTO JSON  |
| GET    | `/api/v1/members/{id}/lectures` | Get lectures a member enrolled in | None           | List of LectureDTO JSON |

---

### Registration APIs

| Method | URL                          | Description                    | Request Body          | Response                      |
| ------ | ---------------------------- | ------------------------------ | --------------------- | ----------------------------- |
| POST   | `/api/v1/registrations/add`  | Register a member to a lecture | MemberLectureDTO JSON | Saved MemberLectureDTO JSON   |
| GET    | `/api/v1/registrations/list` | List all registrations         | None                  | List of MemberLectureDTO JSON |
| GET    | `/api/v1/registrations/{id}` | Get registration by ID         | None                  | MemberLectureDTO JSON         |

---

## Sample JSON Payloads

### Add Lecture

```json
{
  "title": "Introduction to Spring Boot",
  "content": "Learn the basics of Spring Boot framework."
}
```

### Add Member

```json
{
  "name": "John Doe",
  "age": 25,
  "address": "123 Main St"
}
```

### Add Registration (Register member to lecture)

```json
{
  "memberId": 1,
  "lectureId": 1
}
```

---

## How to Run

1. Clone the repository
2. Configure your database connection in `application.properties` or `application.yml`
3. Build the project:

```bash
./mvnw clean install
```

4. Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

5. Use Postman or curl to test the API endpoints.

---

## Example curl commands

### Add Lecture

```bash
curl -X POST http://localhost:8080/api/v1/lectures/add \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Boot Basics","content":"Learn Spring Boot"}'
```

### List Lectures

```bash
curl http://localhost:8080/api/v1/lectures/list
```

### Add Member

```bash
curl -X POST http://localhost:8080/api/v1/members/add \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Smith","age":30,"address":"456 Oak St"}'
```

### Register Member to Lecture

```bash
curl -X POST http://localhost:8080/api/v1/registrations/add \
  -H "Content-Type: application/json" \
  -d '{"memberId":1,"lectureId":1}'
```

### List Registrations

```bash
curl http://localhost:8080/api/v1/registrations/list
```
