FROM amazoncorretto:17-al2022-jdk
ADD target/portfolio-0.0.1-SNAPSHOT.jar portfolio-ap-springboot-docker.jar
ENTRYPOINT ["java", "-jar", "portfolio-ap-springboot-docker.jar"]