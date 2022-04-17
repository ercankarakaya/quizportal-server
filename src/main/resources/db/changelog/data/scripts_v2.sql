--liquibase formatted sql
--changeset ercan:002.1
insert into users (id, created_by, created_date, last_modified_by,
                   last_modified_date, record_status, email, enabled,
                   first_name, last_name, password, phone, profile, username)
values (543534,'admin',CURRENT_DATE,'admin',CURRENT_DATE,1,'sys@gmail.com',
        1,'system','system','$2a$10$p250j1Mln6Kl.4Ev4Maffuj0ElrVCDCj3F7AcjPSdPUoXph0zeK2q','11111',null,'system');

insert into users (id, created_by, created_date, last_modified_by,
                   last_modified_date, record_status, email, enabled,
                   first_name, last_name, password, phone, profile, username)
values (987654,'admin',CURRENT_DATE,'admin',CURRENT_DATE,1,'testuser@gmail.com',
        1,'testuser','testuser','$2a$10$p250j1Mln6Kl.4Ev4Maffuj0ElrVCDCj3F7AcjPSdPUoXph0zeK2q','11111',null,'testuser');


--changeset ercan:002.2 context:test
insert into users (id, created_by, created_date, last_modified_by,
                   last_modified_date, record_status, email, enabled,
                   first_name, last_name, password, phone, profile, username)
values (676767,'admin',CURRENT_DATE,'admin',CURRENT_DATE,1,'testuser2@gmail.com',
        1,'testuser2','testuser2','$2a$10$p250j1Mln6Kl.4Ev4Maffuj0ElrVCDCj3F7AcjPSdPUoXph0zeK2q','11111',null,'testuser2');

