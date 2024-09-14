package com.backoffice.core.vote.v1.usecase;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.v1.usecase.dataProvider.CreateAssociateDataProviderAssociateNotAbleToVoteTest;
import com.backoffice.core.associate.v1.usecase.dataProvider.CreateAssociateDataProviderTest;
import com.backoffice.core.associate.v1.usecase.dataProvider.NotFoundAssociateDataProviderTest;
import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.v1.usecase.dataprovider.CreateSubjectDataProviderTest;
import com.backoffice.core.subject.v1.usecase.dataprovider.FindByIdSubjectDataProviderTest;
import com.backoffice.core.vote.adapter.VoteDataProvider;
import com.backoffice.core.vote.exception.VoteException;
import com.backoffice.core.vote.model.Vote;
import com.backoffice.core.vote.v1.usecase.dataprovider.CreateVoteDataProviderAssociateAlreadyVotedTest;
import com.backoffice.core.vote.v1.usecase.dataprovider.CreateVoteDataProviderTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.backoffice.core.vote.enums.VoteStatusEnum.NO;
import static com.backoffice.core.vote.enums.VoteStatusEnum.YES;
import static org.junit.jupiter.api.Assertions.*;

public class CreateVoteUseCaseTest {

    private CreateVoteUseCase useCase;

    @Test
    void shouldCreateVoteYes() {
        setup(
                new CreateVoteDataProviderTest(),
                new CreateAssociateDataProviderTest(),
                new FindByIdSubjectDataProviderTest()
        );

        var vote = new Vote(
                null,
                "0191f12e-1848-70f7-b32f-38a029602a4a",
                "0191f168-8ec6-74a4-ad9e-102616607e24",
                YES
        );

        var currentVote = useCase.execute(vote);

        var now = LocalDateTime.now(ZoneId.of("UTC"));
        assertNotNull(currentVote.getId());
        assertEquals("0191f12e-1848-70f7-b32f-38a029602a4a", currentVote.getAssociateId());
        assertEquals("0191f168-8ec6-74a4-ad9e-102616607e24", currentVote.getSubjectId());
        assertEquals(YES, currentVote.getStatus());
        assertEquals(now.getYear(), currentVote.getCreateDate().getYear());
        assertEquals(now.getMonthValue(), currentVote.getCreateDate().getMonthValue());
        assertEquals(now.getDayOfMonth(), currentVote.getCreateDate().getDayOfMonth());
        assertEquals(now.getHour(), currentVote.getCreateDate().getHour());
    }

    @Test
    void shouldCreateVoteNo() {
        setup(
                new CreateVoteDataProviderTest(),
                new CreateAssociateDataProviderTest(),
                new FindByIdSubjectDataProviderTest()
        );

        var vote = new Vote(
                null,
                "0191f12e-1848-70f7-b32f-38a029602a4a",
                "0191f168-8ec6-74a4-ad9e-102616607e24",
                NO
        );

        var currentVote = useCase.execute(vote);

        var now = LocalDateTime.now(ZoneId.of("UTC"));
        assertNotNull(currentVote.getId());
        assertEquals("0191f12e-1848-70f7-b32f-38a029602a4a", currentVote.getAssociateId());
        assertEquals("0191f168-8ec6-74a4-ad9e-102616607e24", currentVote.getSubjectId());
        assertEquals(NO, currentVote.getStatus());
        assertEquals(now.getYear(), currentVote.getCreateDate().getYear());
        assertEquals(now.getMonthValue(), currentVote.getCreateDate().getMonthValue());
        assertEquals(now.getDayOfMonth(), currentVote.getCreateDate().getDayOfMonth());
        assertEquals(now.getHour(), currentVote.getCreateDate().getHour());
    }

    @Test
    void shouldNotCreateVoteBecauseAssociateNotFound() {
        setup(
                new CreateVoteDataProviderTest(),
                new NotFoundAssociateDataProviderTest(),
                new FindByIdSubjectDataProviderTest()
        );

        var exception = assertThrows(VoteException.class, () -> {

            var vote = new Vote(
                    null,
                    "0191f12e-1848-70f7-b32f-38a029602a4a",
                    "0191f168-8ec6-74a4-ad9e-102616607e24",
                    NO
            );
            useCase.execute(vote);
        });

        assertEquals("Associate 0191f12e-1848-70f7-b32f-38a029602a4a not found", exception.getMessage());
    }

    @Test
    void shouldNotCreateVoteBecauseAssociateUnableToVote() {
        setup(
                new CreateVoteDataProviderTest(),
                new CreateAssociateDataProviderAssociateNotAbleToVoteTest(),
                new FindByIdSubjectDataProviderTest()
        );

        var exception = assertThrows(VoteException.class, () -> {

            var vote = new Vote(
                    null,
                    "0191f12e-1848-70f7-b32f-38a029602a4a",
                    "0191f168-8ec6-74a4-ad9e-102616607e24",
                    YES
            );
            useCase.execute(vote);
        });

        assertEquals("Associate 0191f12e-1848-70f7-b32f-38a029602a4a not able to vote", exception.getMessage());
    }

    @Test
    void shouldNotCreateVoteBecauseAssociateAlreadyVoted() {
        setup(
                new CreateVoteDataProviderAssociateAlreadyVotedTest(),
                new CreateAssociateDataProviderTest(),
                new FindByIdSubjectDataProviderTest()
        );

        var exception = assertThrows(VoteException.class, () -> {

            var vote = new Vote(
                    null,
                    "0191f12e-1848-70f7-b32f-38a029602a4a",
                    "0191f168-8ec6-74a4-ad9e-102616607e24",
                    YES
            );
            useCase.execute(vote);
        });

        assertEquals("Associate 0191f12e-1848-70f7-b32f-38a029602a4a already voted", exception.getMessage());
    }

    @Test
    void shouldNotCreateVoteBecauseAssociateSubjectNotFound() {
        setup(
                new CreateVoteDataProviderTest(),
                new CreateAssociateDataProviderTest(),
                new CreateSubjectDataProviderTest()
        );

        var exception = assertThrows(VoteException.class, () -> {

            var vote = new Vote(
                    null,
                    "0191f12e-1848-70f7-b32f-38a029602a4a",
                    "0191f168-8ec6-74a4-ad9e-102616607e24",
                    YES
            );
            useCase.execute(vote);
        });

        assertEquals("Subject 0191f168-8ec6-74a4-ad9e-102616607e24 not found", exception.getMessage());
    }

    private void setup(
            VoteDataProvider dataProvider,
            AssociateDataProvider associateDataProvider,
            SubjectDataProvider subjectDataProvider
    ) {
        useCase = new CreateVoteUseCase(dataProvider, associateDataProvider, subjectDataProvider);
    }
}
