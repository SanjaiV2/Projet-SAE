DROP TABLE IF EXISTS region,sub_region,country,climate_disaster,disaster;
DROP TABLE IF EXISTS sanjai;

CREATE TABLE region (
    region_code INTEGER PRIMARY KEY,
    name VARCHAR NOT NULL);

CREATE TABLE sub_region (
    sub_region_code INTEGER PRIMARY KEY,
    name VARCHAR NOT NULL,
    region_code INTEGER REFERENCES region(region_code));

CREATE TABLE country (
    country_code SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    ISO2 CHAR(2),
    ISO3 CHAR(3),
    sub_region_code INTEGER REFERENCES sub_region(sub_region_code));

CREATE TABLE disaster (
    disaster_code SERIAL PRIMARY KEY,
    disaster VARCHAR NOT NULL);

CREATE TABLE climate_disaster (
    country_code INTEGER REFERENCES country(country_code),
    disaster_code INTEGER REFERENCES disaster(disaster_code),
    year INTEGER,
    number INTEGER,
    PRIMARY KEY (country_code, disaster_code, year));
    
CREATE TEMP TABLE sanjai (
    country VARCHAR,
    iso2 CHAR(2),
    iso3 CHAR(3),
    region_code INTEGER,
    region VARCHAR,
    sub_region_code INTEGER,
    sub_region VARCHAR,
    disaster VARCHAR,
    year INTEGER,
    number INTEGER);
    
    
\copy sanjai FROM 'Documents/Climate_related_disasters_frequency.csv' CSV HEADER;    

-- région

INSERT INTO region (region_code, name)
SELECT DISTINCT region_code, region
FROM sanjai;

-- sub_region

INSERT INTO sub_region (sub_region_code, name, region_code)
SELECT DISTINCT sub_region_code, sub_region, region_code
FROM sanjai;

-- country

INSERT INTO country (name, ISO2, ISO3, sub_region_code)
SELECT DISTINCT country, ISO2, ISO3, sub_region_code
FROM sanjai;
    
-- disaster

INSERT INTO disaster(disaster)
SELECT DISTINCT disaster
FROM sanjai;    

-- climate_disaster

INSERT INTO climate_disaster (country_code, disaster_code, year, number)

SELECT
 country.country_code,
 disaster.disaster_code,
 chiffre.year,
 chiffre.number

FROM sanjai chiffre 
JOIN country country ON chiffre.country = country.name
JOIN disaster disaster ON chiffre.disaster = disaster.disaster;


SELECT * FROM region;
SELECT * FROM sub_region;
SELECT * FROM country;
SELECT * FROM disaster;
SELECT * FROM climate_disaster;
