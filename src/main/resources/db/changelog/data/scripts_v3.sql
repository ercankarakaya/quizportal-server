--liquibase formatted sql
--changeset ercan:003.1
insert into users (id, created_by, created_date, last_modified_by,
                   last_modified_date, record_status, email, enabled,
                   first_name, last_name, password, phone, profile, username)
values (21436587,'admin',CURRENT_DATE,'admin',CURRENT_DATE,1,'admin@gmail.com',
        1,'admin','admin','$2a$10$p250j1Mln6Kl.4Ev4Maffuj0ElrVCDCj3F7AcjPSdPUoXph0zeK2q','11111',null,'admin');

insert into users (id, created_by, created_date, last_modified_by,
                   last_modified_date, record_status, email, enabled,
                   first_name, last_name, password, phone, profile, username)
values (78563412,'admin',CURRENT_DATE,'admin',CURRENT_DATE,1,'ercan@gmail.com',
        1,'ercan','karakaya','$2a$10$p250j1Mln6Kl.4Ev4Maffuj0ElrVCDCj3F7AcjPSdPUoXph0zeK2q','11111',null,'ercan');

--changeset ercan:003.2
insert into roles (id, created_by, created_date, last_modified_by,
                          last_modified_date, record_status, role_name)
values (1,'admin',CURRENT_DATE ,'admin',CURRENT_DATE ,1,'ADMIN');

insert into roles (id, created_by, created_date, last_modified_by,
                          last_modified_date, record_status, role_name)
values (2,'admin',CURRENT_DATE ,'admin',CURRENT_DATE ,1,'USER');

--changeset ercan:003.3
insert into user_role (id, created_by, created_date, last_modified_by, last_modified_date,
                       record_status, role_id,  user_id)
values (1,'admin',CURRENT_DATE ,'admin',CURRENT_DATE,1,1,21436587);

insert into user_role (id, created_by, created_date, last_modified_by, last_modified_date,
                       record_status, role_id,  user_id)
values (2,'admin',CURRENT_DATE ,'admin',CURRENT_DATE,1,2,78563412);

--changeset ercan:003.4
CREATE EXTENSION pgcrypto;
UPDATE users SET password = crypt('123456', gen_salt('md5')) where id is not null ;

--changeset ercan:003.5
DROP extension pgcrypto;