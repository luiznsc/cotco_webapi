FROM openjdk:17-jdk-slim

COPY ./demo.jar /app.jar

CMD ["java", "-jar", "/app.jar"]