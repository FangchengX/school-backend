FROM mysql:5.7
ENV LANG=C.UTF-8
COPY deploy/*.sql /docker-entrypoint-initdb.d/
COPY ./*.sql /docker-entrypoint-initdb.d/
