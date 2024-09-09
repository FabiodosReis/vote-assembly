-- liquibase formatted SQL
-- changeset fabio.silva:1 endDelimiter:;

CREATE TABLE IF NOT EXISTS `session` (
    id CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    description VARCHAR(100) NOT NULL UNIQUE,
    start_date DATETIME NOT NULL DEFAULT NOW(),
    end_date DATETIME,
    PRIMARY KEY(id)
);

INSERT INTO `session` (
    id,
    description,
    start_date,
    end_date
) VALUES(
    'be4d5720-1cff-4c87-b60c-2fab58e9c05c',
    'Session 9 AM',
    NOW(),
    NOW()
);

INSERT INTO `session` (
    id,
    description,
    start_date
) VALUES(
    '1547f7c2-b8e2-44fd-9c78-c8f61674a307',
    'SESSION Judgement',
    NOW()
);

