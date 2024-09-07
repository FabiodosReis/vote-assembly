SELECT
    id,
    name,
    cpf,
    status
FROM associate
WHERE (:cpf IS NULL OR cpf = :cpf )
LIMIT :page,:size