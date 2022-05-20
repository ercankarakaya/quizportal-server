--liquibase formatted sql
--changeset ercan:005.1
ALTER TABLE quizzes
ALTER COLUMN description TYPE text;