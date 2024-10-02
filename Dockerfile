FROM openjdk:17

WORKDIR /app

ENV AWS_REGION=us-east-1

COPY app/target/api-vote-assembly-*-RELEASE.jar /app/api-vote-assembly.jar

EXPOSE 8080

CMD ["java", "-jar", "api-vote-assembly.jar"]

