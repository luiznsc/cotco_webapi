# Use a imagem base do OpenJDK 11
FROM openjdk:11-jdk-slim

COPY ./demo.jar /app.jar

CMD ["java", "-jar", "/app.jar"]