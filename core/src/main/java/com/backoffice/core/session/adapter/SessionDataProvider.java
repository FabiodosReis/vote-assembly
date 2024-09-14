package com.backoffice.core.session.adapter;

import com.backoffice.core.session.model.Session;
import com.backoffice.core.session.vo.SessionFilterVO;
import com.backoffice.core.session.vo.SessionVO;

import java.util.List;
import java.util.Optional;

public interface SessionDataProvider {

    Optional<Session> save(Session session);

    Optional<Session> closeSession(Session session);

    Optional<Session> findById(String id);

    List<SessionVO> findAll(SessionFilterVO vo);

    List<String[]> findAllCsv(SessionFilterVO vo);

    boolean existsByDescription(String description);
}
