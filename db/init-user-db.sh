#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER evaluacion WITH PASSWORD '$DBUSER_PASSWORD';
	CREATE DATABASE evaluaciondb;
	GRANT ALL PRIVILEGES ON DATABASE evaluaciondb TO evaluacion;

EOSQL
