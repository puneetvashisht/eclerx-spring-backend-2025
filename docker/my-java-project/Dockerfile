# Use an official OpenJDK runtime as a parent image
FROM arm64v8/openjdk:11

# Set the working directory inside the container
WORKDIR /app

# Copy the Java source file into the container
COPY HelloWorld.java /app/

# Compile the Java source file
RUN javac HelloWorld.java

# Define the command to run the compiled Java class
CMD ["java", "HelloWorld"]
