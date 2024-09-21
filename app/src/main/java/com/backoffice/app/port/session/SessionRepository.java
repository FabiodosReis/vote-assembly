package com.backoffice.app.port.session;

import com.backoffice.core.session.v1.adapter.SessionDataProcess;
import com.backoffice.core.session.v1.model.Session;
import com.backoffice.core.session.v1.vo.SessionFilterVO;
import com.backoffice.core.session.v1.vo.SessionVO;
import com.backoffice.core.session.v1.vo.VoteVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.backoffice.app.application.utils.FileUtils.getSql;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class SessionRepository implements SessionDataProcess {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${sql.base-path.session}")
    private String basePath;

    @Override
    public Optional<Session> save(Session session) {
        var sql = getSql(basePath.concat("insertSession.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", session.getId());
        param.put("description", session.getDescription());
        param.put("startDate", session.getStartDate());

        jdbcTemplate.update(sql, param);
        return findById(session.getId());
    }

    @Override
    public Optional<Session> closeSession(Session session) {
        var sql = getSql(basePath.concat("closeSession.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", session.getId());
        param.put("endDate", session.getEndDate());

        jdbcTemplate.update(sql, param);
        return findById(session.getId());
    }

    @Override
    public boolean sessionIsClosed(String sessionId) {
        var sql = getSql(basePath.concat("sessionIsClosed.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", sessionId);

        var result = jdbcTemplate.queryForObject(sql, param, Integer.class);
        return result != null && result == 1;
    }

    @Override
    public Optional<Session> findById(String id) {
        try {

            Map<String, Object> param = new HashMap<>();
            param.put("id", id);

            var sql = getSql(basePath.concat("findSessionById.sql"));
            var session = jdbcTemplate.queryForObject(sql, param, rowMapper);
            return session == null ? Optional.empty() : Optional.of(session);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<SessionVO> findAll(SessionFilterVO vo) {
        var page = isEmpty(vo.getPage()) ? 0 : vo.getSize();
        var size = isEmpty(vo.getSize()) ? 10 : vo.getSize();

        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("size", size);

        var sql = getSql(basePath.concat("findAllSessions.sql"));
        return jdbcTemplate.query(sql, param, rowMapperFindAll);
    }

    @Override
    public List<String[]> findAllCsv(SessionFilterVO vo) {
        var page = isEmpty(vo.getPage()) ? 0 : vo.getSize();
        var size = isEmpty(vo.getSize()) ? 10 : vo.getSize();

        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("size", size);

        var sql = getSql(basePath.concat("findAllSessionCSV.sql"));
        return jdbcTemplate.query(sql, param, rowMapperFile);
    }

    @Override
    public boolean existsByDescription(String description) {
        var sql = getSql(basePath.concat("existsAssociateByDescription.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("description", description);

        var result = jdbcTemplate.queryForObject(sql, param, Integer.class);
        return result != null && result == 1;
    }

    private final RowMapper<Session> rowMapper = (rs, row) -> {
        var session = new Session(
                rs.getString("id"),
                rs.getString("description")
        );
        session.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
        session.setEndDate(rs.getTimestamp("end_date") != null ? rs.getTimestamp("end_date").toLocalDateTime() : null);

        return session;
    };

    private final RowMapper<SessionVO> rowMapperFindAll = (rs, row) -> SessionVO.builder()
            .id(rs.getString("session_id"))
            .description(rs.getString("session_description"))
            .status(rs.getString("session_status"))
            .votes(List.of(
                    VoteVO.builder()
                            .id(rs.getString("vote_id"))
                            .voteStatus(rs.getString("vote_status"))
                            .cpf(rs.getString("cpf"))
                            .subjectId(rs.getString("subject_id"))
                            .subjectStatus(rs.getString("subject_status"))
                            .subjectDescription(rs.getString("subject_description"))
                            .build()
            ))
            .build();

    private final RowMapper<String[]> rowMapperFile = (rs, row) -> new String[]{
            rs.getString("quantity"),
            rs.getString("session_description"),
            rs.getString("subject_description")
    };

}
