package com.backoffice.core.subject.v1.usecase.dataprovider;

import com.backoffice.core.subject.v1.adapter.SubjectDataProcess;
import com.backoffice.core.subject.v1.model.Subject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CreateSubjectDataProcessTest implements SubjectDataProcess {

    @Override
    public Optional<Subject> save(Subject subject) {
        return Optional.of(subject);
    }

    @Override
    public boolean existsByDescription(Subject subject) {
        return false;
    }

    @Override
    public Optional<Subject> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void disableSubject(String id, LocalDateTime endDate) {

    }

    @Override
    public List<Subject> findAllBySessionId(String id) {
        return List.of();
    }

    @Override
    public void close(String id, LocalDateTime endDate) {

    }

}
