# Variables
APP_NAME = alpas-coding-challenge
JAR_FILE = build/libs/$(APP_NAME)-0.0.1-SNAPSHOT.jar
DOCKER_IMAGE = $(APP_NAME):latest
DOCKER_CONTAINER = $(APP_NAME)-container

# Default target (build everything)
all: clean test build docker-build run

# Clean the project (Gradle & Docker)
clean:
	@echo "🧹 Cleaning Gradle build & Docker images..."
	@./gradlew clean
	-docker stop $(DOCKER_CONTAINER) 2>/dev/null || true
	-docker rm $(DOCKER_CONTAINER) 2>/dev/null || true
	-docker rmi $(DOCKER_IMAGE) 2>/dev/null || true
	@echo "✅ Clean completed."

# Run tests
test:
	@echo "🧪 Running tests..."
	@./gradlew test || { echo "❌ Tests failed!"; exit 1; }
	@echo "✅ Tests passed."

# Build the project using Gradle
build: test
	@echo "🚀 Building the project..."
	@./gradlew build || { echo "❌ Build failed!"; exit 1; }
	@echo "✅ Build completed. JAR file located at $(JAR_FILE)"

# Build the Docker image
docker-build: build
	@echo "🐳 Building Docker image..."
	@docker build -t $(DOCKER_IMAGE) . || { echo "❌ Docker build failed!"; exit 1; }
	@echo "✅ Docker image '$(DOCKER_IMAGE)' built successfully."

# Run the application inside a Docker container
run:
	@echo "🚀 Running the Docker container..."
	-docker stop $(DOCKER_CONTAINER) 2>/dev/null || true
	-docker rm $(DOCKER_CONTAINER) 2>/dev/null || true
	@docker run -d -p 8080:8080 --name $(DOCKER_CONTAINER) $(DOCKER_IMAGE)
	@echo "✅ Application is running on http://localhost:8080/swagger-ui/index.html"

# Stop and remove Docker container
stop:
	@echo "🛑 Stopping the Docker container..."
	-docker stop $(DOCKER_CONTAINER) 2>/dev/null || true
	-docker rm $(DOCKER_CONTAINER) 2>/dev/null || true
	@echo "✅ Container stopped and removed."

# Clean Docker images
docker-clean: stop
	@echo "🗑 Removing Docker image..."
	-docker rmi $(DOCKER_IMAGE) 2>/dev/null || true
	@echo "✅ Docker image removed."

# Full clean: Gradle + Docker
full-clean: clean docker-clean
	@echo "✅ Full cleanup complete."

# Help command to show available targets
help:
	@echo "Available commands:"
	@echo "  make           - Builds everything (default target)"
	@echo "  make clean     - Cleans Gradle & Docker artifacts"
	@echo "  make test      - Runs tests"
	@echo "  make build     - Builds the project using Gradle"
	@echo "  make docker-build - Builds the Docker image"
	@echo "  make run       - Runs the Docker container"
	@echo "  make stop      - Stops and removes the container"
	@echo "  make docker-clean - Removes Docker image"
	@echo "  make full-clean - Performs a complete cleanup"
