From openjdk:8-jdk-alpine
COPY . /app
WORKDIR /app
ADD target/zerorpc-1.0-SNAPSHOT.jar zerorpc.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar" "zerorpc.jar"]
