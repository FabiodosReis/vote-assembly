package com.backoffice.core.vote.v1.usecase;

import com.backoffice.core.vote.adapter.VoteDataProvider;
import com.backoffice.core.vote.enums.VoteStatusEnum;
import com.backoffice.core.vote.exception.VoteException;
import com.backoffice.core.vote.model.Vote;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class CreateVoteUserCase {

    private final VoteDataProvider dataProvider;

    public void save(Vote vote) throws VoteException {

        if (VoteStatusEnum.NO == vote.getStatus()) {
            throw new VoteException(String.format("Associate %s not able to vote", vote.getAssociate().getId()));
        }

        if (dataProvider.isAssociateAlreadyVote(vote.getId(), vote.getAssociate().getId())) {
            throw new VoteException(String.format("Associate %s already voted", vote.getAssociate().getId()));
        }

        dataProvider.save(vote);
    }
}
