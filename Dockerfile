# https://spring.io/guides/gs/spring-boot-docker/
FROM openjdk:17-jdk-alpine

ARG MYSQL_ADDON_URI
ENV MYSQL_ADDON_URI ${MYSQL_ADDON_URI?notset}
WORKDIR /usr/src/app

COPY . .

RUN addgroup -g 1001 -S appuser && adduser -u 1001 -S appuser -G appuser
RUN chown -R 1001:1001 /usr/src/app
RUN ls
USER 1001

EXPOSE 8080
RUN cat  /usr/src/app/src/main/resources/application-mogenius.properties >  /usr/src/app/src/main/resources/application.properties
RUN chmod +x gradlew
RUN echo ${MYSQL_ADDON_URI}
RUN ./gradlew build -MYSQL_ADDON_URI="jdbc:${MYSQL_ADDON_URI}"

ENTRYPOINT ["java","-jar","/usr/src/app/build/libs/tasks-0.0.1-SNAPSHOT.jar"]