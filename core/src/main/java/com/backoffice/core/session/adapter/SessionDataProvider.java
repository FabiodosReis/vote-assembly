package com.backoffice.core.session.adapter;

import com.backoffice.core.session.model.Session;

import java.util.Optional;

public interface SessionDataProvider {

    Optional<Session> save(Session session);

    Optional<Session> closeSession(Session session);

    Optional<Session> findById(String id);

    boolean existsByDescription(String description);
}
