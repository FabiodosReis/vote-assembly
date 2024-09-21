package com.backoffice.core.session.v1.adapter;

import com.backoffice.core.session.v1.model.Session;
import com.backoffice.core.session.v1.vo.SessionFilterVO;
import com.backoffice.core.session.v1.vo.SessionVO;

import java.util.List;
import java.util.Optional;

public interface SessionDataProcess {

    Optional<Session> save(Session session);

    Optional<Session> closeSession(Session session);

    boolean sessionIsClosed(String sessionId);

    Optional<Session> findById(String id);

    List<SessionVO> findAll(SessionFilterVO vo);

    List<String[]> findAllCsv(SessionFilterVO vo);

    boolean existsByDescription(String description);
}
