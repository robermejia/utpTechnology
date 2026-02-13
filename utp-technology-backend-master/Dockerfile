FROM eclipse-temurin:21-jdk-alpine AS build

# Copiando mvnw (Maven Wrapped)
COPY mvnw pom.xml /app/
ADD .mvn /app/.mvn

WORKDIR /app

# Descargando dependencias
RUN ./mvnw package || echo "Dependencies downloaded."

# Compilando app
COPY . /app/
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine AS production

WORKDIR /app
COPY --from=build /app/target/technology-0.0.1-SNAPSHOT.jar /app/backend.jar

EXPOSE 8080
CMD ["java", "-jar", "backend.jar"]
