SELECT
    id,
    name,
    cpf,
    status
 FROM associate
 WHERE (:cpf = null OR :cpf = cpf)
 LIMIT :page,:size