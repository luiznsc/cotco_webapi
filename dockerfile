# Step 1: Start with a base image. In this case, we are using Amazon Corretto 17.
FROM amazoncorretto:17

# Step 2: Set the current working directory inside the container.
WORKDIR /app

# Step 3: Copy the .jar file into the container.
COPY target/*.jar app.jar

# Step 4: Expose the port that the Spring Boot app is running on.
EXPOSE 8080

# Step 5: Define the command to start the application.
ENTRYPOINT ["java", "-jar", "app.jar"]