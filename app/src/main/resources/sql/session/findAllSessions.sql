SELECT
	ss.id as session_id,
	ss.description as session_description,
	(CASE
		WHEN ss.end_date IS NULL AND ss.id IS NOT NULL THEN 'OPEN'
		ELSE 'CLOSED'
	END) as session_status,
	v.id as vote_id,
	a.cpf as cpf,
	s.id as subject_id,
	s.description as subject_description,
	(CASE
		WHEN (s.id IS NOT NULL) AND (NOW() < s.end_date) THEN 'OPEN'
		WHEN (s.id IS NOT NULL AND NOW() = s.end_date) OR (s.id IS NOT NULL AND NOW() > s.end_date) THEN 'CLOSED'
		ELSE null
	END) as subject_status,
	v.status as vote_status
FROM `session` ss
LEFT JOIN subject s ON s.session_id = ss.id
LEFT vote v ON v.subject_id = s.id
LEFT associate a ON a.id = v.associate_id
ORDER BY v.create_date
LIMIT :page,:size