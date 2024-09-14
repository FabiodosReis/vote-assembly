package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.exception.SessionException;
import com.backoffice.core.session.model.Session;
import com.backoffice.core.session.v1.usecase.dataprovider.CreateSessionDataProviderSessionAlreadyExistsTest;
import com.backoffice.core.session.v1.usecase.dataprovider.CreateSessionDataProviderTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSessionUseCaseTest {

    private CreateSessionUseCase useCase;

    @Test
    void shouldCreateSession() {
        setupMock(new CreateSessionDataProviderTest());

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
        setupMock(new CreateSessionDataProviderTest());

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


    private void setupMock(SessionDataProvider dataProvider) {
        useCase = new CreateSessionUseCase(dataProvider);
    }
}
