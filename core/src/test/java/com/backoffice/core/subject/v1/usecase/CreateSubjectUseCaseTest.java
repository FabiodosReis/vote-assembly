package com.backoffice.core.subject.v1.usecase;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.v1.usecase.dataprovider.CloseSessionNotFoundDataProviderTest;
import com.backoffice.core.session.v1.usecase.dataprovider.CreateSessionDataProviderSessionAlreadyExistsTest;
import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.exception.SubjectException;
import com.backoffice.core.subject.model.Subject;
import com.backoffice.core.subject.v1.usecase.dataprovider.CreateSubjectDataProviderDescriptionAlreadyExistsTest;
import com.backoffice.core.subject.v1.usecase.dataprovider.CreateSubjectDataProviderTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSubjectUseCaseTest {

    private CreateSubjectUseCase useCase;

    @Test
    void shouldCreateSubjectWithoutEndDate() {
        setup(new CreateSubjectDataProviderTest(), new CreateSessionDataProviderSessionAlreadyExistsTest());

        var subject = new Subject(
                null,
                "test",
                "0191f0fa-ffc5-7267-8704-db0702624c5b"
        );


        var currentSubject = useCase.execute(subject);

        var now = LocalDateTime.now(ZoneId.of("UTC"));
        assertNotNull(currentSubject.getId());
        assertEquals("test", currentSubject.getDescription());
        assertEquals(now.getYear(), currentSubject.getEndDate().getYear());
        assertEquals(now.getMonthValue(), currentSubject.getEndDate().getMonthValue());
        assertEquals(now.getDayOfMonth(), currentSubject.getEndDate().getDayOfMonth());
        assertEquals(now.getHour(), currentSubject.getEndDate().getHour());
        assertEquals(now.plusMinutes(1).getMinute(), currentSubject.getEndDate().getMinute());

    }

    @Test
    void shouldCreateSubjectWithEndDate() {
        setup(new CreateSubjectDataProviderTest(), new CreateSessionDataProviderSessionAlreadyExistsTest());

        var endDate = LocalDateTime.of(2024, 10, 1, 0, 0, 0)
                .atZone(ZoneId.of("UTC")).toLocalDateTime();

        var subject = new Subject(
                null,
                "test",
                "0191f0fa-ffc5-7267-8704-db0702624c5b",
                endDate
        );


        var currentSubject = useCase.execute(subject);

        assertNotNull(currentSubject.getId());
        assertEquals("test", currentSubject.getDescription());
        assertEquals(2024, currentSubject.getEndDate().getYear());
        assertEquals(10, currentSubject.getEndDate().getMonthValue());
        assertEquals(1, currentSubject.getEndDate().getDayOfMonth());
        assertEquals(0, currentSubject.getEndDate().getHour());
        assertEquals(0, currentSubject.getEndDate().getMinute());
        assertEquals(0, currentSubject.getEndDate().getSecond());

    }


    @Test
    void shouldNotCreateSubjectBecauseDescriptionIsRequired() {
        setup(new CreateSubjectDataProviderTest(), new CreateSessionDataProviderSessionAlreadyExistsTest());

        var exception = assertThrows(SubjectException.class, () -> {
            var subject = new Subject(
                    null,
                    "",
                    "0191f0fa-ffc5-7267-8704-db0702624c5b"
            );
            useCase.execute(subject);
        });

        assertEquals("Subject description is required", exception.getMessage());
    }

    @Test
    void shouldNotCreateSubjectBecauseDescriptionAlreadyExists() {
        setup(new CreateSubjectDataProviderDescriptionAlreadyExistsTest(), new CreateSessionDataProviderSessionAlreadyExistsTest());

        var exception = assertThrows(SubjectException.class, () -> {
            var subject = new Subject(
                    null,
                    "test",
                    "0191f0fa-ffc5-7267-8704-db0702624c5b"
            );
            useCase.execute(subject);
        });

        assertEquals("Subject description already exists", exception.getMessage());
    }

    @Test
    void shouldNotCreateSubjectBecauseSessionIsRequired() {
        setup(new CreateSubjectDataProviderTest(), new CreateSessionDataProviderSessionAlreadyExistsTest());

        var exception = assertThrows(SubjectException.class, () -> {
            var subject = new Subject(
                    null,
                    "test",
                    ""
            );
            useCase.execute(subject);
        });

        assertEquals("Session id is required", exception.getMessage());
    }

    @Test
    void shouldNotCreateSubjectBecauseSessionNotFound() {
        setup(new CreateSubjectDataProviderTest(), new CloseSessionNotFoundDataProviderTest());

        var exception = assertThrows(SubjectException.class, () -> {
            var subject = new Subject(
                    null,
                    "test",
                    "0191f0fa-ffc5-7267-8704-db0702624c5b"
            );
            useCase.execute(subject);
        });

        assertEquals("Session not found", exception.getMessage());
    }


    private void setup(
            SubjectDataProvider subjectDataProvider,
            SessionDataProvider sessionDataProvider) {
        useCase = new CreateSubjectUseCase(subjectDataProvider, sessionDataProvider);
    }

}
