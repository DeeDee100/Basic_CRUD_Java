FROM openjdk:11-jre-slim
WORKDIR /app
COPY . .
EXPOSE 8080
ENTRYPOINT ["java","-jar","docker-spring-boot-postgres-1.0.0.jar"]