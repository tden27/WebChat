create sequence hibernate_sequence start 1 increment 1;

create table message
(
    id       int8          not null,
    datetime timestamp,
    text     varchar(2048) not null,
    user_id  int8,
    primary key (id)
);
create table user_role
(
    username int8 not null,
    roles    varchar(255)
);
create table users
(
    id       int8         not null,
    active   boolean,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);
alter table if exists message
    add constraint message_user_fk foreign key (user_id) references users;
alter table if exists user_role
    add constraint user_role_user_fk foreign key (username) references users;