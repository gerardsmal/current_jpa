# usa un imagine bas con java 21
FROM alpine/java:21-jdk
# imposta la directory di lavoro all'interne del container
WORKDIR /app
# copia il file JAR compilato nelle directory di lavoro
COPY target/*.jar app.jar
# Espore la porta la l'app Spring BOOt in ascoloto
EXPOSE 8080
# Commando per eseguire l'applicazione Spring boot
ENTRYPOINT ["java", "-jar", "app.jar"]