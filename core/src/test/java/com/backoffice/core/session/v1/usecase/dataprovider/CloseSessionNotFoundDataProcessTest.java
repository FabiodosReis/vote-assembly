package com.backoffice.core.session.v1.usecase.dataprovider;

import com.backoffice.core.session.v1.adapter.SessionDataProcess;
import com.backoffice.core.session.v1.model.Session;
import com.backoffice.core.session.v1.vo.SessionFilterVO;
import com.backoffice.core.session.v1.vo.SessionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CloseSessionNotFoundDataProcessTest implements SessionDataProcess {

    @Override
    public Optional<Session> save(Session session) {
        return Optional.empty();
    }

    @Override
    public Optional<Session> closeSession(Session session) {
        return Optional.empty();
    }

    @Override
    public boolean sessionIsClosed(String sessionId) {
        return false;
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
