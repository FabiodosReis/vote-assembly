SELECT
    id,
    description,
    start_date,
    end_date,
    session_id,
    enable
FROM subject
WHERE session_id = :sessionId