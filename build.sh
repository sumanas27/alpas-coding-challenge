#!/bin/bash

# Exit immediately if any command fails
set -e

# Define variables
APP_NAME="alpas-coding-challenge"
JAR_FILE="build/libs/${APP_NAME}.jar"
DOCKER_IMAGE="${APP_NAME}:latest"
DOCKER_CONTAINER="${APP_NAME}_container"

echo "🚀 Building Spring Boot application with Gradle..."
./gradlew clean build

echo "✅ Build completed. JAR located at: $JAR_FILE"

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker and try again."
    exit 1
fi

echo "🐳 Building Docker image..."
docker build -t $DOCKER_IMAGE .

echo "✅ Docker image '$DOCKER_IMAGE' built successfully."

# Stop and remove any running container with the same name
if [ "$(docker ps -q -f name=$DOCKER_CONTAINER)" ]; then
    echo "🛑 Stopping existing container..."
    docker stop $DOCKER_CONTAINER
fi

if [ "$(docker ps -aq -f name=$DOCKER_CONTAINER)" ]; then
    echo "🗑 Removing old container..."
    docker rm $DOCKER_CONTAINER
fi

echo "🚀 Running the application in a Docker container..."
docker run -d -p 8080:8080 --name $DOCKER_CONTAINER $DOCKER_IMAGE

echo "✅ Application is running on http://localhost:8080/swagger-ui/index.html#/"
