# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-alpine AS build

# Set working directory
WORKDIR /app

# Install necessary packages (like bash if needed)
RUN apk add --no-cache bash

# Copy Gradle wrapper and related files
COPY gradlew .
COPY gradle gradle

# Grant execution permissions to gradlew
RUN chmod +x gradlew

# Copy dependency configuration files
COPY build.gradle settings.gradle ./
COPY src src

# Download dependencies (this layer will be cached unless build.gradle or settings.gradle changes)
RUN ./gradlew dependencies --no-daemon

# Build the application
RUN ./gradlew build --no-daemon

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Define the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
