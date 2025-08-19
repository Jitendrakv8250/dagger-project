# Java Dagger + Netty Micro-Framework (Starter)

A minimal starter showing how to wire **Netty** (HTTP) with **Dagger 2** (compile-time DI),
**Jackson** (JSON), and **JUnit 5** for testing – using **Gradle (Kotlin DSL)** and **Java 21**.

## Features

- Netty HTTP server with a tiny routing layer
- Dagger 2 component+modules for DI
- Jackson `ObjectMapper` provided by DI
- Example controller (`HelloController`) returning JSON
- Health check endpoint (`/health`)
- JUnit 5 test that boots the server and makes a real HTTP call

## Project Structure

```text
java-dagger-netty-starter/
├─ build.gradle.kts
├─ settings.gradle.kts
├─ README.md
└─ src
   ├─ main
   │  └─ java/app/...
   └─ test
      └─ java/app/...
```

## Run

1. Ensure you have **Java 21** and **Gradle 8+**.
2. From the project root, run:
   ```bash
   gradle run
   ```
3. Server starts on `http://localhost:8080`.

### Sample Requests

- Health:
  ```bash
  curl -i http://localhost:8080/health
  ```
- Hello JSON (optional query `name`):
  ```bash
  curl -i "http://localhost:8080/hello?name=World"
  ```

## Test

```bash
gradle test
```

## Packaging

Create a runnable JAR:
```bash
gradle jar
```

## Notes

- Routes are exact-match for simplicity.
- Add your own controllers, bind them in `ControllerModule` with `@IntoSet`.
