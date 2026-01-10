FROM eclipse-temurin:17-jre
LABEL authors="hcy18"

# 设置工作目录
WORKDIR /app

COPY target/shopmind-user-service-1.0-SNAPSHOT.jar shopmind-user-service.jar

EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "/app/shopmind-user-service.jar"]