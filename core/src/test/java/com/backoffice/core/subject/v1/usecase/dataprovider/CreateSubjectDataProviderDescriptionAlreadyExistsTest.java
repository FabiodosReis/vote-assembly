package com.backoffice.core.subject.v1.usecase.dataprovider;

import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.model.Subject;

import java.util.Optional;

public class CreateSubjectDataProviderDescriptionAlreadyExistsTest implements SubjectDataProvider {

    @Override
    public Optional<Subject> save(Subject subject) {
        return Optional.of(subject);
    }

    @Override
    public boolean existsByDescription(Subject subject) {
        return true;
    }

    @Override
    public Optional<Subject> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void disableSubject(String id) {

    }
}
