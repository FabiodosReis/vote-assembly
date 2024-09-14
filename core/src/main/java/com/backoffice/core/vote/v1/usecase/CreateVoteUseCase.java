package com.backoffice.core.vote.v1.usecase;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.enums.StatusAssociateEnum;
import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.vote.adapter.VoteDataProvider;
import com.backoffice.core.vote.exception.VoteException;
import com.backoffice.core.vote.model.Vote;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Named
@RequiredArgsConstructor
public class CreateVoteUseCase {

    private final VoteDataProvider dataProvider;
    private final AssociateDataProvider associateDataProvider;
    private final SubjectDataProvider subjectDataProvider;

    public Vote execute(Vote vote) throws VoteException {
        vote.setId(UUID.randomUUID().toString());

        var subject = subjectDataProvider.findById(vote.getSubjectId());

        var associate = associateDataProvider.findById(vote.getAssociateId())
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
