# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim AS run
WORKDIR /app
COPY --from=build /app/target/myapp.jar .
EXPOSE 8080
CMD ["java", "-jar", "myapp.jar"]