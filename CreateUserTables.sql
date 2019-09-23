create table users (
    email varchar(255) not null,
    password varchar(64) not null,
    name varchar(30) not null,
    primary key (email)
);

create table user_groups (
    email varchar(255) not null,
    groupname varchar(32) not null,
    primary key (email)
);