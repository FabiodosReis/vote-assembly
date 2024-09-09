SELECT
    id,
    status,
    create_date,
    associate_id,
    subject_id
FROM vote
WHERE id = :id