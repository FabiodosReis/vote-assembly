package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.v1.adapter.SessionDataProcess;
import com.backoffice.core.session.v1.exception.SessionException;
import com.backoffice.core.session.v1.model.Session;
import com.backoffice.core.session.v1.usecase.dataprovider.CreateSessionDataProviderSessionAlreadyExistsTest;
import com.backoffice.core.session.v1.usecase.dataprovider.CreateSessionDataProcessTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSessionUseCaseTest {

    private CreateSessionUseCase useCase;

    @Test
    void shouldCreateSession() {
        setupMock(new CreateSessionDataProcessTest());

        var session = new Session(
                null,
                "test"
        );

        var currentSession = useCase.execute(session);

        assertNotNull(currentSession.getId());
        assertEquals("test", currentSession.getDescription());
        assertNull(currentSession.getEndDate());
    }

    @Test
    void shouldNotCreateSessionBecauseDescriptionIsRequired() {
        setupMock(new CreateSessionDataProcessTest());

        var exception = assertThrows(SessionException.class, () -> {
            var session = new Session(
                    null,
                    ""
            );

            useCase.execute(session);
        });
        assertEquals("The Session description is required", exception.getMessage());
    }

    @Test
    void shouldNotCreateSessionBecauseDescriptionAlreadyExists() {
        setupMock(new CreateSessionDataProviderSessionAlreadyExistsTest());

        var exception = assertThrows(SessionException.class, () -> {
            var session = new Session(
                    null,
                    ""
            );

            useCase.execute(session);
        });
        assertEquals("The Session description is required", exception.getMessage());
    }


    private void setupMock(SessionDataProcess dataProvider) {
        useCase = new CreateSessionUseCase(dataProvider);
    }
}
