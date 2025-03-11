# First stage of the build will use eclipse-temurin:21-jdk parent image
FROM eclipse-temurin:21-jdk AS builder

# Copy local code to the container image.
WORKDIR /app
COPY . /app

# Packaging the application code
RUN ./gradlew clean build

# Second stage of the build will use openjdk:21-jdk-slim image for reduced overall image size
FROM openjdk:21-jdk-slim

# Copying only the artifacts from the first stage and discarding the rest
COPY --from=builder /app/build/libs/alpas-coding-challenge-0.0.1-SNAPSHOT.jar /app.jar

# Expose the port the application runs on
EXPOSE 8080

# Setting the startup command to execute the jar
CMD ["java","-jar","/app.jar"]