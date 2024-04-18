FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /app
COPY build/libs/backendTestGym-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
