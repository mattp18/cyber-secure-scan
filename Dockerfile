FROM openjdk:17-jdk-alpine

# Install required native libraries
RUN apk update && \
    apk add --no-cache fontconfig ttf-dejavu freetype && \
    # Install msttcorefonts-installer to get Microsoft TrueType core fonts including Arial
    apk add --no-cache msttcorefonts-installer && \
    update-ms-fonts && \
    fc-cache -f && \
    rm -rf /var/cache/apk/*

VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar

COPY ./build/libs/cyber-secure-scan-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]