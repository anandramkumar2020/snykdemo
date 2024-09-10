# Use Maven base image to build the project
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src

# Build the Maven project
RUN mvn clean package

# Use a smaller image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/snykdemo-0.0.1-SNAPSHOT.jar /app/snykdemo.jar

# Run the JAR file
# ENTRYPOINT ["java", "-jar", "/app/snykdemo.jar"]
