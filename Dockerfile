FROM maven:3.8.1-openjdk-17-slim
RAILWAY_VOLUME_NAME tmp
RAILWAY_VOLUME_PATH /tmp
COPY .env .env
COPY pom.xml pom.xml
COPY src src
RUN mvn clean package
CMD ["java","-jar","/target/Golfskor-1.0.0.jar"]
