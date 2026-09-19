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
