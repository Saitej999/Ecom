#!/bin/bash

# Set default port if PORT is not set
export PORT=${PORT:-8080}

# Start the Spring Boot application
java -jar target/sb-ecom-0.0.1-SNAPSHOT.jar
