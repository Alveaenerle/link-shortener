FROM openjdk:25-bullseye

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY ./url-shortener/pom.xml /app
COPY ./url-shortener/src /app/src

RUN mvn clean install

ENTRYPOINT ["java", "-jar", "target/url-shortener-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
