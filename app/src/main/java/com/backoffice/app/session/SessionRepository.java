package com.backoffice.app.session;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.backoffice.app.utils.FileUtils.getSql;

@Repository
@RequiredArgsConstructor
public class SessionRepository implements SessionDataProvider {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${sql.base-path.session}")
    private String basePath;

    @Override
    public Optional<Session> save(Session session) {
        var sql = getSql(basePath.concat("/insertSession.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", session.getId());
        param.put("description", session.getDescription());
        param.put("startDate", session.getStartDate());

        jdbcTemplate.update(sql, param);
        return findById(session.getId());
    }

    @Override
    public Optional<Session> closeSession(Session session) {
        var sql = getSql(basePath.concat("/closeSession.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", session.getId());
        param.put("endDate", session.getEndDate());

        jdbcTemplate.update(sql, param);
        return findById(session.getId());
    }

    @Override
    public Optional<Session> findById(String id) {
        try {

            Map<String, Object> param = new HashMap<>();
            param.put("id", id);

            var sql = getSql(basePath.concat("/findSessionById.sql"));
            var session = jdbcTemplate.queryForObject(sql, param, rowMapper);
            return session == null ? Optional.empty() : Optional.of(session);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsByDescription(String description) {
        var sql = getSql(basePath.concat("/existsAssociateByDescription.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("description",description);

        var result = jdbcTemplate.queryForObject(sql, param, Integer.class);
        return result != null && result == 1;
    }

    private final RowMapper<Session> rowMapper = (rs, row) -> {
        var session = new Session(
                rs.getString("id"),
                rs.getString("description")
        );
        session.setStartDate(rs.getTimestamp("startDate").toLocalDateTime());
        session.setEndDate(rs.getTimestamp("endDate") != null ? rs.getTimestamp("endDate").toLocalDateTime() : null);

        return session;
    };

}
