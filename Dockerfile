FROM openjdk:11
ADD target/pices-mart-0.0.1-SNAPSHOT.jar pices-mart-0.0.1-SNAPSHOT.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "pices-mart-0.0.1-SNAPSHOT.jar"]