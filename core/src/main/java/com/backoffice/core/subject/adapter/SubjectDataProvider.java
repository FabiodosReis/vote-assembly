package com.backoffice.core.subject.adapter;

import com.backoffice.core.subject.model.Subject;

import java.util.Optional;

public interface SubjectDataProvider {

    Optional<Subject> save(Subject subject);

    boolean existsByDescription(Subject subject);

    Optional<Subject> findById(String id);

    void disableSubject(String id);
}
