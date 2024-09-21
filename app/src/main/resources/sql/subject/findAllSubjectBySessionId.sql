SELECT
    id,
    description,
    start_date,
    end_date,
    session_id
FROM subject
WHERE session_id = :sessionId