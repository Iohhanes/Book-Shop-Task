FROM adoptopenjdk:11-jre-hotspot

ENV JAVA_OPTS=""

WORKDIR /opt

COPY /build/libs/book-shop.jar .

EXPOSE 8080

CMD java ${JAVA_OPTS} -jar book-shop.jar
