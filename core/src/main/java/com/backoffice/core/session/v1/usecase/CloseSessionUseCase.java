package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.v1.adapter.SessionDataProcess;
import com.backoffice.core.session.v1.exception.SessionException;
import com.backoffice.core.session.v1.model.Session;
import com.backoffice.core.subject.v1.adapter.SubjectDataProcess;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Named
@RequiredArgsConstructor
public class CloseSessionUseCase {

    private final SessionDataProcess sessionDataProcess;
    private final SubjectDataProcess subjectDataProcess;

    public Session execute(String sessionId) throws SessionException {

        var optionalSession = sessionDataProcess.findById(sessionId)
                .orElseThrow(() -> new SessionException(String.format("Session %s not found", sessionId)));

        optionalSession.setEndDate(LocalDateTime.now(ZoneId.of("UTC")));

        var subjectList = subjectDataProcess.findAllBySessionId(sessionId);

        subjectList
                .forEach(subject -> subjectDataProcess.close(subject.getId(), LocalDateTime.now(ZoneId.of("UTC"))));

        return sessionDataProcess.closeSession(optionalSession)
                .orElseGet(null);
    }
}
