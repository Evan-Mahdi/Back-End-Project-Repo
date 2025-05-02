# Use a base image with JDK
FROM openjdk:23-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/back-end-project-repo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your application will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
