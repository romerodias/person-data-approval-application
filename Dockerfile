FROM openjdk:8-jdk-alpine
ADD target/personaldataapproval*.jar personal-data-approval.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar","/personal-data-approval.jar"]
