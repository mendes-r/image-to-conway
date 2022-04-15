FROM openjdk:11

COPY build/libs/*.jar /
COPY docker/entrypoint.sh /

RUN chmod +x /entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]
