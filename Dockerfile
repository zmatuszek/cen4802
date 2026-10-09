# Runtime-only Dockerfile for running the ToDoTomcat WAR on Tomcat
# The WAR should be built outside (e.g., by CI using Maven) before building this image.

# Use Tomcat 9 with JDK 11 (compatible with Servlet 4.0 API used by the project)
FROM tomcat:9.0-jdk11-temurin

# Path to the prebuilt WAR within the build context
ARG WAR_FILE=target/ToDoTomcat.war

# Deploy the WAR as ROOT.war so the app is available at /
COPY ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war

# Expose default Tomcat HTTP port
EXPOSE 8080

# Default command (inherited): catalina.sh run
