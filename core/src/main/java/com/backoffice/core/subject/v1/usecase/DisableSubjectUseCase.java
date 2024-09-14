package com.backoffice.core.subject.v1.usecase;


import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.exception.SubjectException;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.util.Objects.isNull;

@Named
@RequiredArgsConstructor
public class DisableSubjectUseCase {

    private final SubjectDataProvider dataProvider;

    public void execute(String id) throws SubjectException {

        if (isNull(id) || id.isBlank()) {
            throw new SubjectException("id is required");
        }

        var subject = dataProvider.findById(id)
                .orElseThrow(() -> new SubjectException("Subject not found"));

        dataProvider.disableSubject(subject.getId(), LocalDateTime.now(ZoneId.of("UTC")));
    }

}
