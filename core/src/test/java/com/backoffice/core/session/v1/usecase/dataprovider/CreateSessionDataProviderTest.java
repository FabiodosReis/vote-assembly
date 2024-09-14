package com.backoffice.core.session.v1.usecase.dataprovider;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.model.Session;
import com.backoffice.core.session.vo.SessionFilterVO;
import com.backoffice.core.session.vo.SessionVO;
import com.backoffice.core.session.vo.VoteVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateSessionDataProviderTest implements SessionDataProvider {

    @Override
    public Optional<Session> save(Session session) {
        return Optional.of(session);
    }

    @Override
    public Optional<Session> closeSession(Session session) {
        return Optional.empty();
    }

    @Override
    public Optional<Session> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<SessionVO> findAll(SessionFilterVO vo) {
        return List.of(
                SessionVO.builder()
                        .description("test")
                        .id("0191f0fa-ffc5-7267-8704-db0702624c5b")
                        .status("OPEN")
                        .votes(List.of(
                                VoteVO.builder()
                                        .id("0191f0fa-ffc5-7267-8704-db0702624c5c")
                                        .cpf("055.613.735.36")
                                        .subjectId("0191f0fa-ffc5-7267-8704-db0702624c5d")
                                        .subjectDescription("judgment")
                                        .voteStatus("YES")
                                        .build()
                        ))
                        .build()
        );
    }

    @Override
    public List<String[]> findAllCsv(SessionFilterVO vo) {
        return new ArrayList<>();
    }

    @Override
    public boolean existsByDescription(String description) {
        return false;
    }
}
