package com.backoffice.app.vote;

import com.backoffice.core.vote.adapter.VoteDataProvider;
import com.backoffice.core.vote.model.Vote;
import org.springframework.stereotype.Repository;

@Repository
public class VoteRepository implements VoteDataProvider {

    @Override
    public void save(Vote vote) {

    }

    @Override
    public boolean isAssociateAlreadyVote(String voteId, String associateId) {
        return false;
    }
}
