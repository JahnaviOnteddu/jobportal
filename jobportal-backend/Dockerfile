# Use Java 21 (matches your local build environment)
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy Spring Boot jar from target folder
COPY target/jobportal-0.0.1-SNAPSHOT.jar app.jar

# Expose backend port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
