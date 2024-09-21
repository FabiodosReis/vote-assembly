package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.v1.adapter.SessionDataProcess;
import com.backoffice.core.session.v1.exception.SessionException;
import com.backoffice.core.session.v1.usecase.dataprovider.CloseSessionDataProcessTest;
import com.backoffice.core.session.v1.usecase.dataprovider.CloseSessionNotFoundDataProcessTest;
import com.backoffice.core.subject.v1.adapter.SubjectDataProcess;
import com.backoffice.core.subject.v1.usecase.dataprovider.FindAllSubjectBySessionDataProcessTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CloseSessionUseCaseTest {

    private CloseSessionUseCase useCase;


    @Test
    void shouldCloseSession() {
        setupMock(new CloseSessionDataProcessTest(), new FindAllSubjectBySessionDataProcessTest());

        var session = useCase.execute("0191f0fa-ffc5-7267-8704-db0702624c5b");

        assertEquals("0191f0fa-ffc5-7267-8704-db0702624c5b", session.getId());
        assertNotNull(session.getEndDate());
    }

    @Test
    void shouldNotCloseSessionBecauseSessionNotFound() {
        setupMock(new CloseSessionNotFoundDataProcessTest(), new FindAllSubjectBySessionDataProcessTest());

        var exception = assertThrows(SessionException.class, () -> {
            useCase.execute("0191f0fa-ffc5-7267-8704-db0702624c5b");
        });

        assertEquals("Session 0191f0fa-ffc5-7267-8704-db0702624c5b not found", exception.getMessage());
    }


    private void setupMock(SessionDataProcess dataProvider, SubjectDataProcess subjectDataProcess) {
        useCase = new CloseSessionUseCase(dataProvider, subjectDataProcess);
    }
}
