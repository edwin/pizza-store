FROM quay.io/quarkus/quarkus-micro-image:1.0

COPY target/*-runner /work/application
RUN chmod 775 /work
EXPOSE 8080
CMD ["/work/application", "-Dquarkus.http.host=0.0.0.0"]
