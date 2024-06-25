# Use uma imagem base que tenha o Java 21
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e os arquivos de código fonte para o diretório de trabalho
COPY pom.xml .
COPY src ./src

# Defina o comando para rodar a aplicação
CMD ["java", "-jar", "target/xy-inc.jar"]
