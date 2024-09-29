CREATE DATABASE gestion_analytique;

USE gestion_analytique;

CREATE TABLE IF NOT EXISTS centre(
    id INTEGER AUTO_INCREMENT,
    libele VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS unite_oeuvre(
    id INTEGER AUTO_INCREMENT,
    libele VARCHAR(225),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS charge(
    id INTEGER AUTO_INCREMENT,
    rubrique VARCHAR(255),
    prix DECIMAL(15,2),
    nature CHAR,
    id_unite INTEGER REFERENCES unite_oeuvre(id),
    PRIMARY KEY(id)
);
CREATE TABLE centre_charge(
    id INTEGER AUTO_INCREMENT,
    id_centre INTEGER REFERENCES centre(id),
    id_charge INTEGER REFERENCES charge(id),
    prix DECIMAL(15,2),
    pourcentage DECIMAL(5,2),
    PRIMARY KEY(id)
);

