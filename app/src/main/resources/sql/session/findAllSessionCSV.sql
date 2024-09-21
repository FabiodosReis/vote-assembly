SELECT
    s2.description as "session_description",
    s.description as "subject_description",
	COUNT(v.id) as quantity
	FROM vote v
JOIN subject s ON s.id = v.subject_id
JOIN `session` s2 ON s2.id = s.session_id
GROUP BY s2.id  , s.id
LIMIT :page,:size