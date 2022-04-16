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


---- UserRole Table Script ---
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


---- Categories Table Script ---
create table categories
(
    id                 bigint not null constraint categories_pkey primary key,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    record_status      integer,
    description        varchar(255),
    title              varchar(255)
);


---- Quizzes Table Script ---
create table quizzes
(
    id                  bigint not null constraint quizzes_pkey primary key,
    created_by          varchar(255),
    created_date        timestamp,
    last_modified_by    varchar(255),
    last_modified_date  timestamp,
    record_status       integer,
    description         varchar(255),
    enabled             integer,
    max_marks           varchar(255),
    number_of_questions varchar(255),
    title               varchar(255),
    category_id         bigint constraint fkpo9fnqd9hnnmg8qxiyue40cot references categories
);


---- Questions Table Script ---
create table questions
(
    id                 bigint not null constraint questions_pkey primary key,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    record_status      integer,
    answer             varchar(255),
    content            varchar(5000),
    image              varchar(255),
    option1            varchar(255),
    option2            varchar(255),
    option3            varchar(255),
    option4            varchar(255),
    quiz_id            bigint constraint fkn3gvco4b0kewxc0bywf1igfms references quizzes
);
