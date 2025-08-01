# Base image with Java and Debian (for apt support)
FROM openjdk:21-jdk-slim

# Install system dependencies and browsers
RUN apt-get update && apt-get install -y \
    wget curl gnupg unzip xvfb firefox-esr \
    && rm -rf /var/lib/apt/lists/*

# Install Google Chrome
RUN curl -sSL https://dl.google.com/linux/linux_signing_key.pub | \
    gpg --dearmor -o /etc/apt/trusted.gpg.d/google.gpg && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Set working directory inside the container
WORKDIR /seleniumjenkins

# Copy entire Maven project into the container
COPY . .

# (Optional) Uncomment this if you're using Maven Wrapper
# RUN chmod +x mvnw

# Run tests using Maven
RUN mvn clean test

# Default command if someone runs the container manually
CMD ["echo", "Selenium tests executed inside Docker container"]
