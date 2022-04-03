FROM openjdk:11

COPY *.jar /
COPY entrypoint.sh /

RUN chmod +x /entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]
