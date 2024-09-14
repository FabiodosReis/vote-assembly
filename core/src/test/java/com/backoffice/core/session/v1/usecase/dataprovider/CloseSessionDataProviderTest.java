package com.backoffice.core.session.v1.usecase.dataprovider;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.model.Session;
import com.backoffice.core.session.vo.SessionFilterVO;
import com.backoffice.core.session.vo.SessionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CloseSessionDataProviderTest implements SessionDataProvider {

    @Override
    public Optional<Session> save(Session session) {
        return Optional.empty();
    }

    @Override
    public Optional<Session> closeSession(Session session) {
        return Optional.of(session);
    }

    @Override
    public Optional<Session> findById(String id) {
        var session = new Session(
                "0191f0fa-ffc5-7267-8704-db0702624c5b",
                "test"
        );
        return Optional.of(session);
    }

    @Override
    public List<SessionVO> findAll(SessionFilterVO vo) {
        return List.of();
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
