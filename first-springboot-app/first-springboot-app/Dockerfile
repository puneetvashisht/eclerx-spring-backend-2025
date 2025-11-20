# Use a base image with a Java Development Kit (JDK)
FROM arm64v8/openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
# Replace 'target/my-spring-boot-app.jar' with the actual path and name of your JAR file
COPY target/first-springboot-app-0.0.1-SNAPSHOT.jar /app/my-spring-boot-app.jar

# Expose the port your application runs on (e.g., 8080 for a typical Spring Boot web app)
EXPOSE 9090

# Define the command to run your Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "/app/my-spring-boot-app.jar"]
