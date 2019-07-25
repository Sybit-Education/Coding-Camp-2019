FROM openjdk:8-jdk-alpine

RUN mkdir /app

WORKDIR /app

COPY ./target/coding-camp-2019-0.0.1-SNAPSHOT.jar /app/app.jar

VOLUME /app/log

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
