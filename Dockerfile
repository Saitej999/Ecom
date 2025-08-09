# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the Spring Boot project
COPY sb-ecom ./

# Make Maven wrapper executable  
RUN chmod +x ./mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the application - let Spring Boot handle port from environment
CMD ["java", "-jar", "target/sb-ecom-0.0.1-SNAPSHOT.jar"]
