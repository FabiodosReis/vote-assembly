package com.backoffice.app.session;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.model.Session;
import org.springframework.stereotype.Repository;

@Repository
public class SessionRepository implements SessionDataProvider {

    @Override
    public void save(Session session) {

    }
}
