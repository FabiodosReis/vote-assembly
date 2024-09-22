package com.backoffice.core.vote.v1.usecase;

import com.backoffice.core.associate.v1.adapter.AssociateDataProcess;
import com.backoffice.core.associate.v1.usecase.dataProvider.CreateAssociateDataProviderAssociateNotAbleToVoteTest;
import com.backoffice.core.associate.v1.usecase.dataProvider.CreateAssociateDataProcessTest;
import com.backoffice.core.associate.v1.usecase.dataProvider.NotFoundAssociateDataProcessTest;
import com.backoffice.core.subject.v1.adapter.SubjectDataProcess;
import com.backoffice.core.subject.v1.usecase.dataprovider.CreateSubjectDataProcessTest;
import com.backoffice.core.subject.v1.usecase.dataprovider.FindByIdSubjectDataProcessTest;
import com.backoffice.core.subject.v1.usecase.dataprovider.FindByIdSubjectEndDateBeforeDataProcessTest;
import com.backoffice.core.vote.v1.adapter.VoteDataProcess;
import com.backoffice.core.vote.v1.exception.VoteException;
import com.backoffice.core.vote.v1.model.Vote;
import com.backoffice.core.vote.v1.usecase.dataprovider.CreateVoteDataProcessAssociateAlreadyVotedTest;
import com.backoffice.core.vote.v1.usecase.dataprovider.CreateVoteDataProcessTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.backoffice.core.vote.v1.enums.VoteStatusEnum.NO;
import static com.backoffice.core.vote.v1.enums.VoteStatusEnum.YES;
import static org.junit.jupiter.api.Assertions.*;

public class CreateVoteUseCaseTest {

    private CreateVoteUseCase useCase;

    @Test
    void shouldCreateVoteYes() {
        setup(
                new CreateVoteDataProcessTest(),
                new CreateAssociateDataProcessTest(),
                new FindByIdSubjectDataProcessTest()
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
                new CreateVoteDataProcessTest(),
                new CreateAssociateDataProcessTest(),
                new FindByIdSubjectDataProcessTest()
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
                new CreateVoteDataProcessTest(),
                new NotFoundAssociateDataProcessTest(),
                new FindByIdSubjectDataProcessTest()
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
                new CreateVoteDataProcessTest(),
                new CreateAssociateDataProviderAssociateNotAbleToVoteTest(),
                new FindByIdSubjectDataProcessTest()
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
                new CreateVoteDataProcessAssociateAlreadyVotedTest(),
                new CreateAssociateDataProcessTest(),
                new FindByIdSubjectDataProcessTest()
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
                new CreateVoteDataProcessTest(),
                new CreateAssociateDataProcessTest(),
                new CreateSubjectDataProcessTest()
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

    @Test
    void shouldNotCreateVoteBecauseSubjectClosed() {
        setup(
                new CreateVoteDataProcessTest(),
                new CreateAssociateDataProcessTest(),
                new FindByIdSubjectEndDateBeforeDataProcessTest()
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

        assertEquals("Subject 0191f168-8ec6-74a4-ad9e-102616607e24 closed", exception.getMessage());
    }

    private void setup(
            VoteDataProcess dataProvider,
            AssociateDataProcess associateDataProcess,
            SubjectDataProcess subjectDataProcess
    ) {
        useCase = new CreateVoteUseCase(dataProvider, associateDataProcess, subjectDataProcess);
    }
}
