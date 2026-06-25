# Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Dmaven.test.skip=true
# Run Stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/catanBoardGenerator-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# Ensures Spring Boot binds to the dynamic port Render provides
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]