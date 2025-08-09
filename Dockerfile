# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the Spring Boot project
COPY sb-ecom ./

# Copy startup script
COPY start.sh ./
RUN chmod +x start.sh

# Make Maven wrapper executable  
RUN chmod +x ./mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the application
CMD ["./start.sh"]
