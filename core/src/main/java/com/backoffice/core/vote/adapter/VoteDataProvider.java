package com.backoffice.core.vote.adapter;

import com.backoffice.core.vote.model.Vote;

public interface VoteDataProvider {

    void save(Vote vote);

    boolean isAssociateAlreadyVote(String voteId, String associateId);
}
