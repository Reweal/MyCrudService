# Базовый образ для Java 11
FROM openjdk:11

# Копируем jar-файл в контейнер
COPY target/myservice-0.0.1-SNAPSHOT.jar /app.jar

# Устанавливаем порт, который будет использоваться в контейнере
EXPOSE 8080

# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "/app.jar"]