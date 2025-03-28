# Use a builder image
FROM eclipse-temurin:21-jdk AS builder

# Set working directory
WORKDIR /app
COPY . /app

# Packaging the application code
RUN ./gradlew clean build
# Second stage of the build will use openjdk:21-jdk-slim image for reduced overall image size
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy only the built JAR file from the builder stage
COPY --from=builder /app/build/libs/alpas-coding-challenge-0.0.1-SNAPSHOT.jar /app.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app.jar"]
