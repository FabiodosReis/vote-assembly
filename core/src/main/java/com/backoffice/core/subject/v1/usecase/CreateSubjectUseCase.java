package com.backoffice.core.subject.v1.usecase;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.subject.model.Subject;
import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.exception.SubjectException;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;


@Named
@RequiredArgsConstructor
public class CreateSubjectUseCase {

    private final SubjectDataProvider dataProvider;
    private final SessionDataProvider sessionDataProvider;

    public Optional<Subject> execute(Subject subject) throws SubjectException {
        subject.setId(UUID.randomUUID().toString());

        if (isNull(subject.getDescription()) || subject.getDescription().isBlank()) {
            throw new SubjectException("Subject description is required");
        }

        if(dataProvider.existsByDescription(subject)){
            throw new SubjectException("Subject description already exists");
        }

        if (isNull(subject.getSessionId()) || subject.getSessionId().isBlank()) {
            throw new SubjectException("Session id is required");
        }

        if (sessionDataProvider.findById(subject.getSessionId()).isEmpty()) {
            throw new SubjectException("Session not found");
        }

        return dataProvider.save(subject);
    }

}
