package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.exception.SessionException;
import com.backoffice.core.session.v1.usecase.dataprovider.CloseSessionDataProviderTest;
import com.backoffice.core.session.v1.usecase.dataprovider.CloseSessionNotFoundDataProviderTest;
import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.v1.usecase.dataprovider.FindAllSubjectBySessionDataProviderTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CloseSessionUseCaseTest {

    private CloseSessionUseCase useCase;


    @Test
    void shouldCloseSession() {
        setupMock(new CloseSessionDataProviderTest(), new FindAllSubjectBySessionDataProviderTest());

        var session = useCase.execute("0191f0fa-ffc5-7267-8704-db0702624c5b");

        assertEquals("0191f0fa-ffc5-7267-8704-db0702624c5b", session.getId());
        assertNotNull(session.getEndDate());
    }

    @Test
    void shouldNotCloseSessionBecauseSessionNotFound() {
        setupMock(new CloseSessionNotFoundDataProviderTest(), new FindAllSubjectBySessionDataProviderTest());

        var exception = assertThrows(SessionException.class, () -> {
            useCase.execute("0191f0fa-ffc5-7267-8704-db0702624c5b");
        });

        assertEquals("Session 0191f0fa-ffc5-7267-8704-db0702624c5b not found", exception.getMessage());
    }


    private void setupMock(SessionDataProvider dataProvider, SubjectDataProvider subjectDataProvider) {
        useCase = new CloseSessionUseCase(dataProvider, subjectDataProvider);
    }
}
