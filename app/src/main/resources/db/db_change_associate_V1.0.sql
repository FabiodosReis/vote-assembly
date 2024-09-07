-- liquibase formatted SQL
-- changeset fabio.silva:1 endDelimiter:;

CREATE TABLE IF NOT EXISTS associate (
    id CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT (UUID()),
    cpf VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(cpf)
);

INSERT INTO associate(
    id,
    cpf,
    name,
    status
) VALUES(
    'd6f2db9a-d7c9-4b10-b23e-0bb1ca90ffab',
    '055.613.735-36',
    'Fabio R V Silva',
    'ABLE_TO_VOTE'
);

INSERT INTO associate(
    id,
    cpf,
    name,
    status
) VALUES(
    'cde53c5d-7a03-426d-8538-c3b2ffc338aa',
    '221.224.216-65',
    'José Ruas',
    'ABLE_TO_VOTE'
);

