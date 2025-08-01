FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y \
    wget curl gnupg unzip xvfb firefox-esr maven procps \
    && rm -rf /var/lib/apt/lists/*

RUN curl -sSL https://dl.google.com/linux/linux_signing_key.pub | \
    gpg --dearmor -o /etc/apt/trusted.gpg.d/google.gpg && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Install ChromeDriver with fallback to latest if version specific not found
RUN CHROME_VERSION=$(google-chrome-stable --version | grep -oP '\d+\.\d+\.\d+\.\d+' | head -1) && \
    CHROME_MAJOR_VERSION=$(echo $CHROME_VERSION | cut -d '.' -f1) && \
    CHROMEDRIVER_VERSION=$(curl -fsSL "https://chromedriver.storage.googleapis.com/LATEST_RELEASE_$CHROME_MAJOR_VERSION" || echo "") && \
    if [ -z "$CHROMEDRIVER_VERSION" ]; then \
      echo "Version specific chromedriver not found, fallback to latest"; \
      CHROMEDRIVER_VERSION=$(curl -fsSL "https://chromedriver.storage.googleapis.com/LATEST_RELEASE"); \
    fi && \
    echo "Using Chrome version $CHROME_VERSION and Chromedriver version $CHROMEDRIVER_VERSION" && \
    wget -q "https://chromedriver.storage.googleapis.com/${CHROMEDRIVER_VERSION}/chromedriver_linux64.zip" -O /tmp/chromedriver.zip && \
    unzip /tmp/chromedriver.zip -d /usr/local/bin/ && \
    rm /tmp/chromedriver.zip && \
    chmod +x /usr/local/bin/chromedriver

ENV GECKODRIVER_VERSION=v0.33.0
RUN wget -q "https://github.com/mozilla/geckodriver/releases/download/${GECKODRIVER_VERSION}/geckodriver-${GECKODRIVER_VERSION}-linux64.tar.gz" -O /tmp/geckodriver.tar.gz && \
    tar -xzf /tmp/geckodriver.tar.gz -C /usr/local/bin/ && \
    rm /tmp/geckodriver.tar.gz && \
    chmod +x /usr/local/bin/geckodriver

WORKDIR /seleniumjenkins
COPY . .

ENV DISPLAY=:99

CMD ["sh", "-c", "Xvfb :99 -screen 0 1920x1080x24 & mvn clean test"]
