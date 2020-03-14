FROM java:8
MAINTAINER "Paulson <paulson@gmail.com>"
VOLUME /tmp
COPY target/seckil-0.0.1-SNAPSHOT.jar seckil.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","seckil.jar"]