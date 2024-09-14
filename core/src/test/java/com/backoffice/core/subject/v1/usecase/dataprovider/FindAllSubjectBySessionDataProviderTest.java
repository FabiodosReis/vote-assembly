package com.backoffice.core.subject.v1.usecase.dataprovider;

import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.model.Subject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FindAllSubjectBySessionDataProviderTest implements SubjectDataProvider {

    @Override
    public Optional<Subject> save(Subject subject) {
        return Optional.empty();
    }

    @Override
    public boolean existsByDescription(Subject subject) {
        return false;
    }

    @Override
    public Optional<Subject> findById(String id) {
        var subject = new Subject(
                "0191f18b-3392-7969-8326-31bc609c0661",
                "test",
                "0191f18b-5acc-7109-afad-fe03470909c6"
        );
        return Optional.of(subject);
    }

    @Override
    public void disableSubject(String id, LocalDateTime endDate) {

    }

    @Override
    public List<Subject> findAllBySessionId(String id) {
        return List.of(new Subject(
                        "0191f18b-3392-7969-8326-31bc609c0661",
                        "test",
                        "0191f18b-5acc-7109-afad-fe03470909c6"
                )
        );
    }

    @Override
    public void close(String id, LocalDateTime endDate) {

    }
}
