create table country_region_access
(
    id     SERIAL not null primary key,
    access varchar(255)

);
create table country_region
(
    id                SERIAL not null primary key,
    country           varchar(255),
    region            varchar(255),
    country_access_id integer,
    region_access_id  integer,
    FOREIGN KEY (country_access_id) REFERENCES country_region_access (id),
    FOREIGN KEY (region_access_id) REFERENCES country_region_access (id)
)
