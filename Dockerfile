# Use OpenJDK base
FROM openjdk:21-jdk-slim

# Install dependencies + Maven
RUN apt-get update && apt-get install -y \
    wget curl gnupg unzip xvfb firefox-esr maven \
    && rm -rf /var/lib/apt/lists/*

# Install Chrome
RUN curl -sSL https://dl.google.com/linux/linux_signing_key.pub | \
    gpg --dearmor -o /etc/apt/trusted.gpg.d/google.gpg && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy the entire Maven project
COPY . .

# Run tests using Maven
RUN mvn clean test

# Default command
CMD ["echo", "Tests completed inside Docker"]
