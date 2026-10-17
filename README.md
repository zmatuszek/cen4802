# ToDoTomcat

[![CI](https://github.com/OWNER/REPO/actions/workflows/ci.yml/badge.svg)](https://github.com/OWNER/REPO/actions/workflows/ci.yml)

Simple Java Servlet (WAR) project with a minimal Continuous Integration (CI) pipeline powered by GitHub Actions.

What the CI workflow does:
- Triggers on every push and pull request
- Builds with Maven and runs JUnit 5 tests
- Executes on a Java version matrix (11, 17, 21) to demonstrate compatibility
- Uploads Surefire test reports as artifacts on every run
- Uploads the built WAR (ToDoTomcat.war) as an artifact on successful builds

Where to find results in GitHub:
- Checks tab of a pull request: view job logs per Java version
- Artifacts: "surefire-reports-java-<version>" and "ToDoTomcat-war-java-<version>"

Local build
- Requires Java 11+ and Maven
- Run: mvn -B verify

Containerization (Docker)
- Build the image (from project root):
  - docker build -t todo-tomcat:latest .
- Run the container, mapping port 8080:
  - docker run --rm -p 8080:8080 --name todo-tomcat todo-tomcat:latest
- Open the app in your browser:
  - http://localhost:8080/
- Stop the container:
  - Ctrl+C (if running in foreground) or docker stop todo-tomcat

Using Docker Compose
- Build and run:
  - docker compose up --build
- Tear down:
  - docker compose down

Notes
- The Dockerfile is a runtime-only image that expects the WAR to be built first (e.g., via `mvn -B package`). It then copies the WAR into a Tomcat 9 (JDK 11) runtime image.
- The application is deployed as ROOT.war, so it is served at the root context path (/).
- Make sure Docker Desktop (Windows/macOS) or Docker Engine (Linux) is installed and running before executing the above commands.

Kubernetes on Docker Desktop (local)
- Prereqs:
  - Docker Desktop with Kubernetes enabled (Settings → Kubernetes → Enable Kubernetes)
  - kubectl available (Docker Desktop provides it). Verify: kubectl version --client
- Build WAR and Docker image locally:
  - mvn -B -DskipTests package
  - docker build -t todo-tomcat:latest .
- Apply the manifests to the local cluster (Windows PowerShell):
  - kubectl config use-context docker-desktop
  - kubectl apply -f k8s\todo-tomcat.yaml
  - kubectl -n todo rollout status deploy/todo-tomcat
- Access the app (choose one):
  - Port-forward: kubectl -n todo port-forward svc/todo-tomcat 8080:80 → http://localhost:8080/
  - Or change Service type to NodePort in k8s\todo-tomcat.yaml and access http://localhost:30080/
- Re-deploy after code changes:
  - mvn -B -DskipTests package
  - docker build -t todo-tomcat:latest .
  - kubectl -n todo rollout restart deploy/todo-tomcat
