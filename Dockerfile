# Use a base image with Java installed
FROM openjdk:21-jdk

# Set working directory
WORKDIR /SeleniumJenkins

# Copy the built jar file from target folder
COPY target/my-selenium-tests.jar selenium-tests.jar

# Entry point to run tests
ENTRYPOINT ["java", "-jar", "selenium-tests.jar"]
