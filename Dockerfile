FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} product.jar

ENTRYPOINT ["java","-jar","product.jar"]