---- Users Table Script ---
create table users
(
    id                 bigint not nullconstraint users_pkey primary key,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    record_status      integer,
    email              varchar(255),
    enabled            integer,
    first_name         varchar(255),
    last_name          varchar(255),
    password           varchar(255),
    phone              varchar(255),
    profile            varchar(255),
    username           varchar(100)
);

---- Roles Table Script ---
create table roles
(
    id                 bigint not null constraint roles_pkey primary key,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    record_status      integer,
    role_name          varchar(255)
);

---- User_Role Table Script ---
create table user_role
(
    id                 bigint not null constraint user_role_pkey primary key,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    record_status      integer,
    role_id            bigint constraint fkt7e7djp752sqn6w22i6ocqy6q references roles,
    user_id            bigint constraint fkj345gk1bovqvfame88rcx7yyx references users
);
