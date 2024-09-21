package com.backoffice.core.vote.v1.usecase;

import com.backoffice.core.associate.v1.adapter.AssociateDataProcess;
import com.backoffice.core.associate.v1.enums.StatusAssociateEnum;
import com.backoffice.core.subject.v1.adapter.SubjectDataProcess;
import com.backoffice.core.vote.v1.adapter.VoteDataProcess;
import com.backoffice.core.vote.v1.exception.VoteException;
import com.backoffice.core.vote.v1.model.Vote;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;


@Named
public class CreateVoteUseCase {

    public CreateVoteUseCase(
            VoteDataProcess voteDataProcess,
            AssociateDataProcess associateDataProcess,
            SubjectDataProcess subjectDataProcess
    ){
        this.dataProvider = voteDataProcess;
        this.associateDataProcess = associateDataProcess;
        this.subjectDataProcess = subjectDataProcess;
    }

    private final VoteDataProcess dataProvider;
    private final AssociateDataProcess associateDataProcess;
    private final SubjectDataProcess subjectDataProcess;

    public Vote execute(Vote vote) throws VoteException {
        vote.setId(UUID.randomUUID().toString());

        var subject = subjectDataProcess.findById(vote.getSubjectId());

        var associate = associateDataProcess.findById(vote.getAssociateId())
                .orElseThrow(() -> new VoteException(String.format("Associate %s not found", vote.getAssociateId())));

        if (associate.getStatus() == StatusAssociateEnum.UNABLE_TO_VOTE) {
            throw new VoteException(String.format("Associate %s not able to vote", vote.getAssociateId()));
        }

        if (dataProvider.isAssociateAlreadyVoted(vote.getAssociateId(), vote.getSubjectId())) {
            throw new VoteException(String.format("Associate %s already voted", vote.getAssociateId()));
        }

        if (subject.isEmpty()) {
            throw new VoteException(String.format("Subject %s not found", vote.getSubjectId()));
        }

        if(LocalDateTime.now(ZoneId.of("UTC")).isAfter(subject.get().getEndDate())){
            throw new VoteException(String.format("Subject %s closed", vote.getSubjectId()));
        }

        return dataProvider.save(vote)
                .orElseGet(null);
    }
}
