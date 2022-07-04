CREATE DATABASE resources;
grant all privileges on database "resources" to "admin";
CREATE TABLE resources(
id              SERIAL      PRIMARY KEY,
key             VARCHAR(32) NOT NULL,
);
