-- liquibase formatted SQL
-- changeset fabio.silva:1 endDelimiter:;

CREATE TABLE IF NOT EXISTS vote (
    id CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    status VARCHAR(100) NOT NULL UNIQUE,
    create_date DATETIME NOT NULL DEFAULT NOW(),
    subject_id CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    associate_id CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(subject_id) REFERENCES subject(id),
    FOREIGN KEY(associate_id) REFERENCES associate(id)
);

INSERT INTO vote (
    id,
    status,
    create_date,
    subject_id,
    associate_id
) VALUES(
    '0191d2f4-302f-7399-94bb-3d3317f09ba5',
    'subject 1',
    NOW(),
    NOW(),
    '53dc8338-bff4-467b-b8a5-7d4a554dd8af',
    'd6f2db9a-d7c9-4b10-b23e-0bb1ca90ffab'
);


