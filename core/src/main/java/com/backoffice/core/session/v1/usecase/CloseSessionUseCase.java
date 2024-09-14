package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.exception.SessionException;
import com.backoffice.core.session.model.Session;
import com.backoffice.core.subject.adapter.SubjectDataProvider;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Named
@RequiredArgsConstructor
public class CloseSessionUseCase {

    private final SessionDataProvider sessionDataProvider;
    private final SubjectDataProvider subjectDataProvider;

    public Session execute(String sessionId) throws SessionException {

        var optionalSession = sessionDataProvider.findById(sessionId)
                .orElseThrow(() -> new SessionException(String.format("Session %s not found", sessionId)));

        optionalSession.setEndDate(LocalDateTime.now(ZoneId.of("UTC")));

        var subjectList = subjectDataProvider.findAllBySessionId(sessionId);

        subjectList
                .forEach(subject -> subjectDataProvider.close(subject.getId(), LocalDateTime.now(ZoneId.of("UTC"))));

        return sessionDataProvider.closeSession(optionalSession)
                .orElseGet(null);
    }
}
