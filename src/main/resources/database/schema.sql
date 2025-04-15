create table users (
    id uuid not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
)

create table profiles (
    id uuid not null,
    active boolean not null,
    created timestamp(6) not null,
    email varchar(100) not null,
    last_login timestamp(6) not null,
    modified timestamp(6) not null,
    name varchar(50) not null,
    password varchar(50) not null,
    token varchar(200) not null,
    primary key (id)
)

create table phones (
    id uuid not null,
    city_code varchar(2) not null,
    country_code varchar(2) not null,
    number varchar(50) not null,
    id_profile uuid not null,
    primary key (id)
)



