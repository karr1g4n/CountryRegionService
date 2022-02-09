create table country_region_access
(
    id     SERIAL not null primary key unique,
    access varchar(255)

);
create table country_region
(
    id                SERIAL not null primary key unique,
    country           varchar(255) unique,
    region            varchar(255),
    country_access_id integer,
    region_access_id  integer,
    FOREIGN KEY (country_access_id) REFERENCES country_region_access (id),
    FOREIGN KEY (region_access_id) REFERENCES country_region_access (id)
)
