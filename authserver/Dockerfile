FROM openjdk:17-alpine
RUN apk --no-cache add curl
RUN apk --no-cache add bash
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY entrypoint.sh /
COPY wait-for-it.sh /
RUN chmod +x ./wait-for-it.sh ./entrypoint.sh
EXPOSE 4555
ENTRYPOINT ["./entrypoint.sh"]
CMD ["java","-jar","/app.jar"]
