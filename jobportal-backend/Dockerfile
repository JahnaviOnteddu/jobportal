# # Use an official OpenJDK 17 image as the base
# FROM openjdk:17-jdk-slim

# # Set the working directory inside the container
# WORKDIR /app

# # Copy the Spring Boot JAR file from target folder into the container
# COPY target/jobportal-0.0.1-SNAPSHOT.jar app.jar

# # Expose the port your backend runs on (change if needed)
# EXPOSE 8080

# # Run the JAR file
# ENTRYPOINT ["java", "-jar", "app.jar"]
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
