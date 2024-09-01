package com.backoffice.core.subject.v1.usecase;

import com.backoffice.core.subject.model.Subject;
import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.exception.SubjectException;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

import static java.util.Objects.isNull;

@Named
@RequiredArgsConstructor
public class CreateSubjectUseCase {

    private final SubjectDataProvider dataProvider;

    public void create(Subject subject) throws SubjectException {

        if (isNull(subject.getDescription()) || subject.getDescription().isEmpty()) {
            throw new SubjectException("Subject description is required");
        }

        dataProvider.save(subject);

    }

}
