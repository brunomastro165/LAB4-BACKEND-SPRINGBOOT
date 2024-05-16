
# Usar una imagen base de OpenJDK
FROM openjdk:8

# Exponer el puerto 8080
EXPOSE 8080

# Crear un directorio para la aplicación
RUN mkdir -p /app/

# Agregar el archivo JAR de la aplicación al directorio
ADD build/libs/buen-sabor-0.0.1-SNAPSHOT.jar /app/buen-sabor-0.0.1-SNAPSHOT.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/buen-sabor-0.0.1-SNAPSHOT.jar "]