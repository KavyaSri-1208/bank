# Dockerfile
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the Java source file
COPY KlinkaaraBank.java .

# Compile the Java application
RUN javac KlinkaaraBank.java

# Expose the port the app runs on
EXPOSE 8080

# Run the compiled Java application
CMD ["java", "KlinkaaraBank"]