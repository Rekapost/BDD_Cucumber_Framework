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

Code:
```
driver.set(new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"),chromeOptions));
```

1. Download Selenium Server
```
wget https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.27.0/selenium-server-4.27.0.jar
```
This downloads the Selenium Server JAR file. You'll need this to start a Selenium Hub if you are not using Docker for running the Hub.
3. Start the Selenium Hub
```
java -jar selenium-server-4.27.0.jar hub
```
This command starts the Selenium Hub that can accept requests from clients (like your RemoteWebDriver) and distribute them to available WebDriver nodes (e.g., Chrome or Firefox).
4. Pull the Selenium Standalone Chrome Docker Image
```
docker pull selenium/standalone-chrome
```
This command downloads the Docker image for Selenium with a standalone Chrome browser. You can use this as a Selenium Node to execute tests on a remote WebDriver.
5. Run the Selenium Docker Container
```
docker run -d -p 5555:4444 --name selenium-hub1 selenium/standalone-chrome
```
This runs the Selenium Node in a Docker container with a Chrome browser on it, and exposes port 4444 from the container to port 5555 on your machine.
The Selenium Hub will interact with this Node by connecting to it through port 4444.
6. Check Selenium Hub Status
```
curl http://localhost:5555/wd/hub/status
```
This command checks the status of your Selenium Hub or Node by making a request to the /wd/hub/status endpoint. It should return a status indicating the server is up and running.

![image](https://github.com/user-attachments/assets/dc3bf177-74fb-4186-aacb-cc63dd7a694a)
![image](https://github.com/user-attachments/assets/43f4c3e8-1d8e-4519-a8da-ad35c9ab554b)

7. Run Your Maven Test
```
mvn clean test -Dselenium.grid.url=http://localhost:5555/wd/hub
```
This runs your tests using Maven and specifies the URL of your Selenium Grid (Hub) as a system property (selenium.grid.url). It connects to the Hub via http://localhost:4444/wd/hub to request WebDriver sessions.
*** Code: ***
```
// Read the selenium.grid.url property from the command line (set by Maven)
String gridUrl = System.getProperty("selenium.grid.url", "http://localhost:5555/wd/hub");
// Initialize RemoteWebDriver with the grid URL and Chrome options
driver.set(new RemoteWebDriver(new URL(gridUrl), chromeOptions));
```



## 5. Generating Allure Report
Navigate to the folder containing `allure-results` and run:
```sh
allure serve allure-results
```
![image](https://github.com/user-attachments/assets/58c1ab9f-d985-4426-8cd5-211a8e1e1562)

![image](https://github.com/user-attachments/assets/d0a0e3ff-cbb2-487d-91ee-fb019335b2cf)


