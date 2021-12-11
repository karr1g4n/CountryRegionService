FROM openjdk:8-jdk-alpine
COPY  target/countryRegionService-0.0.1-SNAPSHOT.jar country-region-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/country-region-0.0.1.jar"]