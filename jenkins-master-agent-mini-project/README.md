# Jenkins Master-Agent Setup on AWS using Terraform

## About the Project

This project demonstrates how to set up a Jenkins Master-Agent architecture on AWS using Terraform.

The goal was to automate the complete environment from infrastructure provisioning to running a multi-agent Jenkins pipeline. Instead of running everything on the Jenkins master, the build is distributed across two worker nodes to simulate a real production environment.

The project uses a simple Java Maven application to demonstrate the pipeline.

---

## What this project does

* Creates **1 Jenkins Master** and **2 Jenkins Agent EC2 instances** using Terraform
* Installs Jenkins, Java 21, Maven and Git automatically
* Connects both agents to Jenkins over SSH
* Checks out source code from GitHub
* Builds and tests the application on Worker-1
* Transfers the generated JAR file to Worker-2 using `stash` and `unstash`
* Runs the application on Worker-2
* Archives the build artifact in Jenkins

---

## Architecture

```text
                    +----------------------+
                    |      GitHub Repo     |
                    +----------+-----------+
                               |
                               |
                               ▼
                    +----------------------+
                    |    Jenkins Master    |
                    |----------------------|
                    | Pipeline Controller  |
                    | Build Scheduler      |
                    +----------+-----------+
                               |
              -----------------------------------------
              |                                       |
              ▼                                       ▼

      +---------------+                      +---------------+
      |   Worker-1    |                      |   Worker-2    |
      |---------------|                      |---------------|
      | Checkout Code |                      | Receive JAR   |
      | Maven Build   |                      | Verify JAR    |
      | Unit Tests    |                      | Run Program   |
      | Package JAR   |                      | Archive JAR   |
      +-------+-------+                      +---------------+
              |
              |
      stash Artifact
              |
              ▼
      unstash Artifact
```

---

## Infrastructure

Terraform provisions the following resources:

* Jenkins Master EC2
* Worker-1 EC2
* Worker-2 EC2
* Security Groups
* SSH Key Pair
* User Data scripts for software installation

Each server is automatically configured with:

* Java 21
* Maven
* Git

The Jenkins Master also installs and starts Jenkins automatically.

---

## Repository Structure

```text
terraform/
    main.tf
    variables.tf
    outputs.tf
    provider.tf
    userdata/

jenkins-master-agent-mini-project/
    Jenkinsfile
    pom.xml
    src/

README.md
```

---

## Pipeline Flow

### Worker-1

* Clean workspace
* Checkout source code
* Display Java, Maven and Git versions
* Run Maven build
* Execute unit tests
* Create JAR file
* Stash the generated artifact

### Worker-2

* Receive artifact
* Verify the JAR
* Execute the application
* Archive the artifact

---

## Technologies Used

* AWS EC2
* Terraform
* Jenkins
* Java 21
* Maven
* JUnit 5
* Git
* GitHub

---

## Sample Output

```
Checking out source code...

Java 21

Maven 3.x

Running Unit Tests...

Tests run: 2
Failures: 0

BUILD SUCCESS

Generated JAR

Moving artifact to Worker-2

Running application...

Hello from Jenkins Master-Agent Mini Project!

Application executed successfully on a Jenkins agent.

2 + 3 = 5

Finished: SUCCESS
```

---

## Challenges I Faced

While building this project, I ran into a few issues that helped me understand Jenkins better.

* SSH authentication between the master and agents
* Java version mismatch on the agents
* Maven using the wrong JDK
* Jenkins Durable Task plugin issues
* Incorrect JVM options while launching agents
* JUnit 4 vs JUnit 5 dependency mismatch
* Workspace and artifact transfer between agents

Troubleshooting these problems gave me a much better understanding of how Jenkins works internally.

---

## Future Improvements

Some features I'd like to add in the future are:

* SonarQube code analysis
* Trivy security scanning
* Docker image creation
* Push images to Docker Hub
* Kubernetes deployment
* Shared Jenkins Libraries
* Slack notifications
* Email notifications


