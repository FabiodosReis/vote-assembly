package com.backoffice.core.session.v1.usecase.dataprovider;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.model.Session;

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
    public boolean existsByDescription(String description) {
        return false;
    }
}
