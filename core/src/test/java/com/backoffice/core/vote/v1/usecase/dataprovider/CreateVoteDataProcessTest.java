package com.backoffice.core.vote.v1.usecase.dataprovider;

import com.backoffice.core.vote.v1.adapter.VoteDataProcess;
import com.backoffice.core.vote.v1.model.Vote;

import java.util.Optional;

public class CreateVoteDataProcessTest implements VoteDataProcess {

    @Override
    public Optional<Vote> save(Vote vote) {
        return Optional.of(vote);
    }

    @Override
    public boolean isAssociateAlreadyVoted(String subjectId, String associateId) {
        return false;
    }

    @Override
    public Optional<Vote> findById(String id) {
        return Optional.empty();
    }
}
