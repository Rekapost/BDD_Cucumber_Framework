# BDD-Cucumber Framework

## Overview
This project is a BDD Framework designed to facilitate automated testing for Java applications. It includes configurations and utilities to help you write and run tests effectively.

## Features
- Easy integration with Java projects
- Simple configuration for BDD
- Support for HTML reports
- Docker integration for containerized testing

## Getting Started
### Prerequisites
- Java JDK 8 or higher
- Maven
- Docker (optional)

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/Rekapost/BDD_Cucumber_Framework.git
    ```
2. Install dependencies:
    ```sh
    mvn clean install
    ```
    ### Running Tests
To run the tests, use the following command:
```sh
mvn test
```

## 1. Running the Maven Project
Navigate to the project directory where `pom.xml` is located and run:
```sh
mvn clean
mvn compile
mvn test        # or `mvn install`
mvn clean test  # or `mvn clean install`
```

## 2. Running the Maven Project Using Chaintest Service
Navigate to the Docker folder and run:
```sh
docker-compose -f docker-compose-h2.yml up
```
Chaintest report available at: (http://localhost:8081/)

![image](https://github.com/user-attachments/assets/6ed53759-7f21-41bb-aa6f-9dcaa03db533)

### Verify Port Availability
Before starting your service, ensure the port (e.g., `8081`) is free and not being used by another application. If the service fails to start, it could be because another application is already using the port.

#### On Windows:
1. Open **Command Prompt** and check if port `8081` is in use:
   ```sh
   netstat -ano | findstr :8081
   ```
   If another process is using the port, an output like this appears:
   ```
   TCP    127.0.0.1:8081    0.0.0.0:0    LISTENING    [PID]
   ```
2. Kill the process using the port:
   ```sh
   taskkill /PID [PID] /F
   ```

### Reports are saved at:
- `/target/chaintest/Index.html`
- `/target/chaintest/Email.html`
  ![image](https://github.com/user-attachments/assets/5707c8d0-b97b-431b-b30f-d1daba9cefbb)

To check logs for RemoteWebDriver http://localhost:5555/wd/hub :
driver.set(new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"),chromeOptions));
