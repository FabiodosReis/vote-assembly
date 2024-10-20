package com.backoffice.app.application.repository.subject;

import com.backoffice.core.subject.v1.adapter.SubjectDataProcess;
import com.backoffice.core.subject.v1.model.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.backoffice.app.application.constants.ApplicationConstants.FILE_SEPARATOR;
import static com.backoffice.app.application.utils.FileUtils.getSql;

@Repository
@RequiredArgsConstructor
public class SubjectRepository implements SubjectDataProcess {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${sql.base-path.subject}")
    private String basePath;

    @Override
    public Optional<Subject> save(Subject subject) {
        var sql = getSql(basePath.concat(FILE_SEPARATOR).concat("insertSubject.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", subject.getId());
        param.put("description", subject.getDescription());
        param.put("startDate", subject.getStartDate());
        param.put("endDate", subject.getEndDate());
        param.put("sessionId", subject.getSessionId());

        jdbcTemplate.update(sql, param);
        return findById(subject.getId());
    }

    @Override
    public boolean existsByDescription(Subject subject) {
        var sql = getSql(basePath.concat(FILE_SEPARATOR).concat("findSubjectByDescription.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("description", subject.getDescription());
        param.put("id", subject.getId());

        var result = jdbcTemplate.queryForObject(sql, param, Integer.class);
        return result != null && result == 1;
    }

    @Override
    public Optional<Subject> findById(String id) {
        try {

            var sql = getSql(basePath.concat(FILE_SEPARATOR).concat("findSubjectById.sql"));
            Map<String, Object> param = new HashMap<>();
            param.put("id", id);

            var session = jdbcTemplate.queryForObject(sql, param, rowMapper);
            return session == null ? Optional.empty() : Optional.of(session);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void disableSubject(String id, LocalDateTime endDate) {
        var sql = getSql(basePath.concat(FILE_SEPARATOR).concat("disableSubject.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("endDate", endDate);

        jdbcTemplate.update(sql, param);
    }

    @Override
    public List<Subject> findAllBySessionId(String sessionId) {
        Map<String, Object> param = new HashMap<>();
        param.put("sessionId", sessionId);

        var sql = getSql(basePath.concat(FILE_SEPARATOR).concat("findAllSubjectBySessionId.sql"));
        return jdbcTemplate.query(sql, param, rowMapper);
    }

    @Override
    public void close(String id, LocalDateTime endDate) {
        var sql = getSql(basePath.concat(FILE_SEPARATOR).concat("closeSubject.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("endDate", endDate);

        jdbcTemplate.update(sql, param);
    }


    private final RowMapper<Subject> rowMapper = (rs, row) -> {
        var subject = new Subject(
                rs.getString("id"),
                rs.getString("description"),
                rs.getString("session_id"),
                rs.getTimestamp("end_date") != null ? rs.getTimestamp("end_date").toLocalDateTime() : null
        );
        subject.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());

        return subject;
    };
}
