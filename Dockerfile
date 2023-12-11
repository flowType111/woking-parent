FROM openjdk:8-jre-alpine

WORKDIR /app
COPY . .

ENTRYPOINT ["java", "-jar", "your-app.jar"]
