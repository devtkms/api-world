FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY ../gradlew .
COPY ../gradle ./gradle
RUN chmod +x gradlew

COPY ../build.gradle ../settings.gradle ./

RUN ./gradlew dependencies --no-daemon

COPY .. .

RUN ./gradlew clean build -x test --no-daemon

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar /apiworld.jar

ENTRYPOINT ["java", "-jar", "/apiworld.jar"]