package com.backoffice.app.associate;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.enums.StatusAssociateEnum;
import com.backoffice.core.associate.model.Associate;
import com.backoffice.core.associate.vo.AssociateFilterVO;
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

import static com.backoffice.app.utils.FileUtils.getSql;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class AssociateRepository implements AssociateDataProvider {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${sql.base-path.associate}")
    private String basePath;

    @Override
    public Optional<Associate> save(Associate associate) {
        var sql = getSql(basePath.concat("/insertAssociate.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", associate.getId());
        param.put("name", associate.getName());
        param.put("cpf", associate.getCpf());
        param.put("status", associate.getStatus().name());

        jdbcTemplate.update(sql, param);

        return findById(associate.getId());
    }

    @Override
    public Optional<Associate> update(Associate associate) {
        var sql = getSql(basePath.concat("/updateAssociate.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("id", associate.getId());
        param.put("name", associate.getName());
        param.put("cpf", associate.getCpf());
        param.put("status", associate.getStatus().name());

        jdbcTemplate.update(sql, param);

        return findById(associate.getId());
    }

    @Override
    public Optional<Associate> findById(String id) {
        try {

            Map<String, Object> param = new HashMap<>();
            param.put("id", id);

            var sql = getSql(basePath.concat("/findAssociateById.sql"));
            var associate = jdbcTemplate.queryForObject(sql, param, rowMapper);
            return associate == null ? Optional.empty() : Optional.of(associate);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Associate> findAll(AssociateFilterVO vo) {
        var page = isEmpty(vo.getPage()) ? 0 : vo.getSize();
        var size = isEmpty(vo.getSize()) ? 10 : vo.getSize();

        Map<String, Object> param = new HashMap<>();
        param.put("cpf", vo.getCpf());
        param.put("page", page);
        param.put("size", size);

        var sql = getSql(basePath.concat("/findAllAssociate.sql"));
        return jdbcTemplate.query(sql, param, rowMapper);
    }

    @Override
    public boolean existsCpfInAnotherAssociate(String cpf, String associateId) {
        var sql = getSql(basePath.concat("/existsCpfInAnotherAssociate.sql"));
        Map<String, Object> param = new HashMap<>();
        param.put("cpf",cpf);
        param.put("id", associateId);


        var result = jdbcTemplate.queryForObject(sql, param, Integer.class);
        return result != null && result == 1;
    }

    private final RowMapper<Associate> rowMapper = (rs, row) -> {
        var associate = new Associate(
                rs.getString("id"),
                rs.getString("id"),
                rs.getString("cpf")
        );
        associate.setStatus(StatusAssociateEnum.get(rs.getString("status")));
        return associate;
    };
}
