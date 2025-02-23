FROM bellsoft/liberica-openjdk-alpine:17 AS build

WORKDIR /app

COPY build/libs/gbict-0.0.1-SNAPSHOT.jar gbict_re.jar

# 로그 디렉토리 생성
RUN mkdir /logs

ENTRYPOINT ["java", "-jar", "gbict_re.jar"]