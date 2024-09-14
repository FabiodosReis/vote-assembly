package com.backoffice.core.session.v1.usecase.dataprovider;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.model.Session;
import com.backoffice.core.session.vo.SessionFilterVO;
import com.backoffice.core.session.vo.SessionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CloseSessionNotFoundDataProviderTest implements SessionDataProvider {

    @Override
    public Optional<Session> save(Session session) {
        return Optional.empty();
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
