FROM openjdk:17-alpine
RUN apk --no-cache add curl
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#RUN mkdir /git-cfg
#ADD git-cfg /
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
