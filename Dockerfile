FROM amazoncorretto:21-alpine-jdk

COPY target/chat-reactivo-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 10000

ENV PORT=10000

ENTRYPOINT ["java", "-jar","/app.jar"]
