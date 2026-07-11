# Jenkins Master-Agent Mini Project

This project demonstrates distributed Jenkins execution using:

- Jenkins controller/master
- Agent labeled `worker-1`
- Agent labeled `worker-2`
- Java 17+
- Maven
- Git

## Execution flow

1. Jenkins controller schedules the Pipeline.
2. `worker-1` checks out the repository.
3. `worker-1` compiles the Java code and runs JUnit tests.
4. `worker-1` creates a JAR file.
5. Jenkins temporarily stashes the small JAR file.
6. `worker-2` unstashes the JAR file.
7. `worker-2` verifies and executes the application.
8. Jenkins archives the JAR as a build artifact.

## Required agent labels

- `worker-1`
- `worker-2`

The labels in Jenkins must exactly match the labels in the Jenkinsfile.

## Expected application output

Hello from Jenkins Master-Agent Mini Project!
Application executed successfully on a Jenkins agent.
2 + 3 = 5
