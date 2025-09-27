FROM postgres:latest
COPY ./init_script.sql /docker-entrypoint-initdb.d/