FROM alpine
RUN apk add openjdk11 curl jq
ENV PATH=$PATH:/usr/lib/jvm/java-11-openjdk/bin

WORKDIR /usr/test

ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs
ADD testng.xml testng.xml
ADD healthcheck.sh healthcheck.sh

ENTRYPOINT sh healthcheck.sh