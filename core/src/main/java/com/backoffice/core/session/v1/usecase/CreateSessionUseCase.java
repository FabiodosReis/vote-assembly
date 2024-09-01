package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.model.Session;
import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.exception.SessionException;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class CreateSessionUseCase {

    private final SessionDataProvider dataProvider;

    public void save(Session session) throws SessionException {

        if(session.getSubjectList().isEmpty()){
            throw new SessionException("The Session need 1 or more subjects");
        }

        dataProvider.save(session);
    }

}
