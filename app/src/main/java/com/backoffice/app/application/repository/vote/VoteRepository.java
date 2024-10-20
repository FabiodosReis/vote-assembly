package com.backoffice.app.application.repository.vote;

import com.backoffice.core.vote.v1.enums.VoteStatusEnum;
import com.backoffice.core.vote.v1.model.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.backoffice.app.application.constants.ApplicationConstants.FILE_SEPARATOR;
import static com.backoffice.app.application.utils.FileUtils.getSql;

@Repository
@RequiredArgsConstructor
public class VoteRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${sql.base-path.vote}")
    private String basePath;

    public Optional<Vote> save(Vote vote) {
        var sql = getSql(basePath.concat(FILE_SEPARATOR).concat("insertVote.sql"));

        Map<String, Object> param = new HashMap<>();
        param.put("id", vote.getId());
        param.put("status", vote.getStatus().name());
        param.put("createDate", vote.getCreateDate());
        param.put("subjectId", vote.getSubjectId());
        param.put("associateId", vote.getAssociateId());

        jdbcTemplate.update(sql, param);
        return findById(vote.getId());
    }

    public Optional<Vote> findById(String id) {
        try {

            Map<String, Object> param = new HashMap<>();
            param.put("id", id);

            var sql = getSql(basePath.concat(FILE_SEPARATOR).concat("findVoteById.sql"));
            var session = jdbcTemplate.queryForObject(sql, param, rowMapper);
            return session == null ? Optional.empty() : Optional.of(session);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public boolean isAssociateAlreadyVoted(String associateId, String subjectId) {
        var sql = getSql(basePath.concat(FILE_SEPARATOR).concat("isAssociateAlreadyVoted.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("associateId", associateId);
        param.put("subjectId", subjectId);

        var result = jdbcTemplate.queryForObject(sql, param, Integer.class);
        return result != null && result == 1;

    }

    private final RowMapper<Vote> rowMapper = (rs, row) -> {
        var vote = new Vote(
                rs.getString("id"),
                rs.getString("associate_id"),
                rs.getString("subject_id"),
                rs.getString("status").equals(VoteStatusEnum.YES.name()) ? VoteStatusEnum.YES : VoteStatusEnum.NO
        );

        vote.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        vote.setId(rs.getString("id"));

        return vote;
    };
}
