package com.backoffice.core.subject.v1.usecase;

import com.backoffice.core.session.v1.adapter.SessionDataProcess;
import com.backoffice.core.subject.v1.model.Subject;
import com.backoffice.core.subject.v1.adapter.SubjectDataProcess;
import com.backoffice.core.subject.v1.exception.SubjectException;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

import java.util.UUID;

import static java.util.Objects.isNull;


@Named
@RequiredArgsConstructor
public class CreateSubjectUseCase {

    private final SubjectDataProcess dataProvider;
    private final SessionDataProcess sessionDataProcess;

    public Subject execute(Subject subject) throws SubjectException {
        subject.setId(UUID.randomUUID().toString());

        if (isNull(subject.getDescription()) || subject.getDescription().isBlank()) {
            throw new SubjectException("Subject description is required");
        }

        if (dataProvider.existsByDescription(subject)) {
            throw new SubjectException("Subject description already exists");
        }

        if (isNull(subject.getSessionId()) || subject.getSessionId().isBlank()) {
            throw new SubjectException("Session id is required");
        }

        if (sessionDataProcess.findById(subject.getSessionId()).isEmpty()) {
            throw new SubjectException("Session not found");
        }

        if(sessionDataProcess.sessionIsClosed(subject.getSessionId())){
            throw new SubjectException("Session is closed");
        }

        return dataProvider.save(subject)
                .orElseGet(null);
    }

}
