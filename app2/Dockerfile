# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file into the container
COPY target/app2-0.0.1-SNAPSHOT.jar app.jar

# Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]