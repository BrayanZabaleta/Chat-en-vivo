# Usa una imagen base de Java 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto
COPY . .

# Compila el proyecto con Maven Wrapper (sin ejecutar tests)
RUN ./mvnw clean package -DskipTests

# Expón el puerto (opcional, Render detecta automáticamente)
EXPOSE 8080

# Comando para iniciar la app
CMD ["java", "-jar", "target/chat-reactivo-0.0.1-SNAPSHOT.jar"]
