FROM openjdk:8-jre-alpine
MAINTAINER "SpringRestFullAPI With DockerFile"
ENV JAVA_OPTS ""
RUN mkdir -p /opt/web/spring-restfull
COPY build/libs/SpringBootH2-0.0.1-SNAPSHOT.jar /opt/web/spring-restfull

WORKDIR /opt/web/spring-restfull
EXPOSE 8080
CMD ["sh","-c","java $JAVA_OPTS -jar SpringBootH2-0.0.1-SNAPSHOT.jar"]