INSERT INTO vote (
    id,
    status,
    create_date,
    subject_id,
    associate_id
) VALUES(
    :id,
    :status,
    :createDate,
    :subjectId,
    :associateId
 );