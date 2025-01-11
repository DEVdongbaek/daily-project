# Base image 설정
FROM bellsoft/liberica-openjdk-alpine:17 AS builder

# 작업 디렉토리 설정
WORKDIR /app

# 소스 복사
COPY . .

# 의존성 설치 및 빌드 (테스트 스킵)
RUN ./gradlew clean build -x test

# 런타임 이미지를 위한 기본 이미지
FROM bellsoft/liberica-openjdk-alpine:17

# 빌드된 JAR 복사
ARG JAR_FILE=build/libs/*.jar
COPY --from=builder /app/${JAR_FILE} app.jar

# 포트 설정
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
