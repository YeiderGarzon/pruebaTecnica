FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp
COPY target/*.jar app.jar

# Forzar Java a usar IPv6
ENTRYPOINT ["java","-Djava.net.preferIPv4Stack=false","-Djava.net.preferIPv6Addresses=true","-jar","/app.jar"]
