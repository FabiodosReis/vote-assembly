-- liquibase formatted SQL
-- changeset fabio.silva:1 endDelimiter:;

CREATE TABLE IF NOT EXISTS subject (
    id CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    description VARCHAR(100) NOT NULL UNIQUE,
    start_date DATETIME NOT NULL DEFAULT NOW(),
    end_date DATETIME NOT NULL,
    session_id CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    enable BOOLEAN DEFAULT true,
    PRIMARY KEY(id),
    FOREIGN KEY(session_id) REFERENCES `session`(id)
);

INSERT INTO subject (
    id,
    description,
    start_date,
    end_date,
    session_id
) VALUES(
    '53dc8338-bff4-467b-b8a5-7d4a554dd8af',
    'subject 1',
    NOW(),
    NOW(),
    'be4d5720-1cff-4c87-b60c-2fab58e9c05c'
);

INSERT INTO subject (
    id,
    description,
    start_date,
    end_date,
    session_id
) VALUES(
    '0191ce23-dab6-71a3-8a30-fc26a8132f59',
    'subject 2',
    NOW(),
    NOW(),
    'be4d5720-1cff-4c87-b60c-2fab58e9c05c'
);

