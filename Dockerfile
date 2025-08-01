FROM maven:3.9.6-eclipse-temurin-21

# Install Chrome & Firefox
RUN apt-get update && apt-get install -y \
    wget curl gnupg unzip xvfb firefox-esr \
    && curl -sSL https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /etc/apt/trusted.gpg.d/google.gpg && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /seleniumjenkins

COPY . .

RUN mvn clean test

CMD ["echo", "Tests completed in Docker"]
