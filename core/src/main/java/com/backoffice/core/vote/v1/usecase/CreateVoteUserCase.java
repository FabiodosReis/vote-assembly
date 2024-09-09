package com.backoffice.core.vote.v1.usecase;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.enums.StatusAssociateEnum;
import com.backoffice.core.vote.adapter.VoteDataProvider;
import com.backoffice.core.vote.exception.VoteException;
import com.backoffice.core.vote.model.Vote;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.UUID;

@Named
@RequiredArgsConstructor
public class CreateVoteUserCase {

    private final VoteDataProvider dataProvider;
    private final AssociateDataProvider associateDataProvider;

    public void execute(Vote vote) throws VoteException {
        vote.setId(UUID.randomUUID().toString());

        var associate = associateDataProvider.findById(vote.getAssociateId())
                .orElseThrow(() -> new VoteException(String.format("Associate %s not found", vote.getAssociateId())));

        if (associate.getStatus() == StatusAssociateEnum.UNABLE_TO_VOTE) {
            throw new VoteException(String.format("Associate %s not able to vote", vote.getAssociateId()));
        }

        if (dataProvider.isAssociateAlreadyVoted(vote.getAssociateId(), vote.getSubjectId())) {
            throw new VoteException(String.format("Associate %s already voted", vote.getAssociateId()));
        }

        dataProvider.save(vote);
    }
}
