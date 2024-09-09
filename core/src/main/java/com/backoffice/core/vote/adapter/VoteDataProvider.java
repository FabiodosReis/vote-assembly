package com.backoffice.core.vote.adapter;

import com.backoffice.core.vote.model.Vote;

import java.util.Optional;

public interface VoteDataProvider {

    Optional<Vote> save(Vote vote);

    boolean isAssociateAlreadyVoted(String subjectId, String associateId);

    Optional<Vote> findById(String id);
}
