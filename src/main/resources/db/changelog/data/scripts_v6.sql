--liquibase formatted sql
--changeset ercan:006.1
alter table questions alter column answer type varchar using answer::varchar;