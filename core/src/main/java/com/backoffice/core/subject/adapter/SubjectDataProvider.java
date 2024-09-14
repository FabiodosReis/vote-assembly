package com.backoffice.core.subject.adapter;

import com.backoffice.core.subject.model.Subject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SubjectDataProvider {

    Optional<Subject> save(Subject subject);

    boolean existsByDescription(Subject subject);

    Optional<Subject> findById(String id);

    void disableSubject(String id, LocalDateTime endDate);

    List<Subject> findAllBySessionId(String id);

    void close(String id, LocalDateTime endDate);

}
