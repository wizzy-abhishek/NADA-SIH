FROM openjdk:22-jdk
COPY target/nada.jar nada.jar
ENTRYPOINT ["java", "-jar" , "/nada.jar"]
