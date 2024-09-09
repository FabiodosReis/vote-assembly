package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.model.Session;
import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.exception.SessionException;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.UUID;

import static java.util.Objects.isNull;

@Named
@RequiredArgsConstructor
public class CreateSessionUseCase {

    private final SessionDataProvider dataProvider;

    public void execute(Session session) throws SessionException {
        session.setId(UUID.randomUUID().toString());

        if (isNull(session.getDescription()) || session.getDescription().isEmpty()) {
            throw new SessionException("The Session description is required");
        }

        if (dataProvider.existsByDescription(session.getDescription())) {
            throw new SessionException("The Session description already Exists");
        }

        dataProvider.save(session);
    }

}
