# Multi-stage Dockerfile for building and running the ToDoTomcat WAR on Tomcat

# ---------- Build stage ----------
FROM maven:3.9.9-eclipse-temurin-11 AS build
WORKDIR /app

# Leverage Docker layer caching
COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 mvn -B -q -e dependency:go-offline

# Copy sources and build
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -B -e package

# ---------- Runtime stage ----------
# Use Tomcat 9 with JDK 11 (compatible with Servlet 4.0 API used by the project)
FROM tomcat:9.0-jdk11-temurin

# Deploy the built WAR as ROOT.war so the app is available at /
COPY --from=build /app/target/ToDoTomcat.war /usr/local/tomcat/webapps/ROOT.war

# Expose default Tomcat HTTP port
EXPOSE 8080

# Default command (inherited): catalina.sh run
