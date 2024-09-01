package com.backoffice.core.session.adapter;

import com.backoffice.core.session.model.Session;

public interface SessionDataProvider {

    void save(Session session);
}
