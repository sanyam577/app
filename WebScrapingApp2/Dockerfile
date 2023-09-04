
# Use a Java 8 base image
FROM openjdk:8-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the local machine to the container
COPY target/Web_Scrapping-1.1.jar app.jar

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]

# Expose the port that your Spring Boot application listens on
EXPOSE 7777

