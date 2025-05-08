FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /target/product-ranking-service-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
