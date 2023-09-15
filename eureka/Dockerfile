FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory

# Copy the project files
COPY pom.xml pom.xml
COPY src src

# Build the application
RUN mvn clean install -DskipTests

FROM openjdk:17-alpine

# Set the working directory

# Copy the built JAR file from the build stage
COPY --from=build target .

# Expose the port your application listens on (adjust the port number as needed)

# Command to run the application
ENTRYPOINT ["java", "-jar", "/eureka-0.0.1-SNAPSHOT.jar"]